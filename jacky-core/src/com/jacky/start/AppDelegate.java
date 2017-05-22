package com.jacky.start;

import com.jacky.engine.local.LocalUtile;
import com.jacky.engine.pay.CorePay;
import com.jacky.engine.resource.LocalFile;
import com.jacky.engine.savedata.UserPreferences;
import com.jacky.engine.sound.SoundManger;
import com.jacky.engine.viewnode.Camera;
import com.jacky.engine.viewnode.Camera2D;
import com.jacky.engine.viewnode.GraphicsTool;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.engine.viewnode.Scene;


/**
 * 只有在更新相机参数时传递新的相机矩阵到opengl，相机未发生变化时，不需要每一帧重新计算和传递相机矩阵
 */
public class AppDelegate {
	// 单例模式
	private static AppDelegate appDelegate;
	
	public static AppDelegate share(){
		if(appDelegate == null ){
			appDelegate = new AppDelegate();
		}
		return appDelegate;
	}
	
	//展示窗口的宽度和高度
	public  int view_width=0,view_high=0;

	public int half_vwidth = -1,half_vhigh = -1;


	public GraphicsTool graphicsTool;

	public LocalFile localFile;

	public UserPreferences userpre;

	public CorePay cp;

	public LocalUtile lu;

	//当前场景
	public Scene currentScreen;
		
	//即将切换的场景
	private Scene nextScreen ;
	public Camera camera;
	public Camera2D camera2D;

	public int farme = 0 ;
	private long sec =  System.currentTimeMillis();
	
	public void runGame(){
		
		long start = System.currentTimeMillis();
		//运行游戏
		if(currentScreen!=null&&currentScreen.isInitFinish()){
			currentScreen.runFrame();
		}
		long sleep = 12 - (System.currentTimeMillis() - start);
		if(sleep > 0){
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e){
					e.printStackTrace();
				}
		}
		farme ++;
		if(System.currentTimeMillis() - sec > 1000){
			//统计fps（每秒钟绘制帧数）
			//lu.showMsg("fps:" + farme);
			System.out.println("fps:" + farme);
			//showfps = true;
			//tmpfpsnum = farme;
			sec = System.currentTimeMillis();
			farme = 0;
		}
	}
	//public boolean showfps = false;
	//public int tmpfpsnum = 0;


	//游戏设计宽高
	public static float design_width = 1136;
	public static float design_high = 640;

	public static int camera_len = 90;
	
	public Scene first;
	public void init(){
		camera2D = new Camera2D();
		//MainMenuScene testmain = new MainMenuScene();
		//GameScene gs = new GameScene();
		if(first==null){
			System.out.println("请设置第一个场景！！");
		}
		replaceScene(first);
	}
	public void replaceScene(Scene scene){
		if(currentScreen!=null){
			nextScreen = scene;
			currentScreen.exit();
		}else{
			currentScreen = scene;
			currentScreen.init();
		}
	}
	
	public void nextSceneCallBack(){
		if(nextScreen!=null){
			currentScreen = nextScreen;
			nextScreen = null;
			currentScreen.init();
		}
		
	}

	//获得屏幕一半高度宽度
	public final int getHW(){
		if(half_vwidth==-1){
			half_vwidth = (int)(design_width*0.5f);
		}
		return half_vwidth;
	}
	public final int getHH(){
		if(half_vhigh==-1){
			half_vhigh = (int)(design_high*0.5f);
		}
		return half_vhigh;
	}

	//程序结束时清理工作
	public void clearApp(){
		//这里清理了游戏模块的缓存
		graphicsTool.cleanBuff(Node2D.publictexidx);
	}
	/**
	 * 应用进入后台运行
	 */
	public void applicationDidEnterBackground(){
		SoundManger.share().pauseall();
	}
	/**
	 * 游戏进入前端运行
	 */
	public void applicationWillEnterForeground(){
		SoundManger.share().restart();
	}
	
	

}
