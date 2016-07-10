package com.meteor.gm.element;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Layer;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.start.AppDelegate;
import com.meteor.gm.LevelScene;
import com.meteor.gm.util.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * 装备选择界面
 * Created by Administrator on 2016/3/29.
 */
public class EquipSelectLayer extends Layer {

    /**
     * 当前展示的是第几个页码
     */
    int page_num = 0;

    List<Node2D> firstpage_bg = new ArrayList<>();//第一页页码背景小图片
    List<Node2D> secpage_bg = new ArrayList<>();//第二页页码背景小图片


    int bakwid  = 0;
    @Override
    public void init(){
        this.r2d = GenUITool.gen2d("equipLayer.xui");

        for(int i=0;i<r2d.size();i++){
            Node2D tmp = r2d.get(i);
            if("0".equals(tmp.name)){
                firstpage_bg.add(tmp);
            }else if("1".equals(tmp.name)){
                secpage_bg.add(tmp);
            }else if("wp0".equals(tmp.name)){
                select_info[0] = tmp;
            }else if("wp1".equals(tmp.name)){
                select_info[1] = tmp;
            }else if("wp2".equals(tmp.name)){
                select_info[2] = tmp;
            }else if("wp3".equals(tmp.name)){
                select_info[3] = tmp;
            }else if("wp4".equals(tmp.name)){
                select_info[4] = tmp;
            }else if("wp5".equals(tmp.name)){
                select_info[5] = tmp;
            }else if("xz0".equals(tmp.name)){
                select_btn[0] = tmp;
            }else if("xz1".equals(tmp.name)){
                select_btn[1] = tmp;
            }else if("xz2".equals(tmp.name)){
                select_btn[2] = tmp;
            }else if("xz3".equals(tmp.name)){
                select_btn[3] = tmp;
            }else if("xz4".equals(tmp.name)){
                select_btn[4] = tmp;
            }else if("xz5".equals(tmp.name)){
                select_btn[5] = tmp;
            }else if("rmb0".equals(tmp.name)){
                rmb_buybtn[0] = tmp;
            }else if("rmb1".equals(tmp.name)){
                rmb_buybtn[1] = tmp;
            }else if("rmb2".equals(tmp.name)){
                rmb_buybtn[2] = tmp;
            }else if("rmb3".equals(tmp.name)){
                rmb_buybtn[3] = tmp;
            }else if("rmb4".equals(tmp.name)){
                rmb_buybtn[4] = tmp;
            }else if("rmb5".equals(tmp.name)){
                rmb_buybtn[5] = tmp;
            }else if("coin0".equals(tmp.name)){
                coin_buybtn[0] = tmp;
            }else if("coin1".equals(tmp.name)){
                coin_buybtn[1] = tmp;
            }
        }
        refUI();
    }

    public int[] pagef = new int []{
            Weapon.weapon_id_dao, Weapon.weapon_id_jian, Weapon.weapon_id_bishou,
            Weapon.weapon_id_def, Weapon.weapon_id_bihuodao, Weapon.weapon_id_badao
    };//第一页武器id
    public int[] pages = new int []{
            Weapon.weapon_id_zixia, Weapon.weapon_id_gongheguo,
            Weapon.weapon_id_fenjired, Weapon.weapon_id_fenjiyellow,
    };//第二页武器id

    Node2D [] select_info = new Node2D[6];
    Node2D [] select_btn = new Node2D[6];
    Node2D [] rmb_buybtn = new Node2D[6];
    Node2D [] coin_buybtn = new Node2D[2];

    public void refUI(){


        if(page_num==0){//当前页码为第一页时
            setSFalg(firstpage_bg,true);
            setSFalg(secpage_bg,false);
            boolean tmpisable = false;

            for(int i=0;i<pagef.length;i++){
                tmpisable = false;//当前武器是否已经购买可用



                if(i<3){//前3把武器免费可用
                    tmpisable = true;
                }else{
                    tmpisable = Const.weaponlock_info[i-3];
                }

                if(i<5){//第一页前4个人民币购买按钮一定会隐藏
                    rmb_buybtn[i].isEnable = false;
                }else{
                    if(tmpisable){
                        rmb_buybtn[i].isEnable = false;
                    }else{
                        rmb_buybtn[i].isEnable = true;
                    }
                }
                if(i==3||i==4){
                    if(tmpisable){
                        coin_buybtn[i-3].isEnable = false;
                    }else{
                        coin_buybtn[i-3].isEnable = true;
                    }
                }
                if(tmpisable){
                    if(pagef[i] == Const.weaponid){
                        select_info[i].isEnable = true;
                        select_btn[i].isEnable = false;
                    }else{
                        select_info[i].isEnable = false;
                        select_btn[i].isEnable = true;
                    }
                }else{
                    select_info[i].isEnable = false;
                    select_btn[i].isEnable = false;

                }

            }
            //boolean isable = false;//当前武器是否可用


        }else if(page_num==1){//当前页码为第二页时
            setSFalg(firstpage_bg,false);
            setSFalg(secpage_bg,true);
            for(int i=4;i<=5;i++){
                select_info[i].isEnable = false;
                select_btn[i].isEnable = false;
                rmb_buybtn[i].isEnable = false;
                coin_buybtn[i-4].isEnable =false;
            }


            boolean tmpisable = false;
            for(int i=0;i<4;i++){
                tmpisable = Const.weaponlock_info[i+3];

                if(tmpisable){
                    rmb_buybtn[i].isEnable = false;
                    if(pages[i] == Const.weaponid){
                        select_info[i].isEnable = true;
                        select_btn[i].isEnable = false;
                    }else{
                        select_info[i].isEnable = false;
                        select_btn[i].isEnable = true;
                    }
                }else{
                    rmb_buybtn[i].isEnable = true;
                    select_info[i].isEnable = false;
                    select_btn[i].isEnable = false;
                }



            }


        }



    }

    private void setSFalg(List<Node2D> l,boolean b){
        for(Node2D n2d: l){
            n2d.isEnable = b;
        }

    }

    @Override
    public void update(){

    }

    @Override
    public void event(TouchEventJ tej){
        if(tej.event== TouchEventI.TOUCH_EVENT_END) {
            for (int i = r2d.size() - 1; i >= 0; i--) {
                Node2D dn = r2d.get(i);
                if (dn.isEnable && dn.node_type == Node2D.NODE_TYPE_BUTTON
                        && dn.isInRect(tej.tag_x, tej.tag_y)) {
                    if ("close".equals(dn.name)) {
                        backScene();
                    }else if("next".equals(dn.name)){
                        page_num++;
                        if(page_num>1){
                            page_num =0;
                        }
                        refUI();
                    }else if("after".equals(dn.name)){
                        page_num--;
                        if(page_num<0){
                            page_num = 1;
                        }
                        refUI();
                    }else if("coin0".equals(dn.name)){
                        if(Const.coin_buy(Const.PROPINDEX_5)){

                            refUI();
                            if(this.rescene.uuid == Const.scene_level){//如果当前场景是管卡选择场景，金币购买后需要直接刷新金币数
                                ((LevelScene)this.rescene).refNumUI();
                            }

                        }
                    }else if("coin1".equals(dn.name)){
                        if(Const.coin_buy(Const.PROPINDEX_6)){
                            refUI();
                            if(this.rescene.uuid == Const.scene_level){//如果当前场景是管卡选择场景，金币购买后需要直接刷新金币数
                                ((LevelScene)this.rescene).refNumUI();
                            }
                        }
                    }else if("xz0".equals(dn.name)){
                        if(page_num==0){
                            Const.weaponid = pagef[0]; refUI();
                        }else if(page_num==1){
                            Const.weaponid = pages[0]; refUI();
                        }
                    }else if("xz1".equals(dn.name)){
                        if(page_num==0){
                            Const.weaponid = pagef[1]; refUI();
                        }else if(page_num==1){
                            Const.weaponid = pages[1]; refUI();
                        }
                    }else if("xz2".equals(dn.name)){
                        if(page_num==0){
                            Const.weaponid = pagef[2]; refUI();
                        }else if(page_num==1){
                            Const.weaponid = pages[2]; refUI();
                        }
                    }else if("xz3".equals(dn.name)){
                        if(page_num==0){
                            Const.weaponid = pagef[3]; refUI();
                        }else if(page_num==1){
                            Const.weaponid = pages[3]; refUI();
                        }
                    }else if("xz4".equals(dn.name)){
                        Const.weaponid = pagef[4]; refUI();
                    }else if("xz5".equals(dn.name)){
                        Const.weaponid = pagef[5]; refUI();
                    }else if("rmb0".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_8);
                    }else if("rmb1".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_9);
                    }else if("rmb2".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_10);
                    }else if("rmb3".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_11);
                    }else if("rmb5".equals(dn.name)){
                        AppDelegate.share().cp.pay(Const.PROPINDEX_7);
                    }
                    return;
                }
            }
        }else if(tej.event== TouchEventI.PAY_CALLBACK){////支付回调
            if(tej.payid == Const.PROPINDEX_7){//购买角色龙纹燕
                Const.buyWeapon(2);refUI();
            }else if(tej.payid == Const.PROPINDEX_8){//购买角色龙纹魂
                Const.buyWeapon(3);refUI();
            }else if(tej.payid == Const.PROPINDEX_9){//购买角色黑色星
                Const.buyWeapon(4);refUI();
            }else if(tej.payid == Const.PROPINDEX_10){//购买角色黑色星
                Const.buyWeapon(5);refUI();
            }else if(tej.payid == Const.PROPINDEX_11){//购买角色黑色星
                Const.buyWeapon(6);refUI();
            }

        }else if(tej.event == TouchEventI.KEY_BACK){
            backScene();
        }
    }
    public void backScene(){
        isEnable = false;
        rescene.IsPause = false;

        Layer.LayerParameter lp  = new Layer.LayerParameter();
        lp.layerid =  Const.layer_equip;

        rescene.layerReturn(lp);
    }


}
