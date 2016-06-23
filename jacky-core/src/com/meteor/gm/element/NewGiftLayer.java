package com.meteor.gm.element;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Layer;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.start.AppDelegate;
import com.meteor.gm.util.Const;

import java.util.List;

/**
 * 礼包界面
 * Created by Administrator on 2016/3/29.
 */
public class NewGiftLayer extends Layer{


    @Override
    public void init(){
        this.r2d = GenUITool.gen2d("giftlayer.xui");
    }

    @Override
    public void update(){

    }



    @Override
    public void event(TouchEventJ tej) {
        if(tej.event== TouchEventI.TOUCH_EVENT_END){
            for(int i = r2d.size()-1;i>=0;i--){
                Node2D dn = r2d.get(i);
                if(dn.isEnable&&dn.isInRect(tej.tag_x,tej.tag_y)){
                    if("close".equals(dn.name)){

                        backScene();
                    }else if("buy".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_12);
                    }
                    return;
                }
            }
        }else if(tej.event== TouchEventI.PAY_CALLBACK){//支付回调
            if(tej.payid == Const.PROPINDEX_12){//如果成功购买了新手礼包
                Const.buyRole(4);
                Const.buyWeapon(6);
                Const.addUserCoin(2888);
                isEnable = false;
                rescene.IsPause = false;


                Layer.LayerParameter lp  = new Layer.LayerParameter ();
                lp.layerid = Const.layer_newgift;
                rescene.layerReturn(lp);
            }
        }else if(tej.event == TouchEventI.KEY_BACK){
            backScene();
        }

    }

    public void backScene(){
        isEnable = false;
        rescene.IsPause = false;


        Layer.LayerParameter lp  = new Layer.LayerParameter ();
        lp.layerid =  Const.layer_newgift;
        rescene.layerReturn(lp);
    }
}
