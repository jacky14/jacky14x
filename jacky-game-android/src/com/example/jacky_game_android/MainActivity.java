package com.example.jacky_game_android;

import android.os.Bundle;
import android.view.KeyEvent;

import com.example.game.StartApp;
import com.jacky.android.JackyActivity;

public class MainActivity extends JackyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        StartApp.setFirstScene();
    }

    @Override
    protected void onResume (){
        super.onResume();
       
    }
     
    @Override
    protected void onPause (){
        super.onPause();
		
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		return super.onKeyDown(keyCode, event);
	}
   
    
}
