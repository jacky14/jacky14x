package com.jacky.desktop.opengl;


import com.jacky.desktop.opengl.shaderUtil.Shader;
import com.jacky.desktop.opengl.shaderUtil.ShaderBone;
import com.jacky.desktop.opengl.shaderUtil.ShaderParticle;
import com.jacky.desktop.util.IOutil;
import com.jacky.engine.buffer.BufferUtil;
import com.jacky.engine.viewnode.GraphicsTool;
import com.jacky.engine.viewnode.Node;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.opengl.GL20.glUseProgram;

public class DesktopGraphics implements GraphicsTool{
	
	
	@Override
	public int bindIndexBuffer(ShortBuffer bf){
		int IBO = glGenBuffers();
		// This time however we are defining our buffer
		// as a GL_ELEMENT_ARRAY_BUFFER instead of our
		// normal GL_ARRAY_BUFFER
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
		// We are passing in a byteBuffer object storing all our indices here
		// instead
		// of a floatbuffer as we had above.
		glBufferData(GL_ELEMENT_ARRAY_BUFFER,bf, GL_STATIC_DRAW);

		return IBO;
	}

	@Override
	public int bindBuffer(FloatBuffer fb) {
		int vex = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vex);
		glBufferData(GL_ARRAY_BUFFER,fb,GL_STATIC_DRAW);
		return vex;
	}

	@Override
	public void updateAllCamera(FloatBuffer camera){
		glUseProgram(Shader.share().program);
		glUniformMatrix4(Shader.share().MVPMatrixHandle, false,camera);

		glUseProgram(ShaderBone.share().program);
		glUniformMatrix4(ShaderBone.share().MVPMatrixHandle, false,camera);

		glUseProgram(ShaderParticle.share().program);
		glUniformMatrix4(ShaderParticle.share().MVPMatrixHandle,false,camera);


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
		//glUniformMatrix4(Shader.share().MVPMatrixHandle, false,camera);

		switch (shadermodebak){
			case def_shader:
				glUniformMatrix4(Shader.share().MVPMatrixHandle, false,camera);
				break;
			case bone_shader:
				glUniformMatrix4(ShaderBone.share().MVPMatrixHandle, false,camera);
				break;
			case particle_shader:
				glUniformMatrix4(ShaderParticle.share().MVPMatrixHandle,false,camera);
				break;

		}
		/*testa+=testaoffset;
		if(testa>1||testa<0){
			testaoffset = -testaoffset;
		}

		float[] ff = new float []{
			100,0,0,0,
			100,0,0,0,
			100,0,0,0,
			100,0,testa,0
		};
		FloatBuffer fbb = BufferUtil.createFloatBuffer(ff);
		glUniform4(Shader.Bones_Handle,fbb);*/

	}
	@Override
	public void drawIndex(int vbo, int tcbo, int vibo, int textureId, int count) {


		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(Shader.share().PositionHandle, 3, GL_FLOAT, false, 12, 0);
		glEnableVertexAttribArray(Shader.share().PositionHandle);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vibo);


		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glVertexAttribPointer(Shader.share().TexCoorHandle, 2, GL_FLOAT, false, 8, 0);
		glEnableVertexAttribArray(Shader.share().TexCoorHandle);




		//绑定纹理
		glBindTexture(GL_TEXTURE_2D, textureId);


		
		//绘制三角形
	    glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_SHORT, 0);
	}

	@Override
	public void drawIndexBone(int vbo, int tcbo, int vibo, int textureId, int count, int boneidx, FloatBuffer martixs){
		//传递顶点坐标缓存列表id
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(ShaderBone.share().PositionHandle, 3, GL_FLOAT, false, 12, 0);
		glEnableVertexAttribArray(ShaderBone.share().PositionHandle);
		//传递顶点索引缓存id
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vibo);

		//传递纹理坐标列表id
		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glVertexAttribPointer(ShaderBone.share().TexCoorHandle, 2, GL_FLOAT, false, 8, 0);
		glEnableVertexAttribArray(ShaderBone.share().TexCoorHandle);

		//传递骨骼索引缓存id
		glBindBuffer(GL_ARRAY_BUFFER, boneidx);
		glVertexAttribPointer(ShaderBone.share().verBone_id,1, GL_FLOAT, false, 4, 0);
		glEnableVertexAttribArray(ShaderBone.share().verBone_id);

		//传递当前帧骨骼动画列表
		glUniform4(ShaderBone.share().bone_marixid,martixs);



		//绑定纹理
		glBindTexture(GL_TEXTURE_2D, textureId);

		//绘制三角形
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_SHORT, 0);
	}

	@Override
	public void drawIndexParticle(int vbo, int tcbo, int textureId, int count, int bindidx, FloatBuffer move,FloatBuffer rotate){



		//传递顶点坐标缓存列表id
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(ShaderParticle.share().PositionHandle, 3, GL_FLOAT, false, 12, 0);
		glEnableVertexAttribArray(ShaderParticle.share().PositionHandle);


		//传递纹理坐标列表id
		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glVertexAttribPointer(ShaderParticle.share().TexCoorHandle, 2, GL_FLOAT, false, 8, 0);
		glEnableVertexAttribArray(ShaderParticle.share().TexCoorHandle);

		//传递粒子绑定位置缓存id
		glBindBuffer(GL_ARRAY_BUFFER, bindidx);
		glVertexAttribPointer(ShaderParticle.share()._idx,1, GL_FLOAT, false,4, 0);
		glEnableVertexAttribArray(ShaderParticle.share()._idx);


		glUniformMatrix4(ShaderParticle.share().rotatleM,false,rotate);

		//传递当前帧粒子空间坐标位移信息
		glUniform3(ShaderParticle.share().point_move,move);

		//绑定纹理
		glBindTexture(GL_TEXTURE_2D, textureId);

		glDrawArrays(GL_TRIANGLES, 0, count);
	}

	@Override
	public void draw(int vbo ,int tcbo,int textureId,int count){
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(Shader.share().PositionHandle, 3, GL_FLOAT, false, 12, 0);
		glEnableVertexAttribArray(Shader.share().PositionHandle);
		
		glBindBuffer(GL_ARRAY_BUFFER, tcbo);
		glVertexAttribPointer(Shader.share().TexCoorHandle, 2, GL_FLOAT, false, 8, 0);
		glEnableVertexAttribArray(Shader.share().TexCoorHandle);
		 //绑定纹理
	     glBindTexture(GL_TEXTURE_2D, textureId);
		 //绘制三角形
	     glDrawArrays(GL_TRIANGLES, 0, count);
	}

	@Override
	public int loadTex(String path){


		int[] pixels = null;
		int width = 0, height = 0;
		try{


			String fn = IOutil.getAssetsName(path);


			BufferedImage image = ImageIO.read(new File(fn));


			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0,width, height, pixels, 0, width);
		} catch (IOException e){
			e.printStackTrace();
			System.err.println("加载纹理“"+path+"”时发生错误！！！");
		}
		
		int[] data = new int[width * height];
		for(int i = 0; i < width * height; i++){
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}

		int result = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, result);
	
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);



		//glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,GL20.GL_CLAMP_TO_EDGE);
		//glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,GL_CLAMP_TO_EDGE);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtil.createIntBuffer(data));

		return result;
	}

	@Override
	public void DepthMask(boolean falg){
		// TODO Auto-generated method stub
		glDepthMask(falg);
	}
	//纹理图渲染方式 TEX_MODE1 默认渲染方式GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA，一般普遍渲染方式，
		//				 TEX_MODE2 GL_ONE,GL_ONE  一般用于渲染高亮光效图，或jpg带有黑色边缘的图片
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
	public void cleanTexture(int index){
		glDeleteTextures(index);
	}

	@Override
	public void cleanBuff(int index){
		glDeleteBuffers(index);
	}


	/**
	 * 记录当前请求渲染的模型的渲染模式
	 */
	public static int shadermodebak = 0;
	/**
	 * 选择使用的着色程序模式0：普通着色程序       1：骨骼动画着色程序
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
