package com.meteor.gm.element;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Layer;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.start.AppDelegate;
import com.meteor.gm.LevelScene;
import com.meteor.gm.util.Const;

/**
 * Created by Administrator on 2016/3/31.
 */
public class PauseLayer extends Layer{


    @Override
    public void init(){
        this.r2d = GenUITool.gen2d("pauselayer.xui");
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
                    if ("fhgk".equals(dn.name)){
                        LevelScene mg = new LevelScene();
                        AppDelegate.share().replaceScene(mg);
                    }else if("jixuyx".equals(dn.name)){
                        isEnable = false;
                        rescene.IsPause = false;
                        Layer.LayerParameter lp  = new Layer.LayerParameter ();
                        lp.layerid =  Const.layer_pause;
                        rescene.layerReturn(lp);
                    }
                }
            }
        }

    }
}
