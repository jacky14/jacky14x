package com.jacky.android.opengl.shaderUtil;


import com.jacky.engine.ShaderStrsource.StrSource;
public class Shader extends ShaderBase{

	public static Shader s;
	public static Shader share(){
		if(s==null){
			s = new Shader();
		}
		return s;
	}
	public  void init(){
		program = create(StrSource.default_vert, StrSource.default_frag);
		super.initBase();
	}
	


}
