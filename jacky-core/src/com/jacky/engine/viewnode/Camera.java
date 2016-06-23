package com.jacky.engine.viewnode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.jacky.engine.buffer.BufferUtil;
import com.jacky.engine.math.Matrix4f;
import com.jacky.engine.math.Vector4f;
import com.jacky.start.AppDelegate;
import com.meteor.gm.element.Map;


public class Camera {

	//摄像机矩阵  视景体矩阵参数 
	public float [] data ;
	
	//最终摄像机矩阵
	//public  Matrix4f camera   = new Matrix4f();

	/*private Matrix4f transform;
	private boolean isTran = false;*/
	
	
	private Matrix4f LookAtM  = new Matrix4f();
	
	private Matrix4f frustumM = new Matrix4f();
	
	/*public void setTranform(Matrix4f transform){
		
		this.transform = transform;
		
		isTran = true;
	}*/
	
	public Camera(float [] args){
		data = args;
		frustumM.frustum(data[left], data[right], data[bottom], data[top], data[near], data[far]);
	}

	public float angle ;
	Vector4f v =new Vector4f(0,0,0);
	Matrix4f m =new Matrix4f();
	public void rol(boolean falg ){
		if(!falg){
			angle = -3;
		}else {
			angle = 3;
		}
		
		 dx = (data[centerX] - data[eyeX]) ;
		 dy = (data[centerY] - data[eyeY]) ;
		 dz =( data[centerZ] - data[eyeZ]) ;
		
		v.x = dx;
		v.y = dy;
		v.z = dz;

		m.rotate(angle, 0,1, 0);
		m.multiplyMV(v);
		
		data[centerX] = data[eyeX] + v.changed_x;
		data[centerY] = data[eyeY] + v.changed_y;
		data[centerZ] = data[eyeZ] + v.changed_z;
		
	}
	float sc ;
	float dx ;
	float dy ;
	float dz ;
	public void test(boolean falg ){

		if(falg){
			sc = 10f;
		}else{
			sc =-10f;
		}
		
	
		 dx = (data[centerX] - data[eyeX]) * sc;
		 dy = (data[centerY] - data[eyeY]) * sc;
		 dz =( data[centerZ] - data[eyeZ]) * sc;
		
		
		
		data[eyeX] += dx;
		data[eyeY] += dy;
		data[eyeZ] += dz;
			
		data[centerX] +=dx;
		data[centerY] +=dy;
		data[centerZ] +=dz;
			
		System.out.println("eye poist" +data[eyeX] + ","+data[eyeY]+"," + data[eyeZ]  );
		System.out.println("cen poist" +data[centerX] + ","+data[centerY]+"," + data[centerZ]  );
		
	}

	public void view(int i){

		sc = 3f;
		switch (i){
			case 0:
				data[eyeX]+=sc;
				data[centerX] +=sc;
				break;
			case 1:
				data[eyeX]-=sc;
				data[centerX] -=sc;
				break;
			case 2:
				data[eyeY] += sc;
				data[centerY] +=sc;
				break;
			case 3:
				data[eyeY]-= sc;
				data[centerY] -=sc;
				break;
			case 4:
				data[eyeZ]-= sc;
				data[centerZ] -=sc;
				break;
			case 5:
				data[eyeZ]+= sc;
				data[centerZ] +=sc;
				break;
		}
		//System.out.println("eye poist" +data[eyeX] + ","+data[eyeY]+"," + data[eyeZ]  );

		//设置摄像机
		AppDelegate.share().graphicsTool.updateAllCamera(getCameraM());
	}

	/**
	 * 更新相机的xyz坐标
	 * @param x
	 * @param y
     */
	public void setContentXYPoint(float x,float y){


		data[eyeX]=( data[eyeX]  - data[centerX] ) + x ;

		data[eyeY]=( data[eyeY]  - data[centerY] ) + y ;

		data[centerX] =x;
		data[centerY] =y;

		//设置摄像机
		AppDelegate.share().graphicsTool.updateAllCamera(getCameraM());



	}

	/**
	 * 摄像机旋转 flag不同旋转方向（angle 在这里被用作记录当前相机的旋转角度）
	 * @param flag
     */
	public void role_rotate(boolean flag){
		if(flag){
			angle+=0.052359;
		}else{
			angle-=0.052359;
		}

		data[eyeX] = (float) Math.sin(angle) * AppDelegate.camera_len + data[centerX];
		data[eyeY] = (float) Math.cos(angle) * AppDelegate.camera_len + data[centerY];


		//设置摄像机
		AppDelegate.share().graphicsTool.updateAllCamera(getCameraM());
	}

	public static void main (String []arg){
		float sinA = (float)Math.sin(90);
		float cosA = (float)Math.cos(90);

		System.out.println(sinA);

		System.out.println(cosA);

		//System.out.println(Math.PI/180*3);
	}

	FloatBuffer tmpCM = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();
	public FloatBuffer getCameraM(){
		LookAtM.setLookAt( data[eyeX], data[eyeY], data[eyeZ], data[centerX], data[centerY], data[centerZ], data[upX], data[upY], data[upZ]);
		
		Matrix4f temp =  LookAtM.clone();

		temp.multiplyMM(frustumM);
		BufferUtil.setFloatBuffer(temp.matrix,tmpCM);
		return tmpCM;
	}
	public FloatBuffer getCameraM(Matrix4f t){
		LookAtM.setLookAt( data[eyeX], data[eyeY], data[eyeZ], data[centerX], data[centerY], data[centerZ], data[upX], data[upY], data[upZ]);
		
		Matrix4f temp = t.clone();
		
		temp.multiplyMM(LookAtM);
		temp.multiplyMM(frustumM);

		BufferUtil.setFloatBuffer(temp.matrix,tmpCM);
		return tmpCM;
	}


	//矩阵计算旋转90度后向量
	float [] xypointtmp = new float[2];
	/**
	 * 返回当前相机向前方固定距离的xy增量
	 * @return
     */
	public float[] xiangqian(float jd){
		dx = data[centerX] -data[eyeX] ;
		dy = data[centerY] -data[eyeY] ;

		float sinhd = (float)Math.sin(jd);
		float coshd = (float)Math.cos(jd);


		float tmpx = dx*coshd+dy*sinhd;
		float tmpy = dx*-sinhd+dy*coshd;

		/*if(EventID.MOVE_QIAN == i){

		}else if(EventID.MOVE_HOU == i){
			dx = -dx ;
			dy = -dy ;
		}else if(EventID.MOVE_ZUO == i){
			float tmpx = -dy;
			float tmpy = dx;
			dx = tmpx;
			dy = tmpy;
		}else if(EventID.MOVE_YOU == i){
			float tmpx =dy;
			float tmpy =-dx;
			dx = tmpx;
			dy = tmpy;
		}*/
		xypointtmp[0] = tmpx*0.03f;
		xypointtmp[1] = tmpy*0.03f;

		return xypointtmp;
	}
	
	//data 下标索引
	//摄像机参数
	public final static byte eyeX = 0 ;
	public final static byte eyeY = eyeX + 1 ;
	public final static byte eyeZ = eyeY + 1 ;
	
	public final static byte centerX = eyeZ + 1 ;
	public final static byte centerY = centerX + 1 ;
	public final static byte centerZ = centerY + 1 ;
	
	public final static byte upX = centerZ + 1 ;
	public final static byte upY = upX + 1 ;
	public final static byte upZ = upY + 1 ;
	
	//视景体参数
	public final static byte left = upZ + 1 ;
	public final static byte right = left + 1 ;
	
	public final static byte bottom = right + 1 ;
	public final static byte top = bottom + 1 ;
	
	public final static byte near = top + 1 ;
	public final static byte far = near + 1 ;
	
	public final static byte dataNum = far + 1 ;
}
