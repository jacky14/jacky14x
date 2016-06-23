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
	 * ����������ɫ�������
	 * @param camera
     */
	public void updateAllCamera(FloatBuffer camera);
	
	/**
	 * ������Ȼ������Ƿ���ֻ��״̬ 
	 */
	public void DepthMask(boolean falg);
	
	
	public void setTexMode(int  mode );

	/**
	 * �Ƿ������
	 */
	public void setBlend(boolean falg);

	/**
	 * ���GPU���������
	 * @param index
     */
	public void cleanTexture(int index);

	/**
	 * ���GPU�����ݻ������
	 * @param index
     */
	public void cleanBuff(int index);

	/**
	 * ѡ��ʹ�õ���ɫ����ģʽ0����ͨ��ɫ����  1������������ɫ����   2:������ɫ����
	 * @param shadermode
     */
	public void selectShader(int shadermode);
	int def_shader = 0;
	int bone_shader = def_shader + 1;
	int particle_shader = bone_shader + 1;

}
