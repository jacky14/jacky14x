package com.jacky.engine.resource.meteorFile;


import java.util.HashMap;
import java.util.Map;

public class GMBObj {

	public String ObjName;
	int vertexCount; // 0x04000000
	int indicesCount; // 0x02000000
	
	public float [] Positon;
	float [] Normal;
	int[] Color;
	public float [] UV;
	
	public int[] unknow;
	
	
	public int []Triangle;
	
	float FaceNormal[]; // ?

	//��¼��3���ζ����Ӧ���¶���ֵ  ����3���ζ���,�������ζ��㡷
	//public Map<Integer,Integer> newtriangle = new HashMap<Integer,Integer>();

}
