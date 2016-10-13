package com.jacky.android.opengl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.jacky.android.JackyActivity;
import com.jacky.android.opengl.shaderUtil.Shader;
import com.jacky.android.opengl.shaderUtil.ShaderBone;
import com.jacky.android.opengl.shaderUtil.ShaderParticle;
import com.jacky.engine.viewnode.GraphicsTool;
import com.jacky.engine.viewnode.Node;

import static android.opengl.GLES20.*;

public class AndroidGraphics implements GraphicsTool{

	@Override
	public void drawIndex(int vbo, int tcbo, int vibo,int textureId, int count){
		

		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(Shader.share().PositionHandle, 3, GL_FLOAT, false, 12, 0);
		glEnableVertexAttribArray(Shader.share().PositionHandle);
		
		
		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glVertexAttribPointer(Shader.share().TexCoorHandle, 2, GL_FLOAT, false, 8, 0);
		glEnableVertexAttribArray(Shader.share().TexCoorHandle);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vibo);


		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureId);
	     //绘制三角形
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_SHORT, 0);
	}

	@Override
	public void drawIndexBone(int vbo, int tcbo, int vibo, int textureId, int count, int boneidx, FloatBuffer martixs){
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(ShaderBone.share().PositionHandle, 3, GL_FLOAT, false, 12, 0);
		glEnableVertexAttribArray(ShaderBone.share().PositionHandle);


		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glVertexAttribPointer(ShaderBone.share().TexCoorHandle, 2, GL_FLOAT, false, 8, 0);
		glEnableVertexAttribArray(ShaderBone.share().TexCoorHandle);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vibo);

		//传递骨骼索引缓存id
		glBindBuffer(GL_ARRAY_BUFFER, boneidx);
		glVertexAttribPointer(ShaderBone.share().verBone_id,1, GL_FLOAT, false, 4, 0);
		glEnableVertexAttribArray(ShaderBone.share().verBone_id);

		//传递当前帧骨骼动画列表
		glUniform4fv(ShaderBone.share().bone_marixid,martixs.remaining() >> 2,martixs);
		//glUniform4(ShaderBone.share().bone_marixid,martixs);
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureId);
		//绘制三角形
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_SHORT, 0);
	}

	@Override
	public void drawIndexParticle(int vbo, int tcbo, int textureId, int count, int bindidx, FloatBuffer move, FloatBuffer rotate) {





		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(ShaderParticle.share().PositionHandle, 3, GL_FLOAT, false, 12, 0);
		glEnableVertexAttribArray(ShaderParticle.share().PositionHandle);


		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glVertexAttribPointer(ShaderParticle.share().TexCoorHandle, 2, GL_FLOAT, false, 8, 0);
		glEnableVertexAttribArray(ShaderParticle.share().TexCoorHandle);



		//传递粒子绑定位置缓存id
		glBindBuffer(GL_ARRAY_BUFFER, bindidx);
		glVertexAttribPointer(ShaderParticle.share()._idx,1, GL_FLOAT, false,4, 0);
		glEnableVertexAttribArray(ShaderParticle.share()._idx);


		glUniformMatrix4fv(ShaderParticle.share().rotatleM, rotate.remaining() >> 4, false, rotate);
		//传递当前帧粒子空间坐标位移信息
		glUniform3fv(ShaderParticle.share().point_move,move.remaining() /3 ,move);


		//绑定纹理
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureId);

		glDrawArrays(GL_TRIANGLES, 0, count);


	}

	@Override
	public int bindIndexBuffer(ShortBuffer bf){
		int[] temp = new int[1];
		glGenBuffers(1, temp, 0);
		int vex = temp[0];
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vex);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, bf.remaining() << 1,bf,GL_STATIC_DRAW);
		return vex;
	}
	
	
	@Override
	public int bindBuffer(FloatBuffer fb){
		int[] temp = new int[1];
		glGenBuffers(1, temp, 0);
		int vex = temp[0];
		glBindBuffer(GL_ARRAY_BUFFER, vex);
		glBufferData(GL_ARRAY_BUFFER, fb.remaining() << 2,fb,GL_STATIC_DRAW);
		return vex;
	}

	@Override
	public void updateAllCamera(FloatBuffer camera){
		glUseProgram(Shader.share().program);
		glUniformMatrix4fv(Shader.share().MVPMatrixHandle, camera.remaining() >> 4, false, camera);

		glUseProgram(ShaderBone.share().program);
		glUniformMatrix4fv(ShaderBone.share().MVPMatrixHandle, camera.remaining() >> 4, false, camera);

		glUseProgram(ShaderParticle.share().program);
		glUniformMatrix4fv(ShaderParticle.share().MVPMatrixHandle, camera.remaining() >> 4, false, camera);

		switch (shadermodebak){
			case def_shader:
				glUseProgram(Shader.share().program);
				break;
			case bone_shader:
				glUseProgram(ShaderBone.share().program);
				break;
			case particle_shader:
				glUseProgram(ShaderParticle.share().program);
				break;
		}
	}
	@Override
	public void setCamera(FloatBuffer camera){
		// TODO Auto-generated method stub
		//glUniformMatrix4fv(Shader.MVPMatrixHandle, camera.remaining() >> 4, false, camera);

		switch (shadermodebak){
			case def_shader:
				glUniformMatrix4fv(Shader.share().MVPMatrixHandle, camera.remaining() >> 4, false, camera);
				break;
			case bone_shader:
				glUniformMatrix4fv(ShaderBone.share().MVPMatrixHandle, camera.remaining() >> 4, false, camera);
				break;
			case particle_shader:
				glUniformMatrix4fv(ShaderParticle.share().MVPMatrixHandle,camera.remaining() >> 4,false,camera);
				break;
		}


	}
	
	
	@Override
	public void draw(int vbo ,int tcbo,int textureId,int count){
		// TODO Auto-generated method stub
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(Shader.share().PositionHandle, 3, GL_FLOAT, false, 12, 0);
		glEnableVertexAttribArray(Shader.share().PositionHandle);
		
		
		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glVertexAttribPointer(Shader.share().TexCoorHandle, 2, GL_FLOAT, false, 8, 0);
		glEnableVertexAttribArray(Shader.share().TexCoorHandle);



		glBindTexture(GL_TEXTURE_2D, textureId);
	     //绘制三角形
	     glDrawArrays(GL_TRIANGLES, 0, count); 
	}
	private Bitmap bitmapTmp;
	@Override
	public int loadTex(String texname){
		int textureId = 0;
		//纹理图的宽度高度信息
		int w = 0;
		int h = 0;
		try {
			InputStream is = JackyActivity.assetManager.open(texname);
			bitmapTmp = BitmapFactory.decodeStream(is);
			w = bitmapTmp.getWidth();
			h = bitmapTmp.getHeight();
			//生成纹理ID
			int[] textures = new int[1];
			glGenTextures
			(
					1,          //产生的纹理id的数量
					textures,   //纹理id的数组
					0           //偏移量
			);    
			textureId = textures[0]; 
			BindTexId(textureId);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("加载纹理“"+texname+"”时发生错误！！！");
		}
		
		return textureId;
	}
	
	private  void BindTexId(int textureId){
		glBindTexture(GL_TEXTURE_2D, textureId);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,GL_LINEAR);


		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,GL_REPEAT);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,GL_REPEAT);
		//实际加载纹理
        GLUtils.texImage2D
        (
        		GL_TEXTURE_2D,   //纹理类型，在OpenGL ES中必须为GL10.GL_TEXTURE_2D
        		0, 					  //纹理的层次，0表示基本图像层，可以理解为直接贴图
        		bitmapTmp, 			  //纹理图像
        		0					  //纹理边框尺寸
        );
        bitmapTmp.recycle(); 		  //纹理加载成功后释放图片
	}

	@Override
	public void DepthMask(boolean falg){
		// TODO Auto-generated method stub
		glDepthMask(falg);

	}

	@Override
	public void setTexMode(int mode){
		// TODO Auto-generated method stub
		if(Node.TEX_MODE1==mode){
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		}else if(Node.TEX_MODE2==mode){
			glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		}
	}

	@Override
	public void setBlend(boolean falg){
		if(falg){
			glEnable(GL_BLEND);
		}else{
			glDisable(GL_BLEND);
		}
	}

	@Override
	public void cleanTexture(int index) {
		glDeleteTextures(1,new int[]{index},0);
	}

	@Override
	public void cleanBuff(int index) {
		glDeleteBuffers(1,new int[]{index},0);
	}


	/**
	 * 记录当前请求渲染的模型的渲染模式
	 */
	public static int shadermodebak = 0;
	/**
	 * 选择使用的着色程序模式0：普通着色程序       1：骨骼动画着色程序
	 *
	 * @param shadermode
	 */
	@Override
	public void selectShader(int shadermode){
		shadermodebak = shadermode;
		switch (shadermode){
			case def_shader:
				glUseProgram(Shader.share().program);
				break;
			case bone_shader:
				glUseProgram(ShaderBone.share().program);
				break;
			case particle_shader:
				glUseProgram(ShaderParticle.share().program);
				break;
		}
	}


}
