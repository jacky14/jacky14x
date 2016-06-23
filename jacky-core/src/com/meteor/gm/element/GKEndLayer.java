package com.meteor.gm.element;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Layer;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.engine.viewnode.Number2D;
import com.jacky.start.AppDelegate;
import com.meteor.gm.GameTestScene;
import com.meteor.gm.LevelScene;
import com.meteor.gm.util.Const;

/**
 * 关卡结束界面
 * Created by Administrator on 2016/4/5.
 */
public class GKEndLayer extends Layer{


    @Override
    public void init(){
       // gkendlayer.xui
        this.r2d = GenUITool.gen2d("gkendlayer.xui");
        for(int i=0;i<r2d.size();i++){
           Node2D dn = r2d.get(i);
           if("sb".equals(dn.name)){
               faile = dn;
           }else if("cg".equals(dn.name)){
               success = dn;
           }else if("c0".equals(dn.name)){
               num2d[0] = (Number2D)dn;
               num2d[0].setText("number/coinnum.png",10);
               num2d[0].setNum(0);
           }else if("c1".equals(dn.name)){
               num2d[1] = (Number2D)dn;
               num2d[1].setText("number/coinnum.png",10);
               num2d[1].setNum(0);
           }else if("c2".equals(dn.name)){
               num2d[2] = (Number2D)dn;
               num2d[2].setText("number/coinnum.png",10);
               num2d[2].setNum(0);
           }
        }

    }
    Number2D[] num2d = new Number2D[3];
    Node2D faile = null ;
    Node2D success = null ;
    int gsb [] = new int[3];
    public void showLayer(boolean f_s){
        this.isEnable = true;
        //f_s 成功或失败
        if(f_s){//成功通关
            faile.isEnable = false;
            success.isEnable = true;
        }else{//通关失败
            faile.isEnable = true;
            success.isEnable = false;
        }
        int shuyingjs = 0;//输赢获得的金币基数
        if(f_s){
            shuyingjs = 300;

            int gkjs = 0;
            switch (Const.mapid){
                case Map.sn06:
                    gkjs = (int)(Math.random()*50);
                    break;
                case Map.sn04:
                    gkjs = (int)(Math.random()*60)+70;
                    break;
                case Map.sn19:
                    gkjs = (int)(Math.random()*60)+90;
                    break;
                case Map.sn05:
                    gkjs = (int)(Math.random()*70)+100;
                    break;
                case Map.sn13:
                    gkjs = (int)(Math.random()*70)+100;
                    break;
            }
            shuyingjs+=gkjs;
        }else{//防止玩家进入游戏挂机输掉获得金币
            int gamemiao = ((GameTestScene)this.rescene).gametime/60;
            if(gamemiao<60){
                shuyingjs = gamemiao + (int)(Math.random()*15);
            }else if(gamemiao>=60&&gamemiao<180){
                shuyingjs = 80 +  (int)(Math.random()*15);
            }else if(gamemiao>=180&&gamemiao<300){
                shuyingjs = 150 +  (int)(Math.random()*40);
            }else{
                shuyingjs = 160 +  (int)(Math.random()*40);
            }
        }
        gsb[2] = shuyingjs % 1000 / 100;
        gsb[1] = shuyingjs % 100 / 10;
        gsb[0] = shuyingjs % 10;
        Const.addUserCoin(shuyingjs);
        for(int i=0;i<gsb.length;i++){
            num2d[i].setNum(gsb[i]);
        }

    }
    @Override
    public void update(){

    }

    @Override
    public void event(TouchEventJ tej){
        if(tej.event== TouchEventI.TOUCH_EVENT_END){
            for (int i = r2d.size() - 1; i >= 0; i--){
                Node2D dn = r2d.get(i);
                if (dn.isEnable && dn.node_type == Node2D.NODE_TYPE_BUTTON
                        && dn.isInRect(tej.tag_x, tej.tag_y)){
                    if ("return".equals(dn.name)){
                        Const.unlock_level();
                        LevelScene mg = new LevelScene();
                        AppDelegate.share().replaceScene(mg);
                    }
                }
            }
        }
    }
}
