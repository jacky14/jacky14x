package com.meteor.gm.element;

import com.jacky.engine.math.Matrix4f;
import com.jacky.engine.sound.SoundManger;
import com.jacky.engine.viewnode.Node;
import com.jacky.engine.viewnode.Particle;
import com.jacky.engine.viewnode.Scene;
import com.jacky.engine.viewnode.Sprite;
import com.jacky.start.AppDelegate;
import com.meteor.gm.GameTestScene;
import com.meteor.gm.util.CommonAni;
import com.meteor.gm.util.Const;
import com.meteor.gm.util.NumUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 人物角色类，
 * Created by Administrator on 2016/3/4.
 */
public class Role extends Node {

    //===============人物战斗模块数据处理=================================
    public int ALLHP = 2000;//人物的总血量
    public int HP = 2000;//人物当前的血量

    //===============人物战斗模块数据处理end===============================
    public static float map_hight = 0;
    public float allhightt = 0;
    /**
     * 当前人物的名称
     */
    public String name;

    /**
     * 角色所属阵营，详情见 Role.camp_liuxing
     */
    public int camp;

    /**
     * 角色人物标识，详情见{ role_hun}
     */
    public int role;
    /**
     * 角色人物模型贴图信息【{ role_hun}】【mode name，texture name】
     */
    public static String [][] role_info=new String[][]{
            {"p0.jxb","p0.jpg"},
            {"p1.jxb","p1.jpg"},
            {"p2.jxb","p2.jpg"},
            {"p3.jxb","p3.jpg"},
            {"p4.jxb","p4.jpg"},
            {"p7.jxb","p7.jpg"},
            {"p8.jxb","p8.jpg"},
            {"p9.jxb","p9.jpg"},
            {"p10.jxb","p10.jpg"},
            {"p13.jxb","p13.jpg"},
            {"p15.jxb","p15.jpg"},
            {"p16.jxb","p16.jpg"},
            {"p17.jxb","p17.jpg"},
            {"p19.jxb","p19.jpg"},
            {"p0.jxb","p0_1.jpg"},
            {"p0.jxb","p0_2.jpg"},
            {"p1.jxb","p1_1.jpg"},
    };
    //======================角色人物标识=====================
    public static final int role_hun = 0 ;//孟星魂角色
    public static final int role_yan = role_hun + 1 ;//冷燕角色    ￥
    public static final int role_dahuzi = role_yan + 1 ;//大胡子角色
    public static final int role_yemaozi = role_dahuzi + 1 ;//夜猫子角色
    public static final int role_huaheshang = role_yemaozi + 1 ;//花和尚角色
    public static final int role_jrboss = role_huaheshang + 1 ;//肌肉boss角色
    public static final int role_changqiang = role_jrboss + 1 ;//长枪兵角色
    public static final int role_lyhaohan = role_changqiang + 1 ;//绿衣好汉王强角色
    public static final int role_wuming = role_lyhaohan + 1 ;//无名角色
    public static final int role_gaolaoda = role_wuming + 1 ;//凤凰角色
    public static final int role_hongyiboss = role_gaolaoda + 1 ;//红衣壮汉boss角色
    public static final int role_huiyiboss = role_hongyiboss + 1 ;//灰色衣服boss角色
    public static final int role_weizhinv = role_huiyiboss + 1 ;//高老大女角色 ￥
    public static final int role_hongyijs = role_weizhinv + 1 ;//红衣剑高手

    public static final int role_hunheke = role_hongyijs + 1 ;//孟星魂黑客套装  $
    public static final int role_hunlongwen = role_hunheke + 1 ;//孟星魂龙纹套装 $
    public static final int role_yanlongwen = role_hunlongwen + 1 ;//冷燕龙纹套装  $
    //======================角色人物标识=====================

    /**
     * 当前动画索引,人物状态
     */
    public static final int ani_daoji = 0 ; //待机
    public static final int ani_paodong = ani_daoji+ 1;//跑动
    public static final int ani_qiehuan = ani_paodong + 1;//切换武器
    public static final int ani_houfan = ani_qiehuan+1 ;//被击中后向后翻转一圈倒地
    public static final int ani_houyang = ani_houfan+1;//被击中后向后仰
    public static final int ani_kill = ani_houyang+1;//被击杀死亡动作

    public static final int ani_dao_s0 = ani_kill+1;//刀技能
    public static final int ani_dao_s1 = ani_dao_s0+1;
    public static final int ani_dao_s2 = ani_dao_s1+1;
    public static final int ani_dao_s3 = ani_dao_s2+1;

    public static final int ani_bishou_s0 = ani_dao_s3+1;//匕首技能
    public static final int ani_bishou_s1 = ani_bishou_s0+1;
    public static final int ani_bishou_s2 = ani_bishou_s1+1;
    public static final int ani_bishou_s3 = ani_bishou_s2+1;

    public static final int ani_jian_s0 = ani_bishou_s3+1;//剑技能
    public static final int ani_jian_s1 = ani_jian_s0+1;
    public static final int ani_jian_s2 = ani_jian_s1+1;
    public static final int ani_jian_s3 = ani_jian_s2+1;

    public static String role_path_root = "role/";


    /**
     * 当前角色是否是英雄角色
     */
    public boolean IsHero = false;


    /**
     * 人物旋转角度
     */
    public float role_angle = 0;
    public float role_tag_angle = 0;
    public float role_offset_angle = 25;


    float weapon_marix[][];//当前人物右手武器矩阵信息
    public Role(int role_,int camp_){
        super(role_path_root+role_info[role_][0],role_path_root+role_info[role_][1] ,"role/commonani/base.jxbb");
        camp = camp_;
        role = role_;
        weapon_marix = CommonAni.allani[CommonAni.base_dum];
        allhightt = map_hight;

        this.transfe = new Matrix4f();
        this.transfe.translate(0,0,allhightt);
       //初始化特效模块
       for(int i=0;i<lizi.length;i++){
            lizi[i] = new Sprite("eff/lizi.jxb", Particle.textures[i]);
            lizi[i].isTranslucence = true;
            lizi[i].setTexMode(1);
            lizi[i].transfe= new Matrix4f();
            lizi[i].isEnable = false;
       }
       xuanzhuan = new Sprite("eff/xuanzhuan.jxb","eff/track.jpg");
       xuanzhuan.isTranslucence = true;
       xuanzhuan.setTexMode(1);
       xuanzhuan.transfe = new Matrix4f();
       xuanzhuan.isEnable = false;

       boom = new Sprite("eff/xuanzhuan.jxb","eff/zchza400b.jpg"); // ZCHZ1000.jpg
       boom.isTranslucence = true;
       boom.setTexMode(1);
       boom.transfe = new Matrix4f();
       boom.isEnable = false;

       //刀武器大招剑气
       for(int i=0;i<daoqi.length;i++){
           daoqi[i] = new Sprite("eff/xuanzhuan.jxb",daoqieff[i]);
           daoqi[i].isTranslucence = true;
           daoqi[i].setTexMode(1);
           daoqi[i].transfe= new Matrix4f();
           daoqi[i].isEnable = false;
       }

        bishoudz = new Sprite("eff/bishouef.jxb","eff/FLASH001.jpg");
        bishoudz.isTranslucence = true;
        bishoudz.setTexMode(1);
        bishoudz.transfe = new Matrix4f();
        bishoudz.isEnable = false;

        for(int j=0;j<shuzi.length;j++){
            shuzi[j] = new Sprite("eff/lizi.jxb","number/red/0.png");
            shuzi[j] .isTranslucence = true;
            shuzi[j] .transfe = new Matrix4f();
            shuzi[j] .isEnable = false;
        }
        fuhao = new Sprite("eff/lizi.jxb","number/red/10.png");
        fuhao.isTranslucence = true;
        fuhao.transfe = new Matrix4f();
        fuhao.isEnable = false;
    }
    //符号
    Sprite fuhao ;
    //数字
    Sprite shuzi[] = new Sprite[3];//百位




    //匕首大招
    Sprite bishoudz = null;

    //刀武器大招剑气
    Sprite daoqi[] = new Sprite[4];
    String daoqieff[] = new String []{
            "eff/KANGL001.jpg",
            "eff/KANGL00.jpg",
            "eff/ZSD.jpg",
            "eff/ZSD1.jpg"
    };

    //脚下旋转特效的引用
    Sprite xuanzhuan = null;
    Sprite lizi[] = new Sprite[4];//创建4个粒子对象~1~
    //剑大招爆掉后特效
    Sprite boom = null;



    //人物的位置
    public float pointX = 0;
    public float pointY = 0;

    //人物即将移动到的目标位置
    public float tag_pointX = 0 ;
    public float tag_pointY = 0 ;

    /**
     * 指定某些动作在执行时是可以移动的动作
     *
     */
    public static int[] ismove_ani = new int[]{
        ani_paodong,ani_daoji,-1,ani_dao_s3
    };
    boolean is_sure_move = false;
    //328,400
    //设置人物的位置
    public void setPoint(float x,float y){

        is_sure_move = false;
        for(int i=0;i<ismove_ani.length;i++){
            if(current_aniid==ismove_ani[i]){
                if(current_aniid==ani_dao_s3){
                    if(frameidx>328&&frameidx<400){//刀武器大招时部分帧人物可以移动
                        is_sure_move = true;
                    }
                }else{
                    is_sure_move = true;
                }
                continue;
            }
        }
        if(is_sure_move){
            role_tag_angle = (float)(Math.atan2(x,-y)* Const.HD2JD);

            tag_pointX = this.pointX + x;
            tag_pointY = this.pointY + y;
            if(Map.chick_edge(tag_pointX,tag_pointY)){
                pointX = tag_pointX;
                pointY = tag_pointY;
                this.transfe.reinit();
                this.transfe.translate(this.pointX,this.pointY,allhightt);
            }
            setAni(ani_paodong);
        }

    }

    /**
     * 人物当前使用的武器
     */
    public Weapon wp = null;
    public void setWeapon(int id){
        if(bak_wid!=id){
            bak_wid = id;
            if(wp==null){
                wp = new Weapon(id);
                if(this.rescene!=null){
                    this.rescene.addChile(wp);
                    setAni(ani_daoji);
                }else{
                    System.out.println("警告：武器并未添加到场景中去，请先将人物添加到场景再调用该方法");
                }
            }else{
                effclose();
                current_aniid = ani_daoji;
                setAni(ani_qiehuan);
            }
        }
    }
    float angle = 0;
    public Sprite cbz=null;
    public Sprite cby=null;

    public int rolecb = ChiBang.chibang_null;//当前人物携带的翅膀
    /**
     * 给人物设置翅膀
     */
    public void setCb(int idx){
        if(idx >=ChiBang.chibang_gj &&  idx<=ChiBang.chibang_zj){

            rolecb = idx;
            cbz=new Sprite("cbz.jxb", ChiBang.cbstr[idx]);
            cbz.isTranslucence = true;
            cbz.setTexMode(1);
            cbz.transfe = new Matrix4f();
            this.rescene.addTranslucenceChile(cbz);

            cby=new Sprite("cby.jxb", ChiBang.cbstr[idx]);
            cby.isTranslucence = true;
            cby.setTexMode(1);
            cby.transfe = new Matrix4f();
            this.rescene.addTranslucenceChile(cby);
        }
    }
    Sprite bt = null;
    /**
     * 人物设置称号
     */
    public void setCH(int id){
        bt = new Sprite("bt.jxb",Const.chstr[id]);
        bt.isTranslucence = true;
        bt.transfe = new Matrix4f();
        this.rescene.addTranslucenceChile(bt);
    }

    /**
     * 攻击伤害范围列表，这里记录攻击的范围
     * 各个值意义技能 伤害的范围，技能伤害的角度
     *
     * 注意当机器人距离主角过近时无论是否在指定角度内一定会受到主角技能伤害，
     * 而主角距离机器人过近则不会受到伤害
     */
    public static final int [][] attinfo = new int[][]{
            {40,50},{40,40},{40,70},{50,-1},
            {30,40},{35,26},{30,35},{45,35},
            {40,40},{40,50},{40,40},{50,-1}
    };
    /**
     * 每一个技能中，对应第几的帧数会产生攻击力。
     */
    public static final int [][] attFram = new int[][]{
            {20},{105},{190},{328,336,349,378,385,400,465},
            {16},{65},{143,157,173},{ 240},
            {12},{45,59,72},{137,146,161},{207,218,238,254,261}
    };
    /**
     * 每个技能的攻击力数值
     */
    public static final int [] attvalue = new int []{
            178,220,240,110,
            182,198,78,650,
            185,73,82,140
    };


    /**
     * 对指定人物使用某个技能进行了伤害
     * @param skid
     * @param r
     */
    public void skillatt(int skid,Role r){
        if(!r.issiwangtmp){//角色不能是已经死亡状态

            int hnum = 0 ;//这次攻击对对方照成的伤害值

            if(IsHero){//英雄具备吸血功能
                float cbbl = 1;
                if(rolecb==ChiBang.chibang_gj|| rolecb==ChiBang.chibang_zj){
                    cbbl = 1.4f;
                }
                //主角最终的伤害值 = 技能基础值*武器伤害比率*光环比率
                hnum = (int)(attvalue[skid - ani_dao_s0] * Weapon.weaponrate[wp.wid] * cbbl) ;
                //英雄产生吸血效果
                int zj =(int)(hnum*0.2);
                HP += zj;
                if(HP>2000){ HP = 2000;}//血量不能超过最大值
                showCache.add(new int[]{zj,0});

                ((GameTestScene)rescene).setHerocxl(HP);
                //hnum = 2000;
            }else{
                //非英雄人物对英雄照成的伤害 基础值下降  不同场景下降比率不同
                hnum = (int)(attvalue[skid- ani_dao_s0] * Map.robotrate);
            }

            int rm = (int)(Math.random()*14)-7;

            r.hurt(hnum+rm);//最终伤害值随机

            int uiiu = (int)(Math.random()*3);
            if(uiiu==0){
                SoundManger.share().playsound("sound/skill0.mp3");
            }else if(uiiu==1){
                SoundManger.share().playsound("sound/hurt0.mp3");
            }else if(uiiu==2){
                SoundManger.share().playsound("sound/skill1.mp3");
            }
        }
    }
    /**
     * 当前人物收到了hp点伤害
     * @param hp
     */
    public void hurt(int hp){


        if(rolecb==ChiBang.chibang_fy|| rolecb==ChiBang.chibang_zj){
            hp= (int)(hp*0.65f);//主角携带主角或防御光环，伤害降低35%
        }

        showCache.add(new int[]{hp,1});
        HP -= hp;
        if(HP<=0){//有角色死亡，通知场景更新游戏状态
            GameTestScene gts = (GameTestScene)this.rescene;
            if(IsHero){
                gts.setHerocxl(0);
            }else{
                Const.addsaveKillnum(1,false);
            }
            effclose();//关闭所有特效
            issiwangtmp = true;//这个状态鱼pause区别，这个代表立即死亡系统检测到角色立刻会死亡，pause是死亡动画执行完成后生效
            current_aniid = ani_daoji;
            setAni(ani_kill);
            gts.fram_end_chick = 220;//角色死亡，检测游戏场景状态
            gts.refDRnum();//刷新右侧数字
            return;
        }

        if(IsHero){
            ((GameTestScene)this.rescene).setHerocxl(HP);
            if(hp>40){
                if(Math.random()>0.8){
                    setAni(ani_houfan);
                }else{
                    setAni(ani_houyang);
                }
            }else{
                setAni(ani_houyang);
            }
        }else{//为提高机器人的反击能力，机器人有50%的可能在受到伤害时不执行受伤动作
            if(Math.random()>0.5){
                if(Math.random()>0.6){
                    setAni(ani_houfan);
                }else{
                    setAni(ani_houyang);
                }
            }
        }
        //SoundManger.share().playsound("sound/hurt0.mp3");
    }


    /**
     * 帧数
     */
    public static final int [][] ani_info = new int[][]{
            {1,60},{62,79},{80,108},{108,131},{131,148},{148,191},
            {0,60},{67,150},{160,260},{270,510},
            {0,51},{52,103},{103,219},{ 219,272},
            {0,34},{34,107},{107,193},{193,292}
    };

    public int current_aniid = -1;




    private int bak_wid = -1;
    /**
     * 人物发动技能 0 1 2 3
     * @param id
     */
    public boolean setSkill(int id){
        if(wp!=null){
            switch (wp.type){
                case Weapon.weapon_type_dao:
                    return setAni(6+id);

                case Weapon.weapon_type_jian:
                    return setAni(14+id);

                case Weapon.weapon_type_bishou:
                    return setAni(10+id);
            }
        }
        return false;
    }

    /**
     * 设置当前人物动画，idx动画索引
     * @param idx
     */
    public boolean setAni(int idx){
        if(current_aniid == idx){//如果已经在执行该动作则不做任何操作
            return false;
        }
        if(current_aniid>=ani_qiehuan){//除了奔跑待机动作执行期间可以更换为其他动作，受伤，技能动作一定会执行完成后自动切换为待机
            return false;
        }
        if(idx==ani_daoji&&wp!=null){//当手中有武器时，待机状态根据武器不同待机姿态不同

            switch (wp.type){
                case Weapon.weapon_type_dao:
                    this.maxtriS = CommonAni.allani[CommonAni.dao_bone];
                    this.weapon_marix = CommonAni.allani[CommonAni.dao_dum];
                    frameidx = 60;
                    break;
                case Weapon.weapon_type_jian:
                    this.maxtriS = CommonAni.allani[CommonAni.jian_bone];
                    this.weapon_marix = CommonAni.allani[CommonAni.jian_dum];

                    frameidx = 34;
                    break;
                case Weapon.weapon_type_bishou:
                    this.maxtriS = CommonAni.allani[CommonAni.bishou_bone];
                    this.weapon_marix = CommonAni.allani[CommonAni.bishou_dum];
                    frameidx = 51;
                    break;
            }
            current_aniid = idx;
            return true;//待机动作特殊处理
        }
        if(idx<ani_dao_s0){//基本动作
            this.maxtriS = CommonAni.allani[CommonAni.base_bone];
            this.weapon_marix = CommonAni.allani[CommonAni.base_dum];
        }else if(idx<ani_bishou_s0){//武器刀动作
            this.maxtriS = CommonAni.allani[CommonAni.dao_bone];
            this.weapon_marix = CommonAni.allani[CommonAni.dao_dum];
        }else if(idx<ani_jian_s0){//武器匕首动作
            this.maxtriS = CommonAni.allani[CommonAni.bishou_bone];
            this.weapon_marix = CommonAni.allani[CommonAni.bishou_dum];
        }else{//武器剑动作
            this.maxtriS = CommonAni.allani[CommonAni.jian_bone];
            this.weapon_marix = CommonAni.allani[CommonAni.jian_dum];
        }
        current_aniid = idx;
        frameidx = ani_info[current_aniid][0];

        //如果开始释放剑武器大招
        if(current_aniid == ani_jian_s3){
            boom_scale = 0.1f;
            xuanzhuan.isEnable = true;
            for(int i=0;i<lizi.length;i++){
                lizi[i].isEnable = true;
            }
        }else if(current_aniid == ani_dao_s3 || current_aniid==ani_bishou_s3){//刀武器大招
            for(int i=0;i<lizi.length;i++){
                lizi[i].isEnable = true;
            }
        }
        return true;
    }

    public boolean pause = false;
    float cbtmp = 0.5f;

    Matrix4f tmpp =new Matrix4f();
    Matrix4f weaponMatrix =new Matrix4f();

    private int ffffffidx = 0;


    public void effclose(){
        //设置新武器若之前在释放大招状态，隐藏所有大招特效
        boom.isEnable = false;
        xuanzhuan.isEnable = false;
        bishoudz.isEnable = false;
        for(int i=0;i<lizi.length;i++){
            lizi[i].isEnable = false;
        }
        for(int i=0;i<daoqi.length;i++){
            daoqi[i].isEnable = false;
        }

        fuhao.isEnable = false;
        for(int i=0;i<shuzi.length;i++){
            shuzi[i].isEnable = false;
        }

    }


    @Override
    public void update(){
        if(pause){
            //System.out.println("hero pause ----------" + frameidx);
            return;
        }
        //current_aniid = Role.ani_daoji;  //截取人物模型时方法
        if(current_aniid==ani_qiehuan && frameidx == 94){
            wp.reId(Const.weaponid);
        }



        //计算人物面朝向旋转角度
        if(role_angle>180){
            role_angle = role_angle - 360;
        }else if(role_angle<-180){
            role_angle = role_angle + 360;
        }
        float tmp_offset_angle = role_tag_angle-role_angle;
        if(tmp_offset_angle>role_offset_angle){
            if(tmp_offset_angle>180){
                role_angle -=role_offset_angle;
            }else{
                role_angle +=role_offset_angle;
            }
        }else if(tmp_offset_angle<-role_offset_angle){
            if(tmp_offset_angle<-180){
                role_angle +=role_offset_angle;
            }else{
                role_angle -=role_offset_angle;
            }
        }else if(tmp_offset_angle!=0){
            role_angle = role_tag_angle;
        }
        this.transfe.rotate(role_angle,0,0,1);
        //System.out.println("当前人物的角度是"+role_angle);
        //更新人物动画
        if(frameidx>=ani_info[current_aniid][1]){
            if(current_aniid>=ani_qiehuan){
                //这里说明剑大招刚刚释放完毕
                if(current_aniid == ani_jian_s3){
                    xuanzhuan.isEnable = false;
                    for(int i=0;i<lizi.length;i++){
                        lizi[i].isEnable = false;
                    }
                }else if(current_aniid == ani_dao_s3 || current_aniid==ani_bishou_s3){//刀武器大招
                    for(int i=0;i<lizi.length;i++){
                        lizi[i].isEnable = false;
                    }
                }else if(current_aniid==ani_kill){//人物死亡动画自行完毕
                    pause = true;
                    return;
                }
                //其他任意动作执行完成，自动回到待机动作
                current_aniid = ani_paodong;
                setAni(ani_daoji);
            }else{
                frameidx = ani_info[current_aniid][0];
            }
        }

        if(current_aniid != ani_daoji){

            if(current_aniid==ani_dao_s0||current_aniid==ani_bishou_s0||current_aniid==ani_jian_s0){
                ffffffidx=2;
            }else{
                ffffffidx++;
            }
            if(ffffffidx%2==0){
                frameidx++;

                if(current_aniid == ani_kill){//人物死亡后高度逐渐降低到地面
                    allhightt = map_hight - (frameidx-148)*0.4f;
                    this.transfe.reinit();
                    this.transfe.translate(this.pointX,this.pointY,allhightt);
                    this.transfe.rotate(role_angle,0,0,1);
                }else

                //这里判断攻击动作执行到了哪一帧，处理当前的伤害值
                if(current_aniid>=ani_dao_s0){
                    for(int j=0;j<attFram[current_aniid-ani_dao_s0].length;j++){
                        if(attFram[current_aniid-ani_dao_s0][j] == frameidx){
                            if(IsHero){//如果当前角色是英雄则循环检测所有机器人是否受到伤害
                                for(int i = 0; i< GameTestScene.rolerobot.length; i++){
                                    if(GameTestScene.rolerobot[i] == null){
                                        continue;
                                    }
                                    if(!GameTestScene.rolerobot[i].isEnable){
                                        continue;
                                    }
                                    float llx = GameTestScene.rolerobot[i].pointX - pointX;
                                    float lly = GameTestScene.rolerobot[i].pointY - pointY;

                                    if(llx>-20&& llx<20&& lly>-20 && lly<20){//距离角色太近直接收到伤害
                                        skillatt(current_aniid, GameTestScene.rolerobot[i]);
                                    }else if(llx>-attinfo[current_aniid - ani_dao_s0][0]&& llx<attinfo[current_aniid - ani_dao_s0][0]
                                            && lly>-attinfo[current_aniid - ani_dao_s0][0] && lly<attinfo[current_aniid - ani_dao_s0][0]){
                                        if(attinfo[current_aniid - ani_dao_s0][1] == -1){
                                            //GameTestScene.rolerobot[i].hurt(235);
                                            skillatt(current_aniid, GameTestScene.rolerobot[i]);
                                        }else{
                                            float tmpllang =  (float)(Math.atan2(llx,-lly)* Const.HD2JD) - role_angle;
                                            if((tmpllang>-attinfo[current_aniid - ani_dao_s0][1]&& tmpllang<attinfo[current_aniid - ani_dao_s0][1])
                                                    || tmpllang<(attinfo[current_aniid - ani_dao_s0][1]-360)||
                                                    tmpllang>(360 - attinfo[current_aniid - ani_dao_s0][1])){

                                                skillatt(current_aniid, GameTestScene.rolerobot[i]);
                                            }
                                        }

                                    }

                                }
                            }else{//如果是机器人则检测英雄人物是否受到伤害
                                float llx = GameTestScene.hero.pointX - pointX;
                                float lly = GameTestScene.hero.pointY - pointY;
                                if(llx>-attinfo[current_aniid - ani_dao_s0][0]&& llx<attinfo[current_aniid - ani_dao_s0][0]
                                        && lly>-attinfo[current_aniid - ani_dao_s0][0] && lly<attinfo[current_aniid - ani_dao_s0][0]){
                                    if(attinfo[current_aniid - ani_dao_s0][1] == -1){
                                        skillatt(current_aniid,  GameTestScene.hero);
                                    }else{
                                        float tmpllang =  (float)(Math.atan2(llx,-lly)* Const.HD2JD) - role_angle;
                                        if((tmpllang>-attinfo[current_aniid - ani_dao_s0][1]&& tmpllang<attinfo[current_aniid - ani_dao_s0][1]) ||
                                        tmpllang<(attinfo[current_aniid - ani_dao_s0][1]-360)||
                                        tmpllang>(360 - attinfo[current_aniid - ani_dao_s0][1])){
                                            skillatt(current_aniid,  GameTestScene.hero);
                                        }
                                    }

                                }

                            }
                        }

                    }
                }




            }
        }
        //人物武器和人物整体变换矩阵向乘
        if(wp!=null){
            tmpp.reinit();
            tmpp.translate(pointX,pointY,allhightt);
            tmpp.rotate(role_angle,0,0,1);
            weaponMatrix.reinit();
            System.arraycopy(weapon_marix[frameidx], 0, weaponMatrix.matrix, 0, 16);
            weaponMatrix.multiplyMM(tmpp);
            wp.transfe = weaponMatrix;
        }
        //int bindBone = 24 ;//将翅膀绑定到第3根骨骼上位移24个下表处是第3根骨骼脊椎骨
        //人物翅膀需要与人物整体变换矩阵想乘
        if(cby!=null){
            if(angle>25){
                cbtmp = -0.5f;
            }else if(angle<-5){
                cbtmp = 0.5f;
            }
            angle+=cbtmp;
            cby.transfe.reinit();
            cby.transfe.rotate(angle,1,0,0);

            tmpp.reinit();

            tmpp.matrix[0] = maxtriS[frameidx][24];   tmpp.matrix[1] = maxtriS[frameidx][28];    tmpp.matrix[2] = maxtriS[frameidx][32];
            tmpp.matrix[4] =maxtriS[frameidx][25];     tmpp.matrix[5]= maxtriS[frameidx][29];     tmpp.matrix[6] = maxtriS[frameidx][33];
            tmpp.matrix[8] =maxtriS[frameidx][26];     tmpp.matrix[9]= maxtriS[frameidx][30];     tmpp.matrix[10] = maxtriS[frameidx][34];
            tmpp.matrix[12] =maxtriS[frameidx][27];    tmpp.matrix[13]= maxtriS[frameidx][31];    tmpp.matrix[14] = maxtriS[frameidx][35];

            cby.transfe.multiplyMM(tmpp);

            tmpp.reinit();
            tmpp.rotate(role_angle,0,0,1);
            cby.transfe.multiplyMM(tmpp);


            tmpp.reinit();
            tmpp.translate(pointX,pointY,allhightt);
            cby.transfe.multiplyMM(tmpp);

        }
        if(cbz!=null){
            cbz.transfe.reinit();
            cbz.transfe.rotate(-angle,1,0,0);
            tmpp.reinit();
            tmpp.matrix[0] = maxtriS[frameidx][24];   tmpp.matrix[1]= maxtriS[frameidx][28];    tmpp.matrix[2] = maxtriS[frameidx][32];
            tmpp.matrix[4] =maxtriS[frameidx][25];     tmpp.matrix[5]= maxtriS[frameidx][29];     tmpp.matrix[6] = maxtriS[frameidx][33];
            tmpp.matrix[8] =maxtriS[frameidx][26];     tmpp.matrix[9]= maxtriS[frameidx][30];     tmpp.matrix[10] = maxtriS[frameidx][34];
            tmpp.matrix[12] =maxtriS[frameidx][27];    tmpp.matrix[13]= maxtriS[frameidx][31];    tmpp.matrix[14] = maxtriS[frameidx][35];


            cbz.transfe.multiplyMM(tmpp);
            tmpp.reinit();
            tmpp.rotate(role_angle,0,0,1);
            cbz.transfe.multiplyMM(tmpp);


            tmpp.reinit();
            tmpp.translate(pointX,pointY,allhightt);
            cbz.transfe.multiplyMM(tmpp);

        }
        face2camera = -AppDelegate.share().camera.angle*Const.HD2JD;
        //人物头顶文字需要与相机保持垂直
        if(bt!=null){
            bt.transfe.reinit();
            bt.translate(pointX,pointY,allhightt);
            bt.rotate(face2camera,0,0,1);
        }
        if(current_aniid==ani_jian_s3){
            //武器剑大招特效
            for(int i=0;i<lizi.length;i++){
                lizi[i].transfe.reinit();
                if(lizipoint[i][2]>=50 || lizipoint[i][3] <0.1f){
                    lizipoint[i][0] = -10 + (float)(Math.random()*20);
                    lizipoint[i][1] = -10 + (float)(Math.random()*20);
                    lizipoint[i][2] = (float)(Math.random()*10) ;
                    lizipoint[i][3] = (float)( 0.3+ Math.random()*0.3) ;
                }
                lizipoint[i][2]+=lizipoint[i][3];
                lizi[i].translate(pointX +lizipoint[i][0] ,pointY + lizipoint[i][1], lizipoint[i][2]+allhightt);
                lizi[i].rotate(face2camera,0,0,1);
            }
            xuanzhuan.transfe.reinit();
            xuanzhuan.translate(pointX,pointY,allhightt);
            dipanangle+=5;
            xuanzhuan.rotate(dipanangle,0,0,1);
            if(frameidx>250&&frameidx<265){
                boom.isEnable = true;
                boom_scale+=0.3f;
                if(boom_scale>15){
                    boom_scale = 0f;
                }
                boom.transfe.reinit();
                boom.translate(pointX,pointY,16+allhightt);
                boom.scale(boom_scale,boom_scale,1);
            }else{
                boom.isEnable = false;
            }
        }else if(current_aniid == ani_dao_s3){//刀武器大招
            for(int i=0;i<lizi.length;i++){
                lizi[i].transfe.reinit();
                if(lizipoint[i][2]>=50 || lizipoint[i][3] <0.1f){
                    lizipoint[i][0] = -10 + (float)(Math.random()*20);
                    lizipoint[i][1] = -10 + (float)(Math.random()*20);
                    lizipoint[i][2] = (float)(Math.random()*10) ;
                    lizipoint[i][3] = (float)( 0.3+ Math.random()*0.3) ;
                }
                lizipoint[i][2]+=lizipoint[i][3];
                lizi[i].translate(pointX +lizipoint[i][0] ,pointY + lizipoint[i][1], lizipoint[i][2]+allhightt);
                lizi[i].rotate(face2camera,0,0,1);
            }
            if((frameidx>325 && frameidx<365)|| (frameidx>375 && frameidx<420)){
                for(int i=0;i<daoqi.length;i++){
                    daoqi[i].isEnable = true;
                    float length  = (float)Math.sqrt(daoqi_move[i][0]*daoqi_move[i][0] + daoqi_move[i][1]*daoqi_move[i][1]) ;
                    if(daoqi_move[i][3] == 0|| length >= daoqi_move[i][3]){
                        daoqi_move[i][0] = 0; daoqi_move[i][1] = 0;
                        daoqi_move[i][2] =(float)(Math.PI*0.5*i+( -0.5+ Math.random()));
                        daoqi_move[i][3] =(float)(Math.random()*20 + 40);
                    }
                    float aa = daoqi_move[i][2] * Const.HD2JD;

                    daoqi_move[i][0]+= Math.sin(daoqi_move[i][2]) *3 ;
                    daoqi_move[i][1]-= Math.cos(daoqi_move[i][2]) *3 ;

                    daoqi[i].transfe.reinit();
                    daoqi[i].translate(pointX +daoqi_move[i][0] ,pointY + daoqi_move[i][1], 18+allhightt);
                    daoqi[i].rotate(aa,0,0,1);

                }
            }else {
                for(int i=0;i<daoqi.length;i++){
                    daoqi[i].isEnable = false;
                }
            }
        }else if(current_aniid==ani_bishou_s3){
            for(int i=0;i<lizi.length;i++){
                lizi[i].transfe.reinit();
                if(lizipoint[i][2]>=50 || lizipoint[i][3] <0.1f){
                    lizipoint[i][0] = -10 + (float)(Math.random()*20);
                    lizipoint[i][1] = -10 + (float)(Math.random()*20);
                    lizipoint[i][2] = (float)(Math.random()*10) ;
                    lizipoint[i][3] = (float)( 0.3+ Math.random()*0.3) ;
                }
                lizipoint[i][2]+=lizipoint[i][3];
                lizi[i].translate(pointX +lizipoint[i][0] ,pointY + lizipoint[i][1], lizipoint[i][2]+allhightt);
                lizi[i].rotate(face2camera,0,0,1);
            }
            if(frameidx> 240&&  frameidx<246){
                bishoudz.isEnable = true;
                bishoudz.transfe.reinit();
                bishoudz.translate(pointX,pointY , allhightt);
                bishoudz.rotate(role_angle ,0,0,1 );
            }else {
                bishoudz.isEnable = false;
            }
        }

        if(showCache.size()>0){
            if(bruiseHP(showCache.get(0)[0],showCache.get(0)[1])){
                showCache.remove(0);
            }
        }
        if(isShowBruise){
            if(hphight>=70){
                isShowBruise = false;
                for(int i=0;i<hpidx.length;i++){
                    shuzi[i].isEnable = false;
                }
                fuhao.isEnable = false;
            }else {
                hphight+=0.4f;
                if(num_type_idx==0){//如果是正数显示绿色增加血量，负数则红色降低血量
                    fuhao.textureId =NumUtil.green_[10];
                }else  if(num_type_idx==1){
                    fuhao.textureId =NumUtil.red_[10];
                }
                fuhao.transfe.reinit();
                fuhao.translate(pointX,pointY , allhightt);
                fuhao.rotate(face2camera,0,0,1);
                fuhao.translate(9,0 , hphight+allhightt);
                for(int i=0;i<hpidx.length;i++){
                    if(i>=iszero){
                        continue;
                    }
                    if(num_type_idx==0){//如果是正数显示绿色增加血量，负数则红色降低血量
                        shuzi[i].textureId = NumUtil.green_[hpidx[i]];
                    }else if(num_type_idx==1){
                        shuzi[i].textureId = NumUtil.red_[hpidx[i]];
                    }
                    shuzi[i].transfe.reinit();
                    shuzi[i].translate(pointX,pointY , allhightt);
                    shuzi[i].rotate(face2camera,0,0,1);
                    if(i==0){
                        shuzi[i].translate(-9,0 , hphight+allhightt);
                    }else if(i==1){
                        shuzi[i].translate(-3f,0 , hphight+allhightt);
                    }else if(i==2){
                        shuzi[i].translate(3,0 , hphight+allhightt);
                    }
                }
            }
        }
    }


    public boolean issiwangtmp = false;//角色是否是立即死亡状态
    public List<int[]> showCache = new ArrayList<>();

    private boolean isShowBruise = false;
    private float hphight = 60;
    private int []  hpidx = new int [3];
    private int iszero =0;
    private int num_type_idx = 0;//当前显示的数字是否是正数
    /**
     * 显示受到的伤害值
     * @param hp
     */
    public boolean bruiseHP(int hp,int isz){
        if(isShowBruise){
            return false;
        }
        num_type_idx = isz;
        isShowBruise = true;
        hphight = 60;
        hpidx[2] = hp % 1000 / 100;
        hpidx[1] = hp % 100 / 10;
        hpidx[0] = hp % 10;

        iszero = 3;
        if(hpidx[2]==0){
            iszero=2;//2 百位为0
        }
        if(iszero==2&&hpidx[1]==0){
            iszero=1;//百位十位都是0
        }
        for(int i=0;i<hpidx.length;i++){
            if(i>=iszero){
                continue;
            }
            shuzi[i].isEnable = true;
        }
        fuhao.isEnable = true;

        return true;
    }
    //xy轴坐标  旋转角度   最远距离
    float [][] daoqi_move = new float[daoqi.length][4];

    //剑气爆掉缩放值
    float boom_scale = 0;

    //粒子运动轨迹设置，0，1，2分别xyz变化 3为z轴变化速度
    float [][] lizipoint = new float[lizi.length][4];
    //面向摄像机需要的角度
    float face2camera = 0;
    //人物脚下特效圆盘旋转角度
    float dipanangle = 0;

    @Override
    public  void refScene(Scene s){
       super.refScene(s);
       for(int i=0;i<lizi.length;i++){
          s.addTranslucenceChile(lizi[i]);
       }
       for(int i=0;i<daoqi.length;i++){
          s.addTranslucenceChile(daoqi[i]);
       }
       s.addTranslucenceChile(bishoudz);
       s.addTranslucenceChile(xuanzhuan);
       s.addTranslucenceChile(boom);

        for(int j=0;j<shuzi.length;j++){
            for(int i=0;i<shuzi.length;i++){
                s.addTranslucenceChile( shuzi[j]);
            }
        }
        s.addTranslucenceChile(fuhao);

    }


    /**
     * 计算与目标对象之间的距离
     * @param r
     * @return
     */
    public float chickLength(Role r){
        return (float)Math.sqrt((this.pointX-r.pointX)*(this.pointX-r.pointX) + (this.pointY - r.pointY)*(this.pointY - r.pointY)) ;
    }



    //======================角色阵营标识=====================
    public static final int camp_liuxing = 0;//流星阵营
    public static final int camp_hudie =camp_liuxing + 1;//蝴蝶阵营
    //======================角色阵营标识=====================



}
