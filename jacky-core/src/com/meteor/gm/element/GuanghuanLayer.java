package com.meteor.gm.element;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Layer;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.start.AppDelegate;
import com.meteor.gm.util.Const;

/**
 * Created by Administrator on 2016/3/31.
 */
public class GuanghuanLayer extends Layer{


    Node2D [] sinfo = new Node2D[3];//是否选中信息
    Node2D [] btn_select = new Node2D[3];//选择按钮信息
    Node2D [] btn_buy = new Node2D[3];//购买按钮信息

    @Override
    public void init(){
        this.r2d = GenUITool.gen2d("guanhuanLayer.xui");

        for(int i=0;i<r2d.size();i++){
            Node2D tmp = r2d.get(i);
            if("cgj".equals(tmp.name)){
                sinfo[0] = tmp;
            }else if("cfy".equals(tmp.name)){
                sinfo[1] = tmp;
            }else if("czj".equals(tmp.name)){
                sinfo[2] = tmp;
            }else if("sgj".equals(tmp.name)){
                btn_select[0] = tmp;
            }else if("sfy".equals(tmp.name)){
                btn_select[1] = tmp;
            }else if("szj".equals(tmp.name)){
                btn_select[2] = tmp;
            }else if("bgj".equals(tmp.name)){
                btn_buy[0] = tmp;
            }else if("bfy".equals(tmp.name)){
                btn_buy[1] = tmp;
            }else if("bzj".equals(tmp.name)){
                btn_buy[2] = tmp;
            }
        }
        reUI();
    }



    //刷新UI
    private void reUI(){
        for(int i=0;i<3;i++){
            if(Const.guanghuanlock_info[i]){//若角色已解锁
                btn_buy[i].isEnable = false;
                if(Const.cbid == i){
                    sinfo[i].isEnable = true;
                    btn_select[i].isEnable = false;
                }else{
                    sinfo[i].isEnable = false;
                    btn_select[i].isEnable = true;
                }
            }else{//若角色未解锁
                sinfo[i].isEnable = false;
                btn_select[i].isEnable = false;
                btn_buy[i].isEnable = true;
            }
        }
    }

    @Override
    public void update() {

    }
    @Override
    public void event(TouchEventJ tej) {
        if(tej.event== TouchEventI.TOUCH_EVENT_END){
            for (int i = r2d.size() - 1; i >= 0; i--){
                Node2D dn = r2d.get(i);
                if (dn.isEnable && dn.node_type == Node2D.NODE_TYPE_BUTTON
                        && dn.isInRect(tej.tag_x, tej.tag_y)){
                    if ("close".equals(dn.name)){
                        backScene();
                    }else if("sgj".equals(dn.name)){
                        Const.cbid = ChiBang.chibang_gj;
                        reUI();
                    }else if("sfy".equals(dn.name)){
                        Const.cbid = ChiBang.chibang_fy;
                        reUI();
                    }else if("szj".equals(dn.name)){
                        Const.cbid = ChiBang.chibang_zj;
                        reUI();
                    }else if("bgj".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_13);
                    }else if("bfy".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_14);
                    }else if("bzj".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_15);
                    }
                    return;
                }
            }
        }else if(tej.event== TouchEventI.PAY_CALLBACK){
            if(tej.payid == Const.PROPINDEX_13){//购买攻击光环
                Const.getGuanghuan(ChiBang.chibang_gj);reUI();
            }else if(tej.payid == Const.PROPINDEX_14){//购买防御光环
                Const.getGuanghuan(ChiBang.chibang_fy);reUI();
            }else if(tej.payid == Const.PROPINDEX_15){//购买主角光环
                Const.getGuanghuan(ChiBang.chibang_zj);reUI();
            }

        }else if(tej.event == TouchEventI.KEY_BACK){
            backScene();
        }
    }


    public void backScene(){
        isEnable = false;
        rescene.IsPause = false;
        Layer.LayerParameter lp = new Layer.LayerParameter();
        lp.layerid =  Const.layer_guanghuan;
        rescene.layerReturn(lp);
    }
}
