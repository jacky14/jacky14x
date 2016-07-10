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
 * 角色选择界面
 * Created by Administrator on 2016/3/29.
 */
public class RoleSelectLayer extends Layer{

    @Override
    public void init() {
        this.r2d = GenUITool.gen2d("rolelayer.xui");
        for(int i=0;i<r2d.size();i++){
            Node2D tmp = r2d.get(i);

            if("sbtn0".equals(tmp.name)){
                xuanzhe_btn[0] = tmp;
            }else if("sbtn1".equals(tmp.name)){
                xuanzhe_btn[1] = tmp;
            }else if("sbtn2".equals(tmp.name)){
                xuanzhe_btn[2] = tmp;
            }else if("sbtn3".equals(tmp.name)){
                xuanzhe_btn[3] = tmp;
            }else if("sbtn4".equals(tmp.name)){
                xuanzhe_btn[4] = tmp;
            }else if("sbtn5".equals(tmp.name)){
                xuanzhe_btn[5] = tmp;
            }else if("buyyandef".equals(tmp.name)){
                buy_btn[0]= tmp;
            }else if("buyping".equals(tmp.name)){
                buy_btn[1]= tmp;
            }else if("buyyanlw".equals(tmp.name)){
                buy_btn[2]= tmp;
            }else if("buyxinglw".equals(tmp.name)){
                buy_btn[3]= tmp;
            }else if("buyxinganh".equals(tmp.name)){
                buy_btn[4]= tmp;
            }else if("re0".equals(tmp.name)){
                select_info[0] = tmp;
            }else if("re1".equals(tmp.name)){
                select_info[1] = tmp;
            }else if("re2".equals(tmp.name)){
                select_info[2] = tmp;
            }else if("re3".equals(tmp.name)){
                select_info[3] = tmp;
            }else if("re4".equals(tmp.name)){
                select_info[4] = tmp;
            }else if("re5".equals(tmp.name)){
                select_info[5] = tmp;
            }

        }

        initroleui();
    }
    @Override
    public void openLayer() {
        super.openLayer();
        initroleui();
    }
    public int [] rids = new int [] {
            Role.role_hun,Role.role_yan ,Role.role_weizhinv,
            Role.role_yanlongwen,Role.role_hunlongwen,Role.role_hunheke
    };
    //选择角色按钮
    Node2D [] xuanzhe_btn = new Node2D[6];

    Node2D [] buy_btn = new Node2D[5];

    Node2D [] select_info = new Node2D[6];
    /**
     * 主要根据当前角色解锁信息更新UI界面
     */
    private void initroleui(){
        for(int i=0;i<6;i++){
            //该值标识当前角色是否可用是不是已经购买
            boolean roleisenable = false;
            if(i==0){//0号角色默认是可用的
                roleisenable = true;
            }else{
                roleisenable = Const.rolelock_info[i-1];
                if(roleisenable){
                    buy_btn[i-1].isEnable = false;
                }else{
                    buy_btn[i-1].isEnable = true;
                }
            }
            if(roleisenable){
                if(Const.roleid == rids[i]){
                    select_info[i].isEnable = true;
                    xuanzhe_btn[i].isEnable = false;
                }else{
                    select_info[i].isEnable = false;
                    xuanzhe_btn[i].isEnable = true;
                }
            }else{
                select_info[i].isEnable = false;
                xuanzhe_btn[i].isEnable = false;

            }

        }
    }

    @Override
    public void update() {

    }



    @Override
    public void event(TouchEventJ tej) {
        if(tej.event== TouchEventI.TOUCH_EVENT_END){
            for(int i = r2d.size()-1;i>=0;i--){
                Node2D dn = r2d.get(i);
                if(dn.isEnable&&dn.node_type== Node2D.NODE_TYPE_BUTTON
                        &&dn.isInRect(tej.tag_x,tej.tag_y)){
                    if("close".equals(dn.name)){
                        backScene();
                    }else if("sbtn0".equals(dn.name)){
                        Const.roleid = rids[0];initroleui();
                    }else if("sbtn1".equals(dn.name)){
                        Const.roleid = rids[1];initroleui();
                    }else if("sbtn2".equals(dn.name)){
                        Const.roleid = rids[2];initroleui();
                    }else if("sbtn3".equals(dn.name)){
                        Const.roleid = rids[3];initroleui();
                    }else if("sbtn4".equals(dn.name)){
                        Const.roleid = rids[4];initroleui();
                    }else if("sbtn5".equals(dn.name)){
                        Const.roleid = rids[5];initroleui();
                    }else if("buyyandef".equals(dn.name)){
                        if(Const.coin_buy(Const.PROPINDEX_0)){//如果购买成功则刷新角色选择界面
                            initroleui();
                            if(this.rescene.uuid == Const.scene_level){//如果当前场景是管卡选择场景，金币购买后需要直接刷新金币数
                                ((LevelScene)this.rescene).refNumUI();
                            }
                        }
                    }else if("buyping".equals(dn.name)){
                        if(Const.coin_buy(Const.PROPINDEX_1)){//如果购买成功则刷新角色选择界面
                            initroleui();
                            if(this.rescene.uuid == Const.scene_level){//如果当前场景是管卡选择场景，金币购买后需要直接刷新金币数
                                ((LevelScene)this.rescene).refNumUI();
                            }
                        }
                    }else if("buyyanlw".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_2);
                    }else if("buyxinglw".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_3);
                    }else if("buyxinganh".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_4);
                    }
                    return;
                }
            }
        }else if(tej.event== TouchEventI.PAY_CALLBACK){//支付回调
            if(tej.payid == Const.PROPINDEX_2){//购买角色龙纹燕
                Const.buyRole(2);initroleui();
            }else if(tej.payid == Const.PROPINDEX_3){//购买角色龙纹魂
                Const.buyRole(3);initroleui();
            }else if(tej.payid == Const.PROPINDEX_4){//购买角色黑色星
                Const.buyRole(4);initroleui();
            }

        }else if(tej.event == TouchEventI.KEY_BACK){
            backScene();
        }
    }
    public void backScene(){
        isEnable = false;
        rescene.IsPause = false;
        Layer.LayerParameter lp  = new Layer.LayerParameter ();
        lp.layerid = Const.layer_role;
        rescene.layerReturn(lp);
    }


}
