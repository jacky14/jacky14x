package com.jacky.desktop.Input;

import com.jacky.GameStart;
import com.jacky.engine.input.TouchEventJ;
import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*; 
public class KeyListion extends GLFWKeyCallback{

	public static boolean[] keys = new boolean[65535];

	public static boolean w_key = false;
	public static boolean s_key = false;

	public static boolean o_key = false;
	public static boolean l_key = false;
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods){
		// TODO Auto-generated method stub
		//System.out.println("key = " + key + "scancode=" +scancode +"action = " + action + "mods = " +mods );
		//action 1 按下  2 按住  3 抬起

		TouchEventJ tej =new TouchEventJ();

		tej.uuid = key;
		tej.event = action+100;

		if(GameStart.drive_isUse_eventA){
			GameStart.eventListA.add(tej);
		}else {
			GameStart.eventListB.add(tej);
		}
		//System.out.println("key = " + key + "scancode=" +scancode +"action = " + action + "mods = " +mods );
		//keys[key] = action != GLFW_RELEASE;
	}

}
