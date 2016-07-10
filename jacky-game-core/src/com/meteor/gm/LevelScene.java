package com.meteor.gm;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.sound.SoundManger;
import com.jacky.engine.viewnode.*;
import com.jacky.engine.input.TouchEventI;
import com.jacky.start.AppDelegate;
import com.meteor.gm.element.*;
import com.meteor.gm.util.Const;
import com.meteor.gm.util.NumUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class LevelScene  extends Scene {


    /**
     * 当前选中的关卡id
     */
    //private int current_level = 0;

    RoleSelectLayer rsl;

    GuanghuanLayer ghl;

    ChenghaoLayer chl;

    @Override
    public void initScene(){
        //long start = System.currentTimeMillis();
        uuid = Const.scene_level;

        Const.readUserdef();

        Const.readrolelock();//检测角色解锁存储信息
        Const.readweaponlock();//读取武器解锁存储信息
        Const.readguanghuanlock();//读取光环解锁存储信息


        //检测当前开启的最大关卡
        Const.chick_maxlevel();

        NumUtil.init();

        switch (Const.mapid){
            case Map.sn06:
                Const.current_level = 0;
                break;
            case Map.sn04:
                Const.current_level = 1;
                break;
            case  Map.sn19:
                Const.current_level = 2;
                break;
            case Map.sn05:
                Const.current_level = 3;
                break;
            case Map.sn13:
                Const.current_level = 4;
                break;
        }




       List<Node2D> r2d = GenUITool.gen2d("levelscene.xui");
        for(int i=0;i<r2d.size();i++){
            Node2D tmp = r2d.get(i);
            for(int j=0;j<level_bg_name.length;j++){//保存关卡背景的引用
                if(level_bg_name[j].equals(tmp.name)){
                    level_bg[j] = tmp;
                }
            }

            for(int j=0;j<level_title_name.length;j++){//保存关卡标题的引用
                if(level_title_name[j].equals(tmp.name)){
                    level_title[j] = tmp;
                }
            }

            if("lock".equals(tmp.name)){
                lock = tmp;
            }else if("btngift".equals(tmp.name)){
                btngift = tmp;
            }else if("zwkf".equals(tmp.name)){
                zwkf = tmp;
            }else{
                for(int j=0;j<num2d.length;j++){
                    if(("c"+j).equals(tmp.name)){
                        num2d[j] =(Number2D) tmp;
                        num2d[j].setText("number/coinnum.png",10);
                        num2d[j].setNum(0);
                    }
                }
            }
            addChile2D(tmp);
        }
        refNumUI();



        setLevel(Const.current_level);
        Const.initCommonUI();

        Const.ngl.isEnable=false;
        this.addLayer(Const.ngl);
        Const.esl.isEnable = false;
        this.addLayer(Const.esl);



        rsl = new RoleSelectLayer();
        rsl.isEnable = false;
        this.addLayer(rsl);

        ghl = new GuanghuanLayer();
        ghl.isEnable = false;
        this.addLayer(ghl);


        chl = new ChenghaoLayer();
        chl.isEnable = false;
        this.addLayer(chl);



        chickIsShowGift();


        SoundManger.share().playmusic("sound/mainBg.mp3");
        //System.out.println("标记位置1的耗时："+ (System.currentTimeMillis() -start)  );
    }

    public void refNumUI(){
        //设置玩家金币数，
        int uc = Const.getUserCoin();
        int []numc =  new int [6];
        numc[5] = uc / 100000 ;
        numc[4] = uc % 100000 / 10000;
        numc[3] = uc % 10000 / 1000;
        numc[2] = uc % 1000 / 100;
        numc[1] = uc % 100 /10;
        numc[0] = uc % 10;
        for(int i=0;i<6;i++){
            num2d[i].setNum(numc[i]);
            num2d[i].isEnable = true;
        }
        for(int i=5;i>0;i--){
            if(numc[i]==0){
                num2d[i].isEnable = false;
            }else{
                break;
            }
        }
    }

    Number2D[] num2d = new Number2D[6];
    /**
     * 设定为指定关卡等级
     * @param level
     */
    private void setLevel(int level){
        for(int i=0;i<5;i++){
            if(i==level){
                level_bg[i].isEnable = true;
                level_title[i].isEnable = true;
            }else{
                level_bg[i].isEnable = false;
                level_title[i].isEnable = false;
            }
        }
        if(level<=Const.max_level||level == 4){
            lock.isEnable = false;
        }else{
            lock.isEnable = true;
        }

        if(level == 4){
            zwkf.isEnable  = true;
        }else{
            zwkf.isEnable  = false;
        }

    }
    public String [] level_bg_name = new String []{"0","1","2","3","4"};
    Node2D level_bg[] = new Node2D[5];

    public String [] level_title_name = new String []{"tsfz","tcxc","tlyc","thtc","tjhc"};
    Node2D level_title[] = new Node2D[5];

    Node2D lock = null;//记录锁定地图的引用

    @Override
    public void update(){
        if(exitbj>0){
            exitbj--;
        }

    }

    @Override
    public void exitScene(){
        rsl.clear();
        ghl.clear();
        chl.clear();
        NumUtil.clear();
        Const.saveUserdef();
    }
    @Override
    public void event(TouchEventJ tej){
        if(tej.event== TouchEventI.TOUCH_EVENT_END){
            for(int i = chiles_2d.size()-1;i>=0;i--){
                Node2D dn = (Node2D)chiles_2d.get(i);
                if(dn.isEnable  && dn.node_type == Node2D.NODE_TYPE_BUTTON
                        &&  dn.isInRect(tej.tag_x,tej.tag_y)){
                    if("return".equals(dn.name)){
                        MainMenuScene mg = new MainMenuScene();
                        AppDelegate.share().replaceScene(mg);
                    }else if("into".equals(dn.name)){
                        if(Const.current_level>Const.max_level||Const.current_level == 4){
                            AppDelegate.share().lu.showMsg("通关前一关卡后解锁当前关卡。");
                            return;
                        }
                        switch (Const.current_level){
                            case 0:
                                Const.mapid = Map.sn06;
                                break;
                            case 1:
                                Const.mapid = Map.sn04;
                                break;
                            case 2:
                                Const.mapid = Map.sn19;
                                break;
                            case 3:
                                Const.mapid = Map.sn05;
                                break;
                            case 4:
                                Const.mapid = Map.sn06;            //Map.sn13;
                                break;
                        }
                        LoadScene ls =new LoadScene();
                        AppDelegate.share().replaceScene(ls);
                    }else if("btnequip".equals(dn.name)){
                        Const.esl.isEnable = true;
                        IsPause = true;
                    }else if("syg".equals(dn.name)){
                        Const.current_level--;
                        if(Const.current_level<0){
                            Const.current_level = 4;
                        }
                        setLevel(Const.current_level);
                    }else if("xyg".equals(dn.name)){
                        Const.current_level++;
                        if(Const.current_level>4){
                            Const.current_level = 0;
                        }
                        setLevel(Const.current_level);
                    }else if("btngift".equals(dn.name)){
                        Const.ngl.isEnable = true;
                        IsPause = true;
                    }else if("role".equals(dn.name)){
                        rsl.openLayer();
                    }else if("guangh".equals(dn.name)){
                        ghl.isEnable = true;
                        IsPause = true;
                    }else if("chengh".equals(dn.name)){
                        chl.isEnable = true;
                        IsPause = true;
                    }
                    return;
                }
            }
        }else if(tej.event == TouchEventI.KEY_BACK){
            if(exitbj>0){
                //退出游戏
                AppDelegate.share().lu.exit();
            }else{
                exitbj = 120;
                AppDelegate.share().lu.showMsg("再次点击返回键退出游戏。");
            }
        }
    }

    public int exitbj = 0;

    Node2D zwkf = null;
    Node2D btngift = null;
    private void chickIsShowGift(){
        if(Const.giftCanShow()){
            btngift.isEnable = true;
        }else{
            btngift.isEnable = false;
        }
    }
    @Override
    public void layerReturn(Layer.LayerParameter lp){
        if(lp.layerid ==  Const.layer_newgift || lp.layerid == Const.layer_equip || lp.layerid == Const.layer_role ){
            chickIsShowGift();
        }
        refNumUI();//刷新金币数字
    }
}
