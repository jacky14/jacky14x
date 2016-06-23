package com.jacky.engine.viewnode;

import com.jacky.engine.JxbFileManger;
import com.jacky.engine.JxbbFileManger;
import com.jacky.engine.JxbbInfo;
import com.jacky.engine.TextureManger;
import com.jacky.engine.buffer.BufferUtil;
import com.jacky.engine.math.Matrix4f;
import com.jacky.start.AppDelegate;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Node extends   DrawNode{
	public  int  drawVerCount;//���ƶ���ĸ���
	
	public int vbo ;
	public int tcbo ;
	public int vibo ;
	//������������
	public int bonidx;

	//jbx�ļ�����
	public String jbxfile;
	//jxbb�ļ�����
	public String jxbbfile;
	public int textureId;//��������
	public String texname;//��������
	public Matrix4f transfe ;
	
	public int texmode;
	public int drawMode = -1;


	//֡�������
	public float [][] maxtriS;
	//֡��ʱ���󻺴�
	public FloatBuffer tmpBuffMax;
	/**
	 * �Ƿ��ǰ�͸������
	 */
	public boolean isTranslucence = false;



	public void rotate(float a, float x, float y, float z){
		if(transfe == null){
			transfe = new Matrix4f();
		}
		transfe.rotate(a, x, y, z);
	}
	public  void scale( float x, float y, float z){
		if(transfe == null){
			transfe = new Matrix4f();
		}
		transfe.scaleM(x, y, z);
	}
	
	public void translate(float x,float y,float z){
		if(transfe == null){
			transfe = new Matrix4f();
		}
		transfe.translate(x, y, z);
	}
	public void setTexMode(int mode){
		texmode = mode;
	}

	private void initData(float[] ver ,float[] tex, String path){
		vbo = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(ver));
		tcbo =  AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(tex));
		textureId = TextureManger.getTexture(path);
		texname = path;
	}
	public Node(){

	}

	private Node(float[] ver ,float[] tex, String path){
		initData(ver ,tex,path);
		this.drawVerCount = ver.length / 3;
		drawMode = ARRAY_MODE;

	}
	public Node(float[] ver ,float[] tex,short [] verindex,  String path){
		initData(ver ,tex,path);
		vibo = AppDelegate.share().graphicsTool.bindIndexBuffer(BufferUtil.createShortBuff(verindex));
		this.drawVerCount = verindex.length;
		drawMode = ELEMENT_MODE;
	}
	public Node(float[] ver ,float[] tex,short [] verindex,float []boneidx ,float [][] martixs, String path){
		initData(ver ,tex,path);
		vibo = AppDelegate.share().graphicsTool.bindIndexBuffer(BufferUtil.createShortBuff(verindex));
		bonidx = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(boneidx));
		this.drawVerCount = verindex.length;
		maxtriS = martixs;
		tmpBuffMax = ByteBuffer.allocateDirect(martixs[0].length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		BufferUtil.setFloatBuffer(martixs[200],tmpBuffMax);


		drawMode = ELEMENT_BONE_MODE;
	}
	public void rinit(String jxb,String Texfile){
		texname = Texfile;
		jbxfile = jxb;

		textureId = TextureManger.getTexture(Texfile);
		int [] idxs = JxbFileManger.getidxFromfname(jxb);
		vbo = idxs[0];
		tcbo = idxs[1] ;
		vibo = idxs[2] ;
		this.drawVerCount = idxs[4];
		drawMode = ELEMENT_MODE;
	}
	public Node(String jxb,String Texfile){
		rinit(jxb,Texfile);
	}
	public Node(String jxb,String Texfile,String jxbb){

		texname = Texfile;
		jbxfile = jxb;
		textureId = TextureManger.getTexture(Texfile);
		int [] idxs = JxbFileManger.getidxFromfname(jxb);
		vbo = idxs[0];
		tcbo = idxs[1] ;
		vibo = idxs[2] ;
		bonidx =idxs[3] ;
		this.drawVerCount = idxs[4];

		jxbbfile = jxbb;
		JxbbInfo ji = JxbbFileManger.getInfo(jxbb);
		maxtriS = ji.data;
		tmpBuffMax=ByteBuffer.allocateDirect(maxtriS[0].length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		drawMode = ELEMENT_BONE_MODE;
	}

	/**
	 * ��������ģ�͵�ǰ��ʾ֡����
	 */
	public int frameidx = 0;
	@Override
	public void draw(){
		if(drawMode == ELEMENT_BONE_MODE){
			AppDelegate.share().graphicsTool.selectShader(GraphicsTool.bone_shader);
		}else{
			AppDelegate.share().graphicsTool.selectShader(GraphicsTool.def_shader);
		}


		AppDelegate.share().graphicsTool.setTexMode(texmode);

		if(transfe!=null){
			FloatBuffer allT = AppDelegate.share().camera.getCameraM(transfe);
			AppDelegate.share().graphicsTool.setCamera(allT);
		}


		if(isTranslucence){
			AppDelegate.share().graphicsTool.setBlend(true);
			AppDelegate.share().graphicsTool.DepthMask(false);

		}

		if(drawMode == ARRAY_MODE){
			AppDelegate.share().graphicsTool.draw(vbo,tcbo,textureId,drawVerCount);
		}else if(drawMode == ELEMENT_MODE){
			AppDelegate.share().graphicsTool.drawIndex(vbo, tcbo, vibo, textureId, drawVerCount);
		}else if(drawMode == ELEMENT_BONE_MODE){
			/*frameidx++;
			if(frameidx>=maxtriS.length-20){
				frameidx = 0;
			}
			*/BufferUtil.setFloatBuffer(maxtriS[frameidx],tmpBuffMax);
			AppDelegate.share().graphicsTool.drawIndexBone(vbo, tcbo, vibo, textureId, drawVerCount,bonidx,tmpBuffMax);
		}

		if(isTranslucence){
			AppDelegate.share().graphicsTool.setBlend(false);
			AppDelegate.share().graphicsTool.DepthMask(true);
		}


		if(transfe!=null){
			FloatBuffer allT = AppDelegate.share().camera.getCameraM();
			AppDelegate.share().graphicsTool.setCamera(allT);
		}
		
	}

	@Override
	public void update(){

	}

	@Override
	public void release(){
		TextureManger.clearTex(texname);
		JxbFileManger.clear(jbxfile);
		if(drawMode == ELEMENT_BONE_MODE){
			JxbbFileManger.clear(jxbbfile);
		}
	}








	@Override
	public boolean isInRect(float x, float y){
		return false;
	}


}
