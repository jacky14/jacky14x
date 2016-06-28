package com.jacky.engine.viewnode;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public interface GraphicsTool {
	
	
	public void draw(int vbo ,int tcbo,int textureId,int count);


	public void drawIndex(int vbo ,int tcbo,int vibo, int textureId,int count);
	public void drawIndexBone(int vbo ,int tcbo,int vibo, int textureId,int count,int boneidx,FloatBuffer martixs);
	public void drawIndexParticle(int vbo ,int tcbo, int textureId,int count,int bindidx,FloatBuffer move,FloatBuffer rotate);


	public int loadTex(String path);
	
	public int bindBuffer(FloatBuffer fb);

	
	public int bindIndexBuffer(ShortBuffer bf);
	
	
	public void setCamera(FloatBuffer camera);


	/**
	 * 更新所有着色程序相机
	 * @param camera
     */
	public void updateAllCamera(FloatBuffer camera);
	
	/**
	 * 设置深度缓冲区是否是只读状态 
	 */
	public void DepthMask(boolean falg);
	
	
	public void setTexMode(int  mode );

	/**
	 * 是否开启混合
	 */
	public void setBlend(boolean falg);

	/**
	 * 清空GPU中纹理对象
	 * @param index
     */
	public void cleanTexture(int index);

	/**
	 * 清空GPU中数据缓存对象
	 * @param index
     */
	public void cleanBuff(int index);

	/**
	 * 选择使用的着色程序模式0：普通着色程序  1：骨骼动画着色程序   2:粒子着色程序
	 * @param shadermode
     */
	public void selectShader(int shadermode);
	int def_shader = 0;
	int bone_shader = def_shader + 1;
	int particle_shader = bone_shader + 1;

}
