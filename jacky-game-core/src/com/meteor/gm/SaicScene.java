package com.meteor.gm;

import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.*;
import com.jacky.start.AppDelegate;

/**
 * Created by Administrator on 2016/5/16.
 */
public class SaicScene  extends Scene {

    public static  String [][] name = new String[][]{
            {"dimian",		"pmap_04_dimian.jpg"},//0
            {"cao1",			"pmap_04_cao1.jpg"},//1
            {"cao2",			"pmap_04_cao2.jpg"},//2
            {"hai",			"pmap_04_hai.jpg"},//3
            {"tietu1",		"pmap_04_tietu1.jpg"},//4
            {"tietu2",		"pmap_04_tietu2.jpg"},//5
            {"road",			"pmap_04_road.jpg"},//6
            {"skybox",		"pmap_04_skybox.jpg"},//7
            {"touming",	"pmap_04_touming.png"},//8
            {"touming2",	"pmap_04_touming2.png"},//9
            {"yinying",	"pmap_04_yinying.png"}//10
    };


    @Override
    public void initScene() {
        float[] args = new float[]{
                0, -30, 0,
                -1, -30, 0,
                0, -1,0,
                -0.885f, 0.885f,
                -0.5f, 0.5f,
                1f, 10000
        };
        AppDelegate.share().camera = new Camera(args);
        //向所有着色程序设置初始相机
        AppDelegate.share().graphicsTool.updateAllCamera(AppDelegate.share().camera.getCameraM());

        for(int i = 0;i<name.length;i++){
            Sprite st = new Sprite(  "sc/"+ name[i][0] + ".jxb",  "sc/"+  name[i][1]);

            if(i>=10){
                st.setTexMode(Node.TEX_MODE2);
                st.isTranslucence = true;
            }else if(i>=8){
                st.isTranslucence = true;

            }

            this.addChile(st);
        }




    }
    public boolean xq = false;
    public boolean xh = false;

    public boolean xz = false;
    public boolean xy = false;

    public boolean xs = false;
    public boolean xx = false;
    @Override
    public void update() {
        if(xq){
            AppDelegate.share().camera.view(0);
        }else if(xh){
            AppDelegate.share().camera.view(1);
        }else if(xz){
            AppDelegate.share().camera.view(2);
        }else if(xy){
            AppDelegate.share().camera.view(3);
        }else if(xs){
            AppDelegate.share().camera.view(4);
        }else if(xx){
            AppDelegate.share().camera.view(5);
        }
    }

    @Override
    public void exitScene() {

    }


    @Override
    public void event(TouchEventJ tej) {
        //87 w   83s  65a  68d  h72    74
        if(tej.uuid == 87){
            if(tej.event == 101){ // 按下
                xq = true;
            }else if(tej.event == 100){//抬起
                xq = false;
            }
        }else if(tej.uuid == 83){
            if(tej.event == 101){ // 按下
                xh = true;
            }else if(tej.event == 100){//抬起
                xh = false;
            }
        }else if(tej.uuid == 65){
            if(tej.event == 101){ // 按下
                xz = true;
            }else if(tej.event == 100){//抬起
                xz =false;
            }
        }else if(tej.uuid == 68){
            if(tej.event == 101){ // 按下
                xy = true;
            }else if(tej.event == 100){//抬起
                xy = false;
            }
        }

        else if(tej.uuid == 72){
            if(tej.event == 101){ // 按下
                xs = true;
            }else if(tej.event == 100){//抬起
                xs =false;
            }
        }else if(tej.uuid == 74){
            if(tej.event == 101){ // 按下
                xx = true;
            }else if(tej.event == 100){//抬起
                xx = false;
            }
        }
        System.out.println("测试按键：" + tej.uuid);
    }

    @Override
    public void layerReturn(Layer.LayerParameter lp) {

    }
}
