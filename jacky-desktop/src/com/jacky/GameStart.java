package com.jacky;

import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.GL_RENDERER;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGetError;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.GLFW.*;

import com.jacky.desktop.SoundDesktop;
import com.jacky.desktop.opengl.shaderUtil.ShaderBone;
import com.jacky.desktop.opengl.shaderUtil.ShaderParticle;
import com.jacky.desktop.util.DesktopPay;
import com.jacky.desktop.util.LocalUtilDestop;
import com.jacky.desktop.util.UserPreferences;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.sound.SoundManger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GLContext;

import com.jacky.desktop.Input.KeyListion;
import com.jacky.desktop.opengl.DesktopGraphics;
import com.jacky.desktop.opengl.shaderUtil.Shader;
import com.jacky.desktop.util.IOutil;
import com.jacky.start.AppDelegate;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;


public class GameStart implements Runnable{

	
	
	//初始化窗口 宽度高度和窗口位置
    public int width=850,high=480,x=100,y=100;
    
    private Thread thread;
    private long window;
   
    private KeyListion keyListion;
    
    public static boolean desktopRun = true;
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameStart gameStart = new GameStart();
		
		gameStart.start();
	}
	public void start(){
		thread = new Thread(this);
		thread.start();
	}

	float scx = 0;
	float scy = 0;

	/**
	 * 初始化
	 */
	public void init(){
		scx =  AppDelegate.design_width / width;
		scy =  AppDelegate.design_high / high;
		keyListion =new KeyListion();
		// Initializes our window creator library - GLFW  初始化我们的窗口，由GLFW类库创建
		// This basically means, if this glfwInit() doesn't run properlly 
		// print an error to the console  这是一个基本的手法，如果glfwInit()运行不正确输出一个错误信息在控制台
		if(glfwInit() != GL_TRUE){
			System.err.println("GLFW 初始化失败!");
		}
		
		// Allows our window to be resizable  允许我们的窗口可调节大小尺寸
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		
		// Creates our window. You'll need to declare private long window at the
		// top of the class though. 创建我们的窗口。你需要声明一个私有 long类型的window变量在这个类
		// We pass the width and height of the game we want as well as the title for
		// the window. The last 2 NULL parameters are for more advanced uses and you
		// shouldn't worry about them right now. （这句话太TM长了我快编不下去了），我们需要设置
		// 窗口的宽度和高度和标题，这两个NULL是关于更高级的一些设置，你现在不用担心不理解他们
		window = glfwCreateWindow(width, high, "By jacky.2017-02-13", NULL, NULL);
		 
		 
		 
		// This code performs the appropriate checks to ensure that the
		// window was successfully created.  这段代码确保window成功创建
		// If not then it prints an error to the console 如果不是将会输出一个错误在控制台
		if(window == NULL){
			// Throw an Error 抛出一个错误
			System.err.println("无法创建我们的窗口!");
		}
		
		
		// creates a bytebuffer object 'vidmode' which then queries 
		// to see what the primary monitor is. 创建一个bytebuff
		//ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		// Sets the initial position of our game window. 设置游戏窗口的位置
		glfwSetWindowPos(window, x, y);
		// Sets the context of GLFW, this is vital for our program to work. 设置GLFW环境，这是让我们程序工作的至关重要的一步
		glfwMakeContextCurrent(window);
		// finally shows our created window in all it's glory.完成显示我们的窗口
		glfwShowWindow(window);
		
		
		// In order to perform OpenGL rendering, a context must be "made current"
		// we can do this by using this line of code:
		GLContext.createFromCurrent();
		
		
		
		 // Clears color buffers and gives us a nice color background.
		glClearColor(0f, 0f, 0f, 1.0f);
		// Enables depth testing which will be important to make sure
		// triangles are not rendering in front of each other when they
		// shouldn't be.
		glEnable(GL_DEPTH_TEST);
		// Prints out the current OpenGL version to the console.
		System.out.println("OpenGL版本: " + glGetString(GL_VERSION));
		
		System.out.println("OpenGL厂商: " + glGetString(GL_RENDERER));
		// Sets our keycallback to equal our newly created Input class()
		//glfwSetKeyCallback(window, keyCallback =new Input());
		glfwSetKeyCallback(window,keyListion );

		ShaderParticle.share().init();
		ShaderBone.share().init();
		Shader.share().init();

		AppDelegate.share().view_high = high;
		
		AppDelegate.share().view_width = width;

		//初始化声音类
		SoundDesktop as = new SoundDesktop();
		as.init();
		SoundManger.share().initInterface(as);

		AppDelegate.share().graphicsTool = new DesktopGraphics();
		
		AppDelegate.share().localFile = new IOutil();
		AppDelegate.share().userpre = new UserPreferences();//初始化用户信息储存模块

		AppDelegate.share().cp = new DesktopPay();
		AppDelegate.share().lu = new LocalUtilDestop();

		AppDelegate.share().init();
		
		
	}
	/**
	 * 2个事件监听列表，当执行到某一帧时游戏逻辑处理其中一个监听事件列表
	 * 而设备生成的事件就添加到另一个列表中，下一帧将替换
	 */
	public static List<TouchEventJ> eventListA = new ArrayList();
	public static List<TouchEventJ> eventListB = new ArrayList();
	/**
	 * 设备生成的事件正在使用事件列表A吗？//
	 */
	public static boolean drive_isUse_eventA = true;


	public boolean isrelease = true;//标识鼠标左键是否可以被按下
	@Override
	public void run() {
		// All our initialization code 我们所有的初始化代码
		init();
		
		
		while(desktopRun){
			glfwPollEvents();
			
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


			int state = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT);



			if (isrelease && state == GLFW_PRESS) {
				isrelease = false;

				DoubleBuffer xpos = BufferUtils.createDoubleBuffer(1);
				DoubleBuffer ypos = BufferUtils.createDoubleBuffer(1);
				glfwGetCursorPos(window, xpos, ypos);

				TouchEventJ tej = new TouchEventJ();
				tej.event = TouchEventI.TOUCH_EVENT_END;
				tej.tag_x = (float)xpos.get(0) * scx;
				tej.tag_y = (float)ypos.get(0) * scy;
				AppDelegate.share().currentScreen.accept_event(tej);


			}else if(state == GLFW_RELEASE){
				isrelease = true;
			}


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

			
			AppDelegate.share().runGame();
			
			int i  = glGetError();
			if(i!= GL_NO_ERROR){
				System.out.println("绘图失败，错误代码："+i);
			}
			// Swaps out our buffers 交换我们的帧缓存
			glfwSwapBuffers(window); 
			
			if(glfwWindowShouldClose(window) == GL_TRUE){
				desktopRun = false;
			}
		}
		glfwDestroyWindow(window);
		glfwTerminate();	
		
	}

}
