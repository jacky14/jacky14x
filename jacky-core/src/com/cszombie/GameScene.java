package com.cszombie;

import com.jacky.engine.MuvManger;
import com.jacky.engine.TextureManger;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.*;
import com.jacky.start.AppDelegate;

/**
 * Created by Administrator on 2016/4/20.
 */
public class GameScene extends Scene{
    @Override
    public void initScene(){
        String [] effp = new String[]{
                "eff/FIRE2.jpg",
                "eff/fire2-red.jpg",
                "eff/FIRE2-W.jpg",
                "eff/fire-green.jpg",
                "eff/fire-yellow.jpg",
                "eff/FLASH000.jpg",
                "eff/FLASH001.jpg",
                "eff/FLASH105.jpg",
                "eff/FLASH301.jpg",
                "eff/flashblue.jpg",
                "eff/FLASHGRE.jpg",
                "eff/FLASHRED.jpg",
                "eff/FLASHYEL.jpg",
                "eff/KANG200.jpg",
                "eff/KANGL00.jpg",
                "eff/KANGL001.jpg",
                "eff/track.jpg",
                "eff/ZCHZ1000.jpg",
                "eff/ZCHZA400.jpg",
                "eff/zchza400a.jpg",
                "eff/zchza400b.jpg",
                "eff/ZSD.jpg",
                "eff/ZSD1.jpg"
        };
        String str =  "eff/Plist2.jpg";


        long start = System.currentTimeMillis();
        for(int i=0;i<effp.length;i++){
            int sfsfa = TextureManger.getTexture(effp[i]);
        }
       // int adsfdsf = TextureManger.getTexture(str);

        long end = System.currentTimeMillis() - start;
        System.out.println("测试时间是：" + end);

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
        MuvManger.load("newp/Plist1.jpg","newp/prs.muv");
        String [] textures = new String[]{
                "Particle/FLASH001.jpg",
                "Particle/ZSD1.jpg",
                "Particle/KANGL00.jpg",
                "Particle/track.jpg",
                "Particle/flashblue.jpg",
        };
        String [] yibaige = new String[50];
        for(int i=0;i<yibaige.length;i++){
            yibaige[i] = textures[(int)(Math.random()*textures.length)];
        }


        Particle p = new Particle(yibaige);
        p.rotate(45,1,0,0);
        p.updateRotate();
        /*p.moveinfo[0] = 12 ;  p.moveinfo[1] = 12 ;  p.moveinfo[2] = -2 ;
        p.moveinfo[3] = -66 ;  p.moveinfo[4] = 0 ;  p.moveinfo[5] = 0 ;
        p.moveinfo[6] = 0 ;    p.moveinfo[7] = 66 ; p.moveinfo[8] = 0 ;
        p.updatePoist();*/




        addTranslucenceChile(p);






     /*   String [] effp = new String[]{
                "eff/FLASH001.jpg",
                "eff/ZSD1.jpg",
                "eff/KANGL00.jpg",
                "eff/track.jpg",
                "eff/flashblue.jpg",
        };
       for(int i=0;i<300;i++){
            Sprite   lizi = new Sprite("eff/lizi.jxb", effp[(int)(Math.random()*textures.length)]);
            float x  = (float)(Math.random()*100 - 50);
            float y = (float)(Math.random()*100 - 50);

            lizi.translate(x,y,0);

            addTranslucenceChile(lizi);
        }
*/



    }

    @Override
    public void update(){

    }

    @Override
    public void exitScene(){

    }

    @Override
    public void event(TouchEventJ tej){

    }

    @Override
    public void layerReturn(Layer.LayerParameter lp) {

    }
}
