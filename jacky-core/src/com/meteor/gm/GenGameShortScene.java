package com.meteor.gm;

import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.*;
import com.jacky.start.AppDelegate;
import com.meteor.gm.element.Map;
import com.meteor.gm.element.Role;
import com.meteor.gm.element.Weapon;
import com.meteor.gm.util.CommonAni;

/**
 * 用于生成游戏中的截图场景
 * Created by Administrator on 2016/3/29.
 */
public class GenGameShortScene extends Scene{


    @Override
    public void initScene() {



        CommonAni.load();
       /* float[] args = new float[]{
                0,60, 20,
                0, 0, 20,
                0, 0, 1,
                -0.885f, 0.885f,
                -0.5f, 0.5f,
                1f, 10000
        };//查看角色相机设置*/


        float[] args = new float[]{
                0,-7, 28,
                0,-7, 0,
                1, 0, 0,
                -0.885f, 0.885f,
                -0.5f, 0.5f,
                1f, 10000
        };//查看武器相机设置

        AppDelegate.share().camera = new Camera(args);
        //向所有着色程序设置初始相机
        AppDelegate.share().graphicsTool.updateAllCamera(AppDelegate.share().camera.getCameraM());

         hero = new Role(Role.role_hun,Role.camp_liuxing);


        //addChile(hero);

        hero.setPoint(0,0);


        wp = new Weapon(Weapon.weapon_id_dao);

        addChile(wp);

        Sprite maptest =new Sprite( "hehe.jxb", "smallbg.png");

        maptest.rotate(90,1,0,0);

        addChile(maptest);

    }
    Weapon wp;
    int asfasdf = Weapon.weapon_id_dao;

    Role hero;
    @Override
    public void update() {

    }

    @Override
    public void exitScene() {

    }

    @Override
    public void event(TouchEventJ tej){
        if(tej.event== TouchEventI.TOUCH_EVENT_END){
            this.removeChile(wp);
            asfasdf++;
            if(asfasdf>Weapon.weapon_id_fenjiyellow){
                asfasdf = 0;
            }
            wp = new Weapon(asfasdf);
            addChile(wp);
        }

        if(tej.uuid == 77){//m按键
            hero.role_angle ++;
        }else if(tej.uuid == 78){//N按键
            hero.role_angle --;
        }
    }

    @Override
    public void layerReturn(Layer.LayerParameter lp){

    }
}
