package com.exmp.desk;

import com.example.game.StartApp;
import com.jacky.GameStart;

public class AppStart {
	public static void main(String[] args) {
		GameStart gameStart = new GameStart();
		gameStart.width = 850;
		gameStart.high = 480;
		gameStart.x = 100;
		gameStart.y = 100;
		
		StartApp.setFirstScene();
		gameStart.start();
	}
}
