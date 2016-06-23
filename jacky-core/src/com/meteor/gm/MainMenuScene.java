package com.meteor.gm;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.sound.SoundManger;
import com.jacky.engine.viewnode.*;
import com.jacky.start.AppDelegate;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public class MainMenuScene extends Scene {
    Node2D openm ;
    Node2D closem;
    @Override
    public void initScene(){
        long st = System.currentTimeMillis();
        List<Node2D> r2d =GenUITool.gen2d("mainmenu.xui");
        System.out.println("time is " + (System.currentTimeMillis()-st));
        for(int i=0;i<r2d.size();i++){
            addChile2D(r2d.get(i));
            if("closem".equals(r2d.get(i).name)){
                closem = r2d.get(i);
            }else if("openm".equals(r2d.get(i).name)){
                openm = r2d.get(i);
            }
        }
        SoundManger.share().playmusic("sound/mainBg.mp3");
        musicBtnShow();
    }
    public int exitbj = 0;
    @Override
    public void update(){
        if(exitbj>0){
            exitbj--;
        }
    }

    @Override
    public void exitScene(){

    }

    void musicBtnShow(){
        if(SoundManger.share().soundstate){
            openm.isEnable = true;
            closem.isEnable = false;
        }else{
            openm.isEnable = false;
            closem.isEnable = true;
        }
    }
    @Override
    public void event(TouchEventJ tej){
        if(tej.event == TouchEventI.TOUCH_EVENT_END){
            //System.out.println("触摸点位置：" + tagx + " " + tagy);
            for(int i = chiles_2d.size()-1;i>=0;i--){
                Node2D dn = (Node2D)chiles_2d.get(i);
                if(dn.isEnable&&dn.isInRect(tej.tag_x,tej.tag_y)){
                    if("start".equals(dn.name)){
                        LevelScene mg = new LevelScene();
                        AppDelegate.share().replaceScene(mg);
                    }else if("closem".equals(dn.name)){
                        SoundManger.share().setState(true);
                        musicBtnShow();
                    }else if("openm".equals(dn.name)){
                        SoundManger.share().setState(false);
                        musicBtnShow();
                    }
                    return;
                }
            }


        }else if(tej.event == TouchEventI.KEY_BACK){
            if(exitbj>0){
                //退出游戏
                AppDelegate.share().lu.exit();
            }else{
                exitbj = 120;
                AppDelegate.share().lu.showMsg("再次点击返回键退出游戏。");
            }
        }
    }

    @Override
    public void layerReturn(Layer.LayerParameter lp) {

    }
}
