package com.jacky.engine.viewnode;


public class Sprite extends Node{


	public Sprite(float[] ver ,float[] tex,short [] verindex,float []boneidx ,float [][] martixs, String path){
		super(ver, tex, verindex,boneidx,martixs,path);
	}
	public Sprite(float[] ver , float[] tex, short [] vindex, String path) {
		super(ver, tex, vindex,path);
		// TODO Auto-generated constructor stub
	}
	public Sprite(String jxb,String Texfile){
		super(jxb,Texfile);
	}
	public Sprite(String jxb,String Texfile,String jxbb){
		super(jxb,Texfile,jxbb);
	}
	/*private Sprite(float[] ver, float[] tex, String path) {
		//super(ver, tex, path);

	}*/

}
