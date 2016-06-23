package com.meteor.gm;

import java.util.List;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Camera;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.engine.viewnode.Layer.LayerParameter;
import com.jacky.engine.viewnode.Scene;
import com.jacky.start.AppDelegate;
import com.meteor.gm.element.Map;
import com.meteor.gm.element.Role;
import com.meteor.gm.util.CommonAni;
import com.meteor.gm.util.Const;

public class TestScene extends Scene {

    @Override
    public void initScene() {
        // TODO Auto-generated method stub

        CommonAni.load();

        //初始化摄像机
        float[] args = new float[]{
                0, AppDelegate.camera_len, 120,
                0, 0, 20,
                0, 0, 1,
                -0.885f, 0.885f,
                -0.5f, 0.5f,
                1f, 10000
        };
        AppDelegate.share().camera = new Camera(args);
        //向所有着色程序设置初始相机
        AppDelegate.share().graphicsTool.updateAllCamera(AppDelegate.share().camera.getCameraM());

        Map.addMap2Scene(this,Map.sn06);

        List<Node2D> r2d = GenUITool.gen2d("maingame.xui");
        for (int i = 0; i < r2d.size(); i++){
            Node2D node2dtmp = r2d.get(i);

            if("lence".equals(node2dtmp.name)){
                node2dtmp.isEnable = false;
            }

            addChile2D(node2dtmp);
        }
        Role  hero = new Role(Const.roleid,Role.camp_liuxing);
        addChile(hero);
        hero.setWeapon(Const.weaponid);
        hero.setCb(Const.cbid);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void exitScene() {
        // TODO Auto-generated method stub

    }

    @Override
    public void event(TouchEventJ tej) {
        // TODO Auto-generated method stub

    }

    @Override
    public void layerReturn(LayerParameter lp) {
        // TODO Auto-generated method stub

    }

}
