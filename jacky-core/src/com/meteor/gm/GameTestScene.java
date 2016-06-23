package com.meteor.gm;

import com.jacky.engine.GenUITool;
import com.jacky.engine.TextureManger;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.*;
import com.jacky.start.AppDelegate;
import com.meteor.gm.element.*;
import com.meteor.gm.util.CommonAni;
import com.meteor.gm.util.Const;
import com.meteor.gm.util.NumUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class GameTestScene extends Scene {

    //public static GameTestScene gs = null;
    public static Role hero;
    public static RoleRobot[] rolerobot = new RoleRobot[30];


    public Node2D move2dNode = null;
    public Node2D sxzy[] = new Node2D[4];//上下左右4个按钮引用
    public Node2D[] currxl = new Node2D[4];//当前血量的4个数字引用
    public Node2D[] zongxl = new Node2D[4];//总血量的4个数字引用

    public ProgressBar pb;


    public int  loadnumber = 0;


    String head_str = null;


    PauseLayer pl ;
    GKEndLayer gke;

    public int gametime = 0;//游戏进行的帧数
    public float loadScene(){
        uuid = Const.scene_gametest;

        if(loadnumber == 0){
            CommonAni.load();
            loadnumber = 2;
            gametime = 0;
        }else if(loadnumber == 2){
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

            //初始化数字纹理图片
            NumUtil.init();
            loadnumber = 4;
        }else if(loadnumber == 4){
            //添加地图
            Map.addMap2Scene(this,Const.mapid);

            pl = new PauseLayer();
            pl.isEnable = false;
            this.addLayer(pl);

            gke = new GKEndLayer();
            gke.isEnable = false;
            this.addLayer(gke);


            loadnumber = 6;
        }else if(loadnumber == 6){
            //初始化2DUI部分
            List<Node2D> r2d = GenUITool.gen2d("maingame.xui");
            for (int i = 0; i < r2d.size(); i++){
                Node2D node2dtmp = r2d.get(i);
                if ("shang".equals(node2dtmp.name)){
                    node2dtmp.isEnable = false;
                    sxzy[0] = node2dtmp;
                } else if ("xia".equals(node2dtmp.name)){
                    node2dtmp.isEnable = false;
                    sxzy[1] = node2dtmp;
                } else if ("zuo".equals(node2dtmp.name)){
                    node2dtmp.isEnable = false;
                    sxzy[2] = node2dtmp;
                } else if ("you".equals(node2dtmp.name)){
                    node2dtmp.isEnable = false;
                    sxzy[3] = node2dtmp;
                } else if ("anniu".equals(node2dtmp.name)){
                    move2dNode = node2dtmp;
                }else if ("liftbar".equals(node2dtmp.name)){
                    pb = (ProgressBar)node2dtmp;
                }else if("q_1".equals(node2dtmp.name)){
                    currxl[0] = node2dtmp;
                }else if("q_2".equals(node2dtmp.name)){
                    currxl[1] = node2dtmp;
                }else if("q_3".equals(node2dtmp.name)){
                    currxl[2] = node2dtmp;
                }else if("q_4".equals(node2dtmp.name)){
                    currxl[3] = node2dtmp;
                }else if("h_1".equals(node2dtmp.name)){
                    zongxl[0] = node2dtmp;
                }else if("h_2".equals(node2dtmp.name)){
                    zongxl[1] = node2dtmp;
                }else if("h_3".equals(node2dtmp.name)){
                    zongxl[2] = node2dtmp;
                }else if("h_4".equals(node2dtmp.name)){
                    zongxl[3] = node2dtmp;
                }else if("tmphead".equals(node2dtmp.name)){
                   if(Const.roleid == Role.role_hun || Const.roleid == Role.role_hunheke ||  Const.roleid == Role.role_hunlongwen){
                       head_str = "xingr.png";
                   }else if(Const.roleid == Role.role_weizhinv){//高老大
                       head_str = "pingr.png";
                   }
                   if(head_str != null){
                       node2dtmp.textureId =  TextureManger.getTexture(head_str);
                   }

                }else if("gift".equals(node2dtmp.name)){
                     giftbtn  = node2dtmp;
                }else if("s1".equals(node2dtmp.name)){
                    skill_button[0] = node2dtmp;
                }else if("s2".equals(node2dtmp.name)){
                    skill_button[1] = node2dtmp;
                }else if("s3".equals(node2dtmp.name)){
                    skill_button[2] = node2dtmp;
                }else if("s1lq".equals(node2dtmp.name)){
                    skill_coolimg[0] = node2dtmp;
                }else if("s2lq".equals(node2dtmp.name)){
                    skill_coolimg[1] = node2dtmp;
                }else if("s3lq".equals(node2dtmp.name)){
                    skill_coolimg[2] = node2dtmp;
                }else if("s1num".equals(node2dtmp.name)){
                    skill_num[0] = node2dtmp;
                }else if("s2nums".equals(node2dtmp.name)){
                    skill_num[1] = node2dtmp;
                }else if("s2numg".equals(node2dtmp.name)){
                    skill_num[2] = node2dtmp;
                }else if("s3nums".equals(node2dtmp.name)){
                    skill_num[3] = node2dtmp;
                }else if("s3numg".equals(node2dtmp.name)){
                    skill_num[4] = node2dtmp;
                }else if("zss".equals(node2dtmp.name)){
                    dirensl[0] = node2dtmp;
                }else if("zsgw".equals(node2dtmp.name)){
                    dirensl[1] = node2dtmp;
                }else if("sysw".equals(node2dtmp.name)){
                    dirensl[2] = node2dtmp;
                }else if("sygw".equals(node2dtmp.name)){
                    dirensl[3] = node2dtmp;
                }else if("lence".equals(node2dtmp.name)){
                    lenre = node2dtmp;
                    lenre.isEnable = Const.isShowlenre;
                }
                addChile2D(node2dtmp);
            }

            chickIsShowGift();//检测礼包是否可以展示
            loadnumber = 8;
        }else if(loadnumber == 8){
            addRobot();
            loadnumber = 10;
        }else if(loadnumber == 10){
            //添加主角
            hero = new Role(Const.roleid,Role.camp_liuxing);
            addChile(hero);
            hero.setWeapon(Const.weaponid);
            hero.setCb(Const.cbid);
            int aatmp = Const.getCh();
            if(aatmp!=-1){
                hero.setCH(aatmp);
            }
            hero.IsHero = true;
            setHerozxl(hero.ALLHP);
            setHerocxl(hero.HP);
            hero.setPoint(Map.scene_hero[Map.current_map][0],Map.scene_hero[Map.current_map][1]);
            chickRobotShow();
            AppDelegate.share().camera.setContentXYPoint(hero.pointX, hero.pointY);

            Const.ngl.isEnable=false;
            this.addLayer(Const.ngl);//添加礼包界面

            Const.esl.isEnable = false;
            this.addLayer(Const.esl);//添加装备选择界面


            chickSkillCool();

            loadnumber++;
        }else{
            loadnumber = -1;
            isInitFinish = true;
        }

        return loadnumber * 0.1f;
    }
    Node2D lenre = null;
    Node2D dirensl []= new  Node2D[4];//敌人数量数字引用


    /**
     * 向当前地图中添加机器人对象
     * 场景添加机器人逻辑在这里实现，注意需要先在场景中调用addMap2Scene添加一个地图后再调用该方法
     */
    private void addRobot(){
        //性能测试 小米1s 6个带翅膀角色同时大招 不掉帧  8个掉帧帧数 53左右
        //                 12个带翅膀角色招开始丢帧  帧数57以上
        //小米note 12个带翅膀角色同时大招不掉帧 满帧61帧
        int robotallnum = Map.robotinfo[Map.current_map].length/3;
        for(int i=0;i<robotallnum;i++){
            rolerobot[i] = new RoleRobot(Map.robotinfo[Map.current_map][i*3], Role.camp_hudie);
            rolerobot[i].setArea(Map.robotinfo[Map.current_map][i*3+2]);
            addChile(rolerobot[i]);
            rolerobot[i].setWeapon(Map.robotinfo[Map.current_map][i*3+1]);
            if(Map.robotinfo[Map.current_map][i*3] == Role.role_jrboss){
                rolerobot[i].setCb(ChiBang.chibang_fy);//肌肉boss设置上防御光环
            }else if(Map.robotinfo[Map.current_map][i*3] == Role.role_huiyiboss){
                rolerobot[i].setCb(ChiBang.chibang_gj);//灰衣boss设置上攻击光环
            }else if( Map.robotinfo[Map.current_map][i*3] == Role.role_hongyiboss){
                rolerobot[i].setCb(ChiBang.chibang_zj);//红衣服boss设置上主角光环
            }
        }

        //初始化右侧敌人总数 数字信息
        int sw = robotallnum%100 /10;
        int gw  = robotallnum %10;
        dirensl[0].textureId = NumUtil.red_[sw];
        dirensl[1].textureId = NumUtil.red_[gw];

        refDRnum();
    }
    //刷新剩余敌人数量文字
    public void refDRnum(){
        int allnum = 0;
        for(int i=0;i<rolerobot.length;i++) {
            if (rolerobot[i] == null) {
                continue;
            }
            if(rolerobot[i].issiwangtmp){
                continue;
            }
            allnum++;
        }
        int sw = allnum%100 /10;
        int gw  = allnum %10;
        dirensl[2].textureId = NumUtil.red_[sw];
        dirensl[3].textureId = NumUtil.red_[gw];
    }


    @Override
    public void initScene(){

    }

    //3个技能的冷却时间
    public int [] skill_cooling = new int [3];
    //3个有冷却时间的技能按钮
    public Node2D[] skill_button = new Node2D[3];
    //3个冷却时灰色图片的引用
    public Node2D[] skill_coolimg = new Node2D[3];
    //3个技能冷却时间的数字显示
    public Node2D[] skill_num = new Node2D[5];

    private int miaojsq = 0;//秒钟计时器，默认60帧为一秒钟

    @Override
    public void update() {
        if(idx == -1){
            idx = -2;
            hero.setAni(Role.ani_daoji);
        }else if(idx==3){
            float[] xyp = AppDelegate.share().camera.xiangqian(fjd);
            hero.setPoint(xyp[0], xyp[1]);chickRobotShow();
            AppDelegate.share().camera.setContentXYPoint(hero.pointX, hero.pointY);
        }

        miaojsq++;
        if(miaojsq == 60){//默认60帧为一秒，如果手机低性能卡顿则会出现，技能冷却时间很长
                            //测试期间小米1s 枭龙220即可达到60帧，不考虑性能比1s更差的手机
            miaojsq = 0;
            chickSkillCool();

        }

        if(fram_end_chick!=-1){
            fram_end_chick--;
            if(fram_end_chick == 0){
                chickGameState();
            }
        }

        gametime++;//游戏进行的总的帧数



    }
    private void chickSkillCool(){
        //检测技能冷却情况
        for(int i=0;i<3;i++){//检测并更新3个技能按钮状态
            if(skill_cooling[i]==0){
                skill_cooling[i]--;
                skill_button[i].isEnable = true;
                skill_coolimg[i].isEnable = false;

                if(i==0){
                    skill_num[0].isEnable = false;
                }else if(i==1){
                    skill_num[1].isEnable = false;
                    skill_num[2].isEnable = false;
                }else if(i==2){
                    skill_num[3].isEnable = false;
                    skill_num[4].isEnable = false;
                }



            }else if(skill_cooling[i]>0){

                skill_button[i].isEnable = false;
                skill_coolimg[i].isEnable = true;

                if(i==0){
                    skill_num[0].isEnable = true;
                    skill_num[0].textureId = NumUtil.red_[skill_cooling[i]];
                }else if(i==1){
                    int sw = skill_cooling[i] / 10;
                    int gw = skill_cooling[i] % 10;
                    if(sw>0){
                        skill_num[1].isEnable = true;
                        skill_num[1].textureId = NumUtil.red_[sw];
                    }else{
                        skill_num[1].isEnable = false;
                    }
                    skill_num[2].isEnable = true;
                    skill_num[2].textureId  = NumUtil.red_[gw];

                }else if(i==2){
                    int sw = skill_cooling[i] / 10;
                    int gw = skill_cooling[i] % 10;
                    if(sw>0){
                        skill_num[3].isEnable = true;
                        skill_num[3].textureId = NumUtil.red_[sw];
                    }else{
                        skill_num[3].isEnable = false;
                    }
                    skill_num[4].isEnable = true;
                    skill_num[4].textureId  = NumUtil.red_[gw];

                }

                skill_cooling[i]--;
            }
        }
    }
    /**
     * 检测机器人距离角色的距离，是否隐藏掉机器人更新等资源消耗
     */
    private void chickRobotShow(){
        for(int i=0;i<rolerobot.length;i++){
            if(rolerobot[i] == null){
                continue;
            }
            if(rolerobot[i].chickLength(hero)>280){
                rolerobot[i].isEnable = false;

                if(rolerobot[i].wp!=null){
                    rolerobot[i].wp.isEnable = false;
                }
                if(rolerobot[i].cbz!=null){
                    rolerobot[i].cbz.isEnable = false;
                }
                if(rolerobot[i].cby!=null){
                    rolerobot[i].cby.isEnable = false;
                }
            }else {
                rolerobot[i].isEnable = true;
                if(rolerobot[i].wp!=null){
                    rolerobot[i].wp.isEnable = true;
                }
                if(rolerobot[i].cbz!=null){
                    rolerobot[i].cbz.isEnable = true;
                }
                if(rolerobot[i].cby!=null){
                    rolerobot[i].cby.isEnable = true;
                }
            }
        }
    }
    @Override
    public void exitScene(){

        Const.addsaveKillnum(0,true);//退出场景实例化击杀数量

        pl.clear();
        gke.clear();
        Const.saveUserdef();
        NumUtil.clear();//清空小数字纹理图片
        TextureManger.clearTex(head_str);//手动清空头像纹理
    }

    /**
     * 角色移动事件变量缓存
     */
    public int idx = -1;

    /**
     * 人物移动事件触发唯一ID
     */
    public int moveId = -1;
    public float move_sx = 0;
    public float move_sy = 0;

    public float tmpdx = 0;
    public float tmpdy = 0;
    /**
     * 屏幕旋转事件属性缓存
     */
    public int rotate_id = -1;
    public float rotate_x = 0;

    float fjd = 0;


    @Override
    public void event(TouchEventJ tej){
        //System.out.println("事件唯一索引是："+tej.uuid+"事件类型："+tej.event+"事件目标位置XY坐标"+tej.tag_x+","+tej.tag_y);
        if (tej.event == TouchEventI.TOUCH_EVENT_START){
            if (move2dNode.isInRect(tej.tag_x, tej.tag_y)){//判断是否开始点击时落入移动事件按钮范围
                moveId = tej.uuid;
                move_sx = tej.tag_x;
                move_sy = tej.tag_y;
            } else {
                for (int i = chiles_2d.size() - 1; i >= 0; i--){
                    Node2D dn = (Node2D) chiles_2d.get(i);
                    if (dn.isEnable&&dn.node_type == Node2D.NODE_TYPE_BUTTON&&
                            dn.isInRect(tej.tag_x, tej.tag_y)){
                        if ("s0".equals(dn.name)){
                            hero.setSkill(0);
                            return;
                        } else if ("s1".equals(dn.name)){
                            if(hero.setSkill(1)){
                                skill_cooling[0] = 7;//第一个技能冷却5秒
                                chickSkillCool();
                            }
                            return;
                        } else if ("s2".equals(dn.name)){
                            if(hero.setSkill(2)){
                                skill_cooling[1] = 14;//第二个技能冷却7秒
                                chickSkillCool();
                            }
                            return;
                        } else if ("s3".equals(dn.name)){
                            if(hero.setSkill(3)){
                                skill_cooling[2] = 22;//第3个技能冷却7秒
                                chickSkillCool();
                            }
                            return;
                        }
                    }
                }
                if (tej.tag_y < 400){
                    rotate_id = tej.uuid;
                    rotate_x = tej.tag_x;
                }

            }
        } else if (tej.event == TouchEventI.TOUCH_EVENT_MOVE){
            if (moveId != -1 && move2dNode.isInRect(tej.tag_x, tej.tag_y)){//判断是否开始点击时落入移动事件按钮范围
                tmpdx = tej.tag_x - move_sx;                        //由每一帧渲染驱动人物走动
                tmpdy = tej.tag_y - move_sy;


                if(tmpdx==0&&tmpdy==0){

                }else{
                    double uiuey = -tmpdy/(Math.sqrt(tmpdx*tmpdx+ tmpdy*tmpdy));
                    if(uiuey<=1&&uiuey>=-1){
                        if(tmpdx>0){
                            fjd= (float)Math.acos(uiuey);
                        }else{
                            fjd= -(float)Math.acos(uiuey);
                        }
                    }

                }
                idx = 3;
                setYaogan(fjd);
            } else if (rotate_id != -1 && tej.tag_y < 400){//由触屏事件驱动游戏状态，滑动越快视角转动越快
                if (tej.tag_x > rotate_x){
                    AppDelegate.share().camera.role_rotate(true);
                } else if (tej.tag_x < rotate_x){
                    AppDelegate.share().camera.role_rotate(false);
                }
            }
        } else if (tej.event == TouchEventI.TOUCH_EVENT_END){
            if (tej.uuid == moveId){//取消角色移动
                idx = -1;
                moveId = -1;
                setYaogan(-10);
            } else if (tej.uuid == rotate_id){//取消旋转
                rotate_id = -1;
            }
            for (int i = chiles_2d.size() - 1; i >= 0; i--){
                Node2D dn = (Node2D) chiles_2d.get(i);
                if (dn.isEnable&&dn.node_type == Node2D.NODE_TYPE_BUTTON&&
                    dn.isInRect(tej.tag_x, tej.tag_y)){
                    if ("gift".equals(dn.name)){
                        Const.ngl.isEnable = true;//弹出新手礼包
                        pauseScene();
                    }else if ("fanhui".equals(dn.name)){
                        pl.isEnable = true;
                        pauseScene();
                    }else if("equip".equals(dn.name)){
                        Const.esl.isEnable = true;//弹出装备界面
                        pauseScene();
                    }

                    return;
                }
            }
        }else if(tej.event == TouchEventI.KEY_BACK){
            pl.isEnable = true;
            pauseScene();
        }

        //键盘事件监听

        if (tej.uuid == 87) {
            if (tej.event == 101) {//按下
                idx = 3;
                fjd = 0;
            } else if (tej.event == 100) {//弹起
                idx = -1;
            }
        } else if (tej.uuid == 65) {
            if (tej.event == 101) {//按下
                idx = 3;fjd = -1.57f;
            } else if (tej.event == 100) {//弹起
                idx = -1;
            }
        } else if (tej.uuid == 83) {
            if (tej.event == 101) {//按下
                idx = 3;fjd = 3.14f;
            } else if (tej.event == 100){//弹起
                idx = -1;
            }
        } else if (tej.uuid == 68) {
            if (tej.event == 101) {//按下
                idx = 3;fjd = 1.57f;
            } else if (tej.event == 100){//弹起
                idx = -1;
            }
        } else if (tej.uuid == 72){
            if (tej.event == 101){//技能
                hero.setSkill(0);
            }
        } else if (tej.uuid == 74){
            if (tej.event == 101){//技能
                if(skill_cooling[0]<=0){
                    if(hero.setSkill(1)){
                        skill_cooling[0] = 7;//第一个技能冷却5秒
                        chickSkillCool();
                    }
                }
            }
        } else if (tej.uuid == 75){
            if (tej.event == 101){//技能
                if(skill_cooling[1]<=0){
                    if(hero.setSkill(2)){
                        skill_cooling[1] = 14;//第一个技能冷却5秒
                        chickSkillCool();
                    }
                }
            }
        } else if (tej.uuid == 76){
            if (tej.event == 101){//技能
                if(skill_cooling[2]<=0){
                    if(hero.setSkill(3)){
                        skill_cooling[2] = 22;//第一个技能冷却5秒
                        chickSkillCool();
                    }
                }
            }
        } else if (tej.uuid == 79){ //旋转
            AppDelegate.share().camera.role_rotate(true);
        } else if (tej.uuid == 80){
            //旋转
            AppDelegate.share().camera.role_rotate(false);
        }else if(tej.uuid == 32){//空格键
            if (tej.event == 101){//按下
                hero.pause = !hero.pause;
            }
        }else if(tej.uuid == 77){//m按键

        }else if(tej.uuid == 78){//N按键

        }
        if(Const.isShowlenre){
            Const.isShowlenre = false;
            lenre.isEnable = false;
        }

    }
    //暂停当前场景
    private void pauseScene(){
        //暂停场景中2d  3d对象更新方法
        IsPause = true;

        //取消主角移动操作
        idx = -1;
        moveId = -1;
        setYaogan(-10);
    }

    Node2D giftbtn = null;
    private void chickIsShowGift(){
        if(Const.giftCanShow()){
            giftbtn.isEnable = true;
        }else{
            giftbtn.isEnable = false;
        }
    }

    @Override
    public void layerReturn(Layer.LayerParameter lp){
        if(lp.layerid ==  Const.layer_newgift || lp.layerid == Const.layer_equip || lp.layerid == Const.layer_role){
            chickIsShowGift();
        }
        if(lp.layerid == Const.layer_equip){//如果是装备选择界面返回，还需检测当前装备的武器并切换角色武器
           hero.setWeapon(Const.weaponid);
        }
    }

    /**
     * 设置主角当前血量
     * @param num
     */
    public void setHerocxl(int num){
        if(num<0){
            return;
        }
        int tmp  = num %10000 /1000;
        currxl[0].textureId = NumUtil.red_[tmp];
        tmp  = num %1000 /100;
        currxl[1].textureId = NumUtil.red_[tmp];
        tmp  = num %100 /10;
        currxl[2].textureId = NumUtil.red_[tmp];
        tmp  = num %10 ;
        currxl[3].textureId = NumUtil.red_[tmp];
        pb.setJD((float)num/(float)hero.ALLHP);
    }
    public int fram_end_chick = -1;
    public void chickGameState(){

        boolean robot_have_live = false;
        for(int i=0;i<rolerobot.length;i++){
            if(rolerobot[i]!=null&&!rolerobot[i].issiwangtmp){
                robot_have_live = true;
            }
        }
        if(!robot_have_live){//游戏通关成功
            gke.showLayer(true);
            pauseScene();
            return;
        }
        if(hero.issiwangtmp){//主角死亡游戏通关失败
            gke.showLayer(false);
            pauseScene();
            return;
        }
    }
    /**
     * 设置主角总血量
     * @param num
     */
    public void setHerozxl(int num){
        if(num<0){
            return;
        }
        int tmp  = num %10000 /1000;
        zongxl[0].textureId = NumUtil.red_[tmp];
        tmp  = num %1000 /100;
        zongxl[1].textureId = NumUtil.red_[tmp];
        tmp  = num %100 /10;
        zongxl[2].textureId = NumUtil.red_[tmp];
        tmp  = num %10 ;
        zongxl[3].textureId = NumUtil.red_[tmp];
    }

    /**
     * 设置摇杆展示状态
     */
    private void setYaogan(float hd){
        int idd = -1;
        if(hd<-5){

        }else if(hd>=-1.04 &&hd<1.04f){//向上
            idd = 0;
        }else if(hd>=1.04f&& hd <2.08f){//向右
            idd = 3;
        }else if(hd>=-2.08&&hd<-1.04){//向左
            idd = 2;
        }else if(hd<-2.08||hd>=2.08){//向下
            idd = 1;
        }

        for (int i = 0; i < sxzy.length; i++){
            if (idd == i){
                sxzy[i].isEnable = true;
            } else {
                sxzy[i].isEnable = false;
            }
        }
    }
}
