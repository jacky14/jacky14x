package com.jacky.android;

import static android.opengl.GLES10.GL_BLEND;
import static android.opengl.GLES10.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES10.GL_RENDERER;
import static android.opengl.GLES10.GL_SRC_ALPHA;
import static android.opengl.GLES10.GL_VERSION;
import static android.opengl.GLES10.glBlendFunc;
import static android.opengl.GLES10.glGetString;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_CULL_FACE;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_TEST;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDisable;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glViewport;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.jacky.android.opengl.AndroidGraphics;
import com.jacky.android.opengl.shaderUtil.Shader;
import com.jacky.android.opengl.shaderUtil.ShaderBone;
import com.jacky.android.opengl.shaderUtil.ShaderParticle;
import com.jacky.android.sound.AndroidSound;
import com.jacky.android.util.AndroidCorePay;
import com.jacky.android.util.LoadFile;
import com.jacky.android.util.LocalUtileAndroid;
import com.jacky.android.util.UserPreference;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.sound.SoundManger;
import com.jacky.start.AppDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainView extends GLSurfaceView implements GLSurfaceView.Renderer {


	public MainView(Context context) {
		super(context);
		this.setEGLContextClientVersion(2); // 设置使用OPENGL ES2.0
		setRenderer(this);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);// 设置渲染模式为主动渲染
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// view创建是调用

		ShaderParticle.share().init();
		ShaderBone.share().init();
		Shader.share().init();

		//初始化声音模块
		AndroidSound as = new AndroidSound();
		as.init();


		SoundManger.share().initInterface(as);//初始化本地音效模块
		AppDelegate.share().graphicsTool = new AndroidGraphics();//初始化本地绘图模块
		AppDelegate.share().localFile = new LoadFile();//初始化文件加载模块
		AppDelegate.share().userpre = new UserPreference();//初始化用户信息储存模块
		
		AppDelegate.share().lu = new LocalUtileAndroid();


		AppDelegate.share().init();
		
		System.out.println("OpenGL版本: " + glGetString(GL_VERSION));
		
		System.out.println("OpenGL厂商: " + glGetString(GL_RENDERER));
		// 设置屏幕背景色RGBA
		glClearColor(0, 0, 0, 1);
		// 打开深度检测
		glEnable(GL_DEPTH_TEST);
		// 关闭背面剪裁
		glDisable(GL_CULL_FACE);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height){
		// 视图大小改变时，（包含第一次创建后调用）
		// 设置视窗大小及位置
		glViewport(0, 0, width, height);
		// 计算GLSurfaceView的宽高比
		AppDelegate.share().view_high = height;
		AppDelegate.share().view_width = width;

		scx =  AppDelegate.design_width / width;
		scy =  AppDelegate.design_high / height;
	}

	@Override
	public void onDrawFrame(GL10 gl){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//传递当前帧的事件列表
		drive_isUse_eventA = !drive_isUse_eventA;
		if(drive_isUse_eventA){
			if(eventListB.size()>0){
				for(TouchEventJ tj : eventListB){
					AppDelegate.share().currentScreen.accept_event(tj);
				}
				eventListB.clear();
			}
		}else{
			if(eventListA.size()>0){
				for(TouchEventJ tj : eventListA){
					AppDelegate.share().currentScreen.accept_event(tj);
				}
				eventListA.clear();
			}
		}
		//处理游戏逻辑，渲染游戏
		AppDelegate.share().runGame();
		/*if(AppDelegate.share().showfps){
			JackyActivity.handler.sendEmptyMessage(0);
			AppDelegate.share().showfps = false;
		}*/

		
	}
	float scx = 0;
 	float scy = 0;
	/*public int touch_type = 0;
 	public float tmp_nX,tmp_nY;*/
	//public boolean HaveEvent = false;


    /**
	 * 2个事件监听列表，当执行到某一帧时游戏逻辑处理其中一个监听事件列表
	 * 而设备生成的事件就添加到另一个列表中，下一帧将替换
	 */
	public List<TouchEventJ> eventListA = new ArrayList();
	public List<TouchEventJ> eventListB = new ArrayList();

	/**
	 * 设备生成的事件正在使用事件列表A吗？//
	 */
	public boolean drive_isUse_eventA = true;
	@Override
	public boolean onTouchEvent(MotionEvent event){
		//一帧可以传递多个事件
		TouchEventJ tej =new TouchEventJ();
		switch (event.getAction()  & MotionEvent.ACTION_MASK){
			case MotionEvent.ACTION_DOWN:
				tej.event = TouchEventI.TOUCH_EVENT_START;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				tej.event = TouchEventI.TOUCH_EVENT_START;
				break;
			case MotionEvent.ACTION_MOVE:
				tej.event = TouchEventI.TOUCH_EVENT_MOVE;
				break;
			case MotionEvent.ACTION_UP:
				tej.event = TouchEventI.TOUCH_EVENT_END;
				break;
			case MotionEvent.ACTION_POINTER_UP:
				tej.event = TouchEventI.TOUCH_EVENT_END;
				break;
			default:
				return true;
		}

		int index = event.getActionIndex();// 触发该事件的索引号
		tej.tag_x = event.getX(index) * scx;
		tej.tag_y = event.getY(index) * scy;
		tej.uuid = event.getPointerId(index); // 触发该事件的id       MotionEvent.getActionIndex()

		//System.out.println("当前使用队列是A吗"+drive_isUse_eventA + "事件uuid："+tej.uuid + "事件类型："+tej.event);
		//System.out.println("队列eventListA的大小是"+eventListA.size() + "队列eventListB的大小是："+eventListB.size());

		addEvent(tej);
		return true;
	}
	public void addPayCallBack(int id){
		TouchEventJ tej =new TouchEventJ();
		tej.event = TouchEventI.PAY_CALLBACK;
		tej.payid = id;
		addEvent(tej);
	}

	public  void addEvent(TouchEventJ tej){
		if(drive_isUse_eventA){
			eventListA.add(tej);
		}else{
			eventListB.add(tej);
		}
	}




}
