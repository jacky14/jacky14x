package com.meteor.gm;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Layer;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.engine.viewnode.ProgressBar;
import com.jacky.engine.viewnode.Scene;
import com.jacky.start.AppDelegate;
import com.meteor.gm.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class LoadScene  extends Scene {



    ProgressBar pb ;
    GameTestScene gs;
    @Override
    public void initScene(){
        List<Node2D> r2d = GenUITool.gen2d("loadscnen.xui");
        for(int i=0;i<r2d.size();i++){
            addChile2D(r2d.get(i));
            if("loadbar".equals(r2d.get(i).name)){
                pb = (ProgressBar)r2d.get(i);
            }
        }
        pb.setJD(0.3f);
        gs =new  GameTestScene();


    }

    @Override
    public void update(){
        float jjd = gs.loadScene();
        if(jjd<0){
            AppDelegate.share().replaceScene(gs);
        }else {
            pb.setJD(jjd);
        }

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
