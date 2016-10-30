package com.jacky.android;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.sound.SoundManger;
import com.jacky.start.AppDelegate;

public class JackyActivity extends Activity {

	
	public static AssetManager assetManager;
	
	public static  MainView mainView;

	public static JackyActivity jackyActivity;
	public static String MsgInfo="";
	public static  Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			switch (msg.what){
				case 0:
					//Toast.makeText(JackyActivity.jackyActivity,"fps:"+ AppDelegate.share().tmpfpsnum, Toast.LENGTH_SHORT).show();
					Toast.makeText(JackyActivity.jackyActivity,MsgInfo, Toast.LENGTH_SHORT).show();
					break;
				case 1://通知具体支付sdk进行支付  JackyActivity
					jackyActivity.sk.skpay();
					break;
				case 2://支付sdk通知gl游戏支付成功
					AppDelegate.share().cp.paysuccecc();
					break;

			}
		}
	};
	private  SKPay sk;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //
     	//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
     	//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     	assetManager = getAssets();
		jackyActivity = this;
		mainView = new MainView(this);





		sk = new SKPay();



		//PayConnect.getInstance("da984f87ea2ed60ac90c00e560bdd4ca", "shenhe", this);


	}



	@Override
    protected void onResume (){
        super.onResume();
        //mainView.onResume();
        AppDelegate.share().applicationWillEnterForeground();
    }
     
    @Override
    protected void onPause (){
        super.onPause();
        //mainView.onPause();
		AppDelegate.share().applicationDidEnterBackground();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
        
		//PayConnect.getInstance(this).close();
		System.exit(0);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
			SoundManger.share().sf.revol();
		}else if(keyCode == KeyEvent.KEYCODE_BACK){
			TouchEventJ tej =new TouchEventJ();
			tej.event = TouchEventI.KEY_BACK;
			mainView.addEvent(tej);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


	/*@Override
	public boolean onKeyUp(int keyCode, KeyEvent event){
		System.exit(0);
		return true;
	}*/
}
