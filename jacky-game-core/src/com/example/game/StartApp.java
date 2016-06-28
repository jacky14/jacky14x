package com.example.game;

import com.jacky.start.AppDelegate;
import com.testgame.GameScene;

public class StartApp {
	public static void setFirstScene(){
		AppDelegate.share().first = new GameScene();
		AppDelegate.design_high = 1136;
		AppDelegate.design_width = 640;
	}
}
