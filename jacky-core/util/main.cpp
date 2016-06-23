#include <iostream>
#include <fstream>
#include <cstdlib>
#include <string>
#include <vector>


static char __shared_buffer[10240];


struct GString
{
	int len;
	std::string str;


	void read_binary(std::istream& ifs)
	{
		ifs.read((char*)&len, sizeof(len));
		ifs.read(__shared_buffer, len);
		__shared_buffer[len] = '\0';
		str.assign(__shared_buffer);
	}
};


struct GVertex
{
	float Positon[3];
	float Normal[3]; // ?
	unsigned int Color;
	float UV[2];
};


struct GIndex
{
	int unknow;
	int Triangle[3];
	float FaceNormal[3]; // ?
};


struct GObject
{
	GString ObjName;
	int vertexCount; // 0x04000000
	int indicesCount; // 0x02000000
	std::vector<GVertex> vertexList;
	std::vector<GIndex> indicesList;


	void read_binary(std::istream& ifs)
	{
		ObjName.read_binary(ifs);

		ifs.read((char*)&vertexCount, sizeof(int));
		ifs.read((char*)&indicesCount, sizeof(int));

		vertexList.resize(vertexCount);
		indicesList.resize(indicesCount);

		ifs.read((char*)&vertexList[0], sizeof(GVertex)*vertexCount);
		ifs.read((char*)&indicesList[0], sizeof(GIndex)*indicesCount);
	}


	void write_text(std::ostream& oss)
	{
		int ct = sprintf(__shared_buffer, 
			"Object %s\n"
			"VertexCount %d\n"
			"IndicesCount %d\n"
			"{\n"
			, ObjName.str.c_str()
			, vertexCount
			, indicesCount );
		oss.write(__shared_buffer, ct);
		for (int i = 0; i < vertexCount; ++i)
		{
			GVertex & v = vertexList[i];
			ct = sprintf(__shared_buffer, " v %10.5f %10.5f %10.5f %2.5f %2.5f %2.5f %x %2.5f %2.5f\n",
				v.Positon[0], v.Positon[1], v.Positon[2], v.Normal[0], v.Normal[1], v.Normal[2], v.Color, v.UV[0], v.UV[1]);
			oss.write(__shared_buffer, ct);
		}
		for (int i = 0; i < indicesCount; ++i)
		{
			GIndex & id = indicesList[i];
			ct = sprintf(__shared_buffer, " f %d %d %d %d %2.5f %2.5f %2.5f\n",
				id.unknow, id.Triangle[0], id.Triangle[1], id.Triangle[2], id.FaceNormal[0], id.FaceNormal[1], id.FaceNormal[2]);
			oss.write(__shared_buffer, ct);
		}
		oss.write("}\n\n", 3);
	}
};


struct GHeaderOption
{
	GString option1;
	char unknowChar1; // 00
	GString option2;
	int unknowInt1; // 0x0000803f
	int ObjIndex; 


	void read_binary(std::istream& ifs)
	{
		option1.read_binary(ifs);
		ifs.read(&unknowChar1, sizeof(char));
		option2.read_binary(ifs);
		ifs.read((char*)&unknowInt1, sizeof(int));
		ifs.read((char*)&ObjIndex, sizeof(int));
	}


	void write_text(std::ostream& oss)
	{
		int ct = sprintf(__shared_buffer,
			"Option\n"
			"{\n"
			" %s %d\n"
			" %s\n"
			" %x\n"
			" ObjectIndex %d\n"
			"}\n",
			option1.str.c_str(), (int)unknowChar1,
			option2.str.c_str(), unknowInt1,
			ObjIndex);
		oss.write(__shared_buffer, ct);
	}
};


struct GMDL_Header
{
	char magic_version[10];
	int textureCount; // ?
	std::vector<GString> textureList;
	int optionCount;
	int unknowInt1;
	std::vector<GHeaderOption> optionList;
	int unknowInt3; // 
	int unknowInt4; // 
	int unknowInt5; // 


	void read_binary(std::istream& ifs)
	{
		ifs.read(magic_version, 10);



		ifs.read((char*)&textureCount, sizeof(int));
		for (int i = 0; i < textureCount; ++i)
		{
			textureList.push_back(GString());
			textureList.back().read_binary(ifs);
		}
		ifs.read((char*)&optionCount, sizeof(int));
		ifs.read((char*)&unknowInt1, sizeof(int));
		for (int i = 0; i < optionCount; ++i)
		{
			optionList.push_back(GHeaderOption());
			optionList.back().read_binary(ifs);
		}
		ifs.read((char*)&unknowInt3, sizeof(int));
		ifs.read((char*)&unknowInt4, sizeof(int));
		ifs.read((char*)&unknowInt5, sizeof(int));
	}


	void write_text(std::ostream& oss)
	{
		oss.write(magic_version, 10);
		int ct = sprintf(__shared_buffer,
			"\nTextures\n"
			"{\n");
		oss.write(__shared_buffer, ct);
		for (int i = 0; i < textureCount; ++i)
		{
			ct = sprintf(__shared_buffer, " tex %s\n", textureList[i].str.c_str());
			oss.write(__shared_buffer, ct);
		}
		ct = sprintf(__shared_buffer, "}\n\n#? %x\n\n", unknowInt1);
		oss.write(__shared_buffer, ct);
		for (int i = 0; i < optionCount; ++i)
		{
			optionList[i].write_text(oss);
		}
		ct = sprintf(__shared_buffer, "\n#? %x\n", unknowInt3);
		oss.write(__shared_buffer, ct);
		ct = sprintf(__shared_buffer, "#? %x\n", unknowInt4);
		oss.write(__shared_buffer, ct);
		ct = sprintf(__shared_buffer, "#? %x\n\n", unknowInt5);
		oss.write(__shared_buffer, ct);
	}
};


struct GMDL_File
{
	GMDL_Header Header;
	std::vector<GObject> ObjectList;


	void read_binary(std::istream& ifs)
	{
		Header.read_binary(ifs);
		while ( ifs.peek() >= 0 )
		{
			ObjectList.push_back(GObject());
			ObjectList.back().read_binary(ifs);
		}
	}


	void read_binary(const std::string & path)
	{
		std::ifstream ifs(path.c_str(), std::ios::binary);
		if (!ifs.good())
		{
			return;
		}
		read_binary(ifs);
		ifs.close();
	}


	void write_text(std::ostream& oss)
	{
		Header.write_text(oss);
		for (int i = 0; i < ObjectList.size(); ++i)
		{
			ObjectList[i].write_text(oss);
		}
	}


	void write_text(const std::string& path)
	{
		std::ofstream ofs(path.c_str(), std::ios::trunc | std::ios::out);
		write_text(ofs);
	}
};


int  main(){
	GMDL_File gf;
	gf.read_binary("D:/sn23.gmb");
	gf.write_text("D:/1.txt");
	return 0;
}
