package com.meteor.gm.util;

import com.jacky.start.AppDelegate;
import com.meteor.gm.element.*;

/**
 * Created by Administrator on 2016/3/8.
 */
public class Const {

    public static boolean isShowlenre = true;//是否展示教程

    //public static boolean isdebug = true;

    public static int roleid = Role.role_hun;
    public static int weaponid = Weapon.weapon_id_dao;
    public static int mapid = Map.sn06;
    public static int cbid = ChiBang.chibang_null;//光环一旦携带就无法被取消掉，必须任选一个携带，取消携带需要额外开发功能，这里不开发了累了
    public static int chid = -2;//人物选择的称号   默认是-1无

    private static int coin = -1;
    private static String coin_key = "coin_key";
    public static int getUserCoin(){
        if(coin==-1){
            coin =  AppDelegate.share().userpre.getInt(coin_key,0);
        }
        return coin;
    }
    //花费玩家指定数量的金币，若花费成功返回true  反之false
    public static boolean cost_coin(int costnum){
        int currentcoin = getUserCoin() -  costnum;
        if(currentcoin<0){
            AppDelegate.share().lu.showMsg("金币不足无法购买。");
            return false;
        }else{
            coin = currentcoin;
            AppDelegate.share().userpre.setInt(coin_key,coin);
            return true;
        }
    }

    public static void addUserCoin(int c){
        coin+=c;
        AppDelegate.share().userpre.setInt(coin_key,coin);
    }


    public static String key_ch = "chenghao_k";
    public static int getCh(){//读取玩家的称号
        if(chid ==-2){
            chid = AppDelegate.share().userpre.getInt(key_ch,-1);
        }
        return chid;
    }
    public static void saveCh(int i){
        chid = i;
        AppDelegate.share().userpre.setInt(key_ch,i);
    }

    public static String []  chstr = new String[]{
            "gsyx.png","chtxdy.png","chcszs.png"
    };

    public static int killnum = -1;//主角在游戏中击杀总数
    public static String key_killnum = "killnum";
    public static int getKillnum(){
        if(killnum==-1){
            killnum= AppDelegate.share().userpre.getInt(key_killnum,0);
        }
        return killnum;
    }
    //保存击杀数量， bo true直接将数据实例化到磁盘永久保存，比较耗时   ，false 修改内存中数量 极快
    public static void addsaveKillnum(int i,boolean bo){
        killnum+=i;
        if(bo){
            AppDelegate.share().userpre.setInt(key_killnum,killnum);
        }

    }

    //用户偏好设置key,用于记录玩家上次游戏时使用的角色武器地图等信息
    public static String bak_role = "bak_role";
    public static String bak_weapon = "bak_weapon";
    public static String bak_map = "bak_map";
    public static String bak_gh = "bak_gh";

    public static void readUserdef(){//读取用户偏好选择
        roleid = AppDelegate.share().userpre.getInt(bak_role,Role.role_hun);
        weaponid =  AppDelegate.share().userpre.getInt(bak_weapon,Weapon.weapon_id_dao);
        mapid = AppDelegate.share().userpre.getInt(bak_map,Map.sn06);
        cbid = AppDelegate.share().userpre.getInt(bak_gh,ChiBang.chibang_null);
    }
    public static void saveUserdef(){//保存用户偏好选择
        AppDelegate.share().userpre.setInt(bak_role,roleid);
        AppDelegate.share().userpre.setInt(bak_weapon,weaponid);
        AppDelegate.share().userpre.setInt(bak_map,mapid);
        AppDelegate.share().userpre.setInt(bak_gh,cbid);
    }

    //关卡解锁存储key
    public static String key_level_[] = new String[]{"leve1","leve2","leve3","leve4"};

    /**
     * 当前开启的最大关卡
     */
    public static int max_level = 0;
    public static int current_level = 0;

    /**
     * 检测当前开启的最大关卡
     */
    public static void chick_maxlevel(){
        /*if(isdebug){
            max_level = 4;
            return;
        }*/
        for(int i=0;i<Const.key_level_.length;i++){
            String  key_level =   Const.key_level_[i];
            if(AppDelegate.share().userpre.getBool(key_level,false)){
                max_level = i+1;
            }
        }
    }
    //解锁下一管卡
    public static void unlock_level(){
        if(current_level == max_level){
            max_level++;
            AppDelegate.share().userpre.setBool(key_level_[max_level-1],true);
        }
    }

    //5个付费角色是否已经解锁keys
    public static String[] key_rolelock = new String[]{"yan_def","ping_def","yan_longwen","xing_longwen","xing_anhei"};
    public static boolean [] rolelock_info = new boolean[5];
    public static void readrolelock(){//读取角色解锁信息
        for(int i=0;i<key_rolelock.length;i++){
            rolelock_info[i] = AppDelegate.share().userpre.getBool(key_rolelock[i],false);
        }
    }
    public static void buyRole(int idx){//玩家购买了指定索引的角色，索引值是key_rolelock 变量的下标
        rolelock_info[idx] = true;
        AppDelegate.share().userpre.setBool(key_rolelock[idx],true);
    }
    //7个付费武器的存储key值
    public static String[] key_weaponlock = new String[]{"binhuo_def","huoyan","badao","zixia","xueren","fenjihong","fenjihuang"};
    public static boolean [] weaponlock_info = new boolean[7];
    //读取武器解锁信息
    public static void readweaponlock(){
        for(int i=0;i<key_weaponlock.length;i++){
            weaponlock_info[i] = AppDelegate.share().userpre.getBool(key_weaponlock[i],false);
        }
    }
    public static void buyWeapon(int idx){//玩家购买了指定索引的武器，所引值是key_weaponlock变量的下标值
        weaponlock_info[idx] = true;
        AppDelegate.share().userpre.setBool(key_weaponlock[idx],true);
    }

    //光环购买是否可用key值
    public static String [] key_guanghuan = new String []{"gongji","fangyu","zhujiao"};
    public static boolean [] guanghuanlock_info = new boolean[3];
    public static void readguanghuanlock(){
        for(int i=0;i<key_guanghuan.length;i++){
            guanghuanlock_info[i] = AppDelegate.share().userpre.getBool(key_guanghuan[i],false);
        }
    }
    //获得指定索引的光环，索引意识是上面的下标
    public static void getGuanghuan(int idx){
        guanghuanlock_info[idx] = true;
        AppDelegate.share().userpre.setBool(key_guanghuan[idx],true);
    }
    public static NewGiftLayer ngl  = null;//礼包界面索引
    public static EquipSelectLayer esl  = null;//装备选择界面索引

    /**
     * 加载公用Layer界面
     */
    public static void initCommonUI(){
        if(ngl == null){
            ngl = new NewGiftLayer();
        }
        if(esl==null){
            esl = new EquipSelectLayer();
        }
    }
    public static void clearCommonUI(){
        if(ngl!= null){
            ngl.clear();
            ngl = null;
        }
        if(esl!=null){
            esl.clear();
            esl=null;
        }
    }

    /**
     * 角度转换为弧度
     */
    public static final float HD2JD = 57.29577951308232f;


    /**
     * 以下是游戏中商品信息  ，包含金币购买的道具和rmb购买道具
     */
    public static final int PROPINDEX_0 = 0 ;//角色默认燕                  C
    public static final int PROPINDEX_1 = PROPINDEX_0+1 ;//角色默认萍    C
    public static final int PROPINDEX_2 = PROPINDEX_1+1 ;//角色龙纹燕    $
    public static final int PROPINDEX_3 = PROPINDEX_2+1 ;//角色龙纹魂    $
    public static final int PROPINDEX_4 = PROPINDEX_3+1 ;//角色黑色星    $


    public static final int PROPINDEX_5 = PROPINDEX_4+ 1;//武器冰火刀默认皮肤 C
    public static final int PROPINDEX_6 = PROPINDEX_5+1;//火焰刀           C

    public static final int PROPINDEX_7 = PROPINDEX_6+1;//霸道            $
    public static final int PROPINDEX_8 = PROPINDEX_7+1;//紫霞宝剑          $
    public static final int PROPINDEX_9 = PROPINDEX_8+1;//血刃            $
    public static final int PROPINDEX_10 = PROPINDEX_9+1;//焚寂剑红色    $
    public static final int PROPINDEX_11 = PROPINDEX_10+1;//焚寂剑黄色   $

    public static final int PROPINDEX_12 = PROPINDEX_11+1;//新手礼包        $

    public static final int PROPINDEX_13 = PROPINDEX_12+1;//攻击光环        $
    public static final int PROPINDEX_14 = PROPINDEX_13+1;//防御光环        $
    public static final int PROPINDEX_15 = PROPINDEX_14+1;//主角光环        $

    //购买金币道具
    public static boolean coin_buy(int propid){
        switch (propid){
            case PROPINDEX_0://角色默认燕
                if(cost_coin(2000)){
                    buyRole(0);
                    return true;
                }
                break;
            case PROPINDEX_1://角色默认萍
                if(cost_coin(2000)){
                    buyRole(1);
                    return true;
                }
                break;
            case PROPINDEX_5://武器冰火刀默认皮肤
                if(cost_coin(1600)){
                    buyWeapon(0);
                    return true;
                }
                break;
            case PROPINDEX_6://火焰刀
                if(cost_coin(1600)){
                    buyWeapon(1);
                    return true;
                }

                break;
        }

        return false;
    }



    /**
     * 礼包界面能否展示
     * @return
     */
    public static boolean giftCanShow(){
        if(!rolelock_info[4]&&!weaponlock_info[6]){
            return true;
        }else{
            return false;
        }

    }
    //层索引标识，返回场景时将传递给场景
    public static final int layer_chenghao = 0;//称号选择界面
    public static final int layer_equip = layer_chenghao+1;//装备选择
    public static final int layer_guanghuan = layer_equip+1;//关卡选择
    public static final int layer_newgift = layer_guanghuan+1;//新手礼包选择
    public static final int layer_pause = layer_newgift+1;//游戏暂停界面
    public static final int layer_role = layer_pause+1;//角色选择界面
    public static final int layer_gkend = layer_role + 1;//关卡结束界面


    //场景唯一标识
    public static final int scene_level = 100;//选择关卡场景标识
    public static final int scene_gametest = scene_level + 1;//主游戏场景


}
