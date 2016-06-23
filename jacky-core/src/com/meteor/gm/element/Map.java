package com.meteor.gm.element;

import com.jacky.engine.sound.SoundManger;
import com.jacky.engine.viewnode.Scene;
import com.jacky.engine.viewnode.Sprite;

/**
 * Created by Administrator on 2016/3/18.
 */
public class Map {
    //关卡顺序  四方阵 炽雪城 洛阳城 皇天城 金华城

    //机器人各个地图活动区域检测
    public static float [][] robot_area = new float[][]{
            {-870.2715f, -235.1587f,-570.2715f, 164.8413f,       -317.5517f, -90.8804f, -17.5517f, 89.1196f,
             272.3822f, -90.8804f, 572.3822f, 89.1196f,    651.3293f, -335.1587f,871.3293f, 264.8413f
            },//炽雪城

            {201.5234f, -638.4923f,  581.5234f, -368.4923f,
             201.5234f, -359.7834f, 581.5234f, -89.7834f
            },
            //皇天城

            {64.7142f, -425.0846f, 464.7142f, -25.0846f,      64.7142f, 27.1319f,464.7142f,427.1320f,
             -388.5586f, 27.1319f,11.4414f, 427.1320f,        -388.5586f, -421.9022f,11.4414f, -21.9022f},
            //四方阵


            {-269.8021f, -196.5735f, 330.1979f, 703.4265f},
            //金华城


            {-122.1894f, 620.1432f,  257.8106f, 920.1432f,     -82.9997f, -248.5955f, 297.0003f, 351.4045f,
             -72.3440f, -1158.0425f, 307.6560f, -608.0425f
            },//洛阳城
    };

    /**
     * 判断传入的坐标点是否在当前地图的指定区域内
     * @param x
     * @param y
     * @return
     */
    public static boolean isInArea(float x,float y,int area){
        if(x>robot_area[current_map][area*4] && x < robot_area[current_map][area*4 + 2]
           && y> robot_area[current_map][area*4+1] &&  y<  robot_area[current_map][area*4+3]){
            return true;
        }
        return false;
    }


    //关卡场景机器人类型及武器类型配置,机器人所在区域
    public static final int[][] robotinfo= new int[][]{
            {Role.role_dahuzi,Weapon.weapon_id_def,0, Role.role_yemaozi,Weapon.weapon_id_gongheguo,0,
             Role.role_changqiang,Weapon.weapon_id_fenjiyellow,0,Role.role_gaolaoda,Weapon.weapon_id_fenjired,0,

             Role.role_lyhaohan,Weapon.weapon_id_dao,1, Role.role_dahuzi,Weapon.weapon_id_bishou,1,
             Role.role_dahuzi,Weapon.weapon_id_jian,1, Role.role_changqiang,Weapon.weapon_id_zixia,1,

             Role.role_dahuzi,Weapon.weapon_id_dao,2,Role.role_huaheshang,Weapon.weapon_id_dao,2,
             Role.role_changqiang,Weapon.weapon_id_bishou,2,Role.role_changqiang,Weapon.weapon_id_jian,2,

             Role.role_gaolaoda,Weapon.weapon_id_def,3,Role.role_jrboss,Weapon.weapon_id_badao,3,
             Role.role_wuming,Weapon.weapon_id_fenjired,3,Role.role_hongyijs,Weapon.weapon_id_bihuodao,3
            },//炽雪城

            {   Role.role_dahuzi,Weapon.weapon_id_dao,1,Role.role_huaheshang,Weapon.weapon_id_jian,1,
                Role.role_changqiang,Weapon.weapon_id_jian,1,Role.role_changqiang,Weapon.weapon_id_def,1,
                Role.role_gaolaoda,Weapon.weapon_id_zixia,0,Role.role_lyhaohan,Weapon.weapon_id_bishou,0,
                Role.role_yemaozi,Weapon.weapon_id_gongheguo,0,Role.role_huiyiboss,Weapon.weapon_id_badao,0,
            },//皇天城

            {Role.role_dahuzi,Weapon.weapon_id_dao,0, Role.role_changqiang,Weapon.weapon_id_jian,0,Role.role_yemaozi,Weapon.weapon_id_def,0,
             Role.role_dahuzi,Weapon.weapon_id_zixia,1,Role.role_changqiang,Weapon.weapon_id_badao,1,Role.role_wuming,Weapon.weapon_id_zixia,1,
             Role.role_wuming,Weapon.weapon_id_zixia,2,Role.role_changqiang,Weapon.weapon_id_badao,2,Role.role_gaolaoda,Weapon.weapon_id_zixia,2,
             Role.role_yemaozi,Weapon.weapon_id_zixia,3,Role.role_changqiang,Weapon.weapon_id_badao,3,Role.role_jrboss,Weapon.weapon_id_zixia,3
            },//四方阵

            {0},//金华城

            {Role.role_dahuzi,Weapon.weapon_id_def,0, Role.role_dahuzi,Weapon.weapon_id_bishou,0,
             Role.role_changqiang,Weapon.weapon_id_jian,0,Role.role_lyhaohan,Weapon.weapon_id_badao,0,

             Role.role_changqiang,Weapon.weapon_id_dao,1, Role.role_dahuzi,Weapon.weapon_id_bishou,1,
             Role.role_dahuzi,Weapon.weapon_id_jian,1, Role.role_lyhaohan,Weapon.weapon_id_badao,1,Role.role_hongyijs,Weapon.weapon_id_fenjired,1,

             Role.role_gaolaoda,Weapon.weapon_id_dao,2,Role.role_hongyijs,Weapon.weapon_id_zixia,2,
             Role.role_yemaozi,Weapon.weapon_id_bishou,2,Role.role_huaheshang,Weapon.weapon_id_def,2,Role.role_hongyiboss,Weapon.weapon_id_fenjiyellow,2,

            },//洛阳城
    };

    public static float dxy [] = new float[2];
    /**
     * 在当前地图指定区域产生一个随机坐标点
     * @param i
     * @return
     */
    public static void robot_point(int i){
        dxy[0] = robot_area[current_map][i*4+2] - robot_area[current_map][i*4];
        dxy[1] = robot_area[current_map][i*4+3] - robot_area[current_map][i*4+1];

        dxy[0] = (float)(Math.random()*dxy[0]) + robot_area[current_map][i*4];
        dxy[1] = (float)(Math.random()*dxy[1]) + robot_area[current_map][i*4+1];
    }
    //各个场景主角英雄初始的坐标
    public static float[][] scene_hero  = new float [][]{
            {-841,391},//炽雪城
            {418,-70},//皇天城
            {0,-1},//四方阵
            {0,0},//金华城
            {-82,1002}//洛阳城
    };

    //各个地图边缘检测数据
    public static float [][] edge = new float [][]{
            {-941.8444f, -505.9803f,-561.8444f, 493.9749f,     -589.1910f, -94.5749f, 1010.9421f, 95.4054f,
             636.1388f, -460.1423f,  886.1388f, 459.8577f,    -48.7282f, -506.6449f,151.2718f, 493.3551f },
            //炽雪城


            {191.5234f, -651.1904f, 591.5234f, -71.1904f,      268.2781f, -352.1259f, 568.2781f, 47.8741f,
            },//皇天城

            {-420.5007f, -441.3484f, 479.4993f, 458.5343f},
            //四方阵

            {-287.6729f, -841.7317f, 312.3271f, 58.2683f},
            //金华城

            {13.2918f, -1218.4077f, 173.2918f, 981.5923f,       -107.2194f, -453.8579f, 312.7806f, 546.1421f,
             -143.7039f, 613.7698f, 276.2961f,1063.7698f,       -95.3273f, -1236.7908f,  324.6727f, -536.7908f},
            //洛阳城
    };


    public static int current_map = 0;
    public static boolean chick_edge(float x,float y){
        for(int i=0;i<edge[current_map].length/4 ;i++){
            int base_idx = i*4;
            if(x>edge[current_map][base_idx]  &&   x< edge[current_map][base_idx+2]
               &&y> edge[current_map][base_idx+1] &&  y< edge[current_map][base_idx+3]){
                return true;
            }
        }
        return false;
    }


    public static float robotrate = 0.3f;//不同的地图关卡机器人伤害值的比率

    /**
     * 向场景中添加地图
     * @param scene
     */
    public static void addMap2Scene(Scene scene,int mapid){
        current_map= mapid;
        String tmpstrs[] = null;
        Role.map_hight = 0;//四方阵场景人物高度需要降低
        if(mapid == sn04){//炽雪
            tmpstrs = strsn04;
            robotrate = 0.35f;
            SoundManger.share().playmusic("sound/lbg2.mp3");
        }else if(mapid == sn05){//皇天
            tmpstrs = strsn05;
            robotrate = 0.42f;
            SoundManger.share().playmusic("sound/lbg3.mp3");
        }else if(mapid == sn06){//四方阵
            tmpstrs = strsn06;
            //四方阵需添加水流模型
            for(int i=0;i<strsn06_t.length;i++){
                Sprite maptest = new Sprite(map_path[mapid] + strsn06_t[i] + ".jxb", map_path[mapid] + strsn06_t[i] + ".JPG");
                maptest.isTranslucence = true;
                maptest.setTexMode(1);
                scene.addTranslucenceChile(maptest);
            }
            Role.map_hight = -18;

            robotrate =  0.3f;

            SoundManger.share().playmusic("sound/lbg1.mp3");

        }else if(mapid == sn13){//金华
            tmpstrs = strsn13;
            robotrate =  0.42f;
        }else if(mapid == sn19){//洛阳
            tmpstrs = strsn19;
            robotrate = 0.42f;

            int mid = (int)(Math.random()*10) ;
            if(mid>7){
                SoundManger.share().playmusic("sound/lbg1.mp3");
            }else if(mid>4){
                SoundManger.share().playmusic("sound/lbg2.mp3");
            }else{
                SoundManger.share().playmusic("sound/lbg3.mp3");
            }


        }
        for (int i = 0; i < tmpstrs.length; i++){
            Sprite maptest =new Sprite(map_path[mapid] + tmpstrs[i] + ".jxb", map_path[mapid] + tmpstrs[i] + ".JPG");
            scene.addChile(maptest);
        }
    }

    public static  String[] map_path = new String[]{
            "map/sn04/", "map/sn05/", "map/sn06/", "map/sjhc/", "map/sn19/"
    };

    //炽雪城
    public static  String[] strsn04 = new String[]{
            "sn0425" ,"lho04" ,"sn0438" ,"sn0417" ,"sn0412" ,"sn0434" ,"l1w01" ,"sn0404" ,"sn0445" ,"sn0421" ,"B1gsc08" ,"sn0429" ,
            "sn0411" ,"sn0415" ,"sn0416" ,"sn0403" ,"lho01" ,"sn0407" ,"sn0420" ,"sn0433" ,"sn0401" ,"sn0406" ,"sn0419" ,
            "sn0427" ,"sn0414" ,"sn0428" ,"sn0410" ,"sn0423" ,"sn0430" ,"sn0432" ,"sn0413" ,"sn0418" ,"sn0443" ,"sn0426" ,"sn0440" ,
            "sn0422" ,"sn0439" ,"sn0405"
    };
    //皇天城
    public static  String[] strsn05 = new String[]{
            "1Rwd01","303w27","kmn05r2","sn052","sn05d","sn05dor","sn05g","sn05rfc","sn05roof","sn05wal4",
            "sn05z","sn05zc","sn05zu","sn3tr4","sn5roof","snw",
    };
    //四方阵
    public static  String[] strsn06 = new String[]{
            "sn06mian5","SN06ZF","SN06ZHU",
            "SN06ZHU2","SN06ZHU2","SN06ZHU3","SN064"
    };
    public static  String[] strsn06_t = new String[]{//特殊地形，透明地形
            "SN06SH","SN06SH2","SN06W"
    };


    //金华城
    public static  String[] strsn13 = new String[]{
            "jhc"
    };
    /*public static  String[] strsn13 = new String[]{
            "deco3","deco4","f14o04","s2","sn13h01","sn13h02","sn13h03","sn13h04","sn13h06","sn13h07","sn13h08","sn13h10","sn13h14",
            "sn13r01","sn13r02","sn13r03","sn13r04","sn13r05","sn13r06","sn13r08","sn13r09","sn13r10","sn13r11","sn13r12","sn13r13",
            "sn13r14","sn13r16","sn13w01","sn13w02",
    };*/

    //洛阳城
    public static  String[] strsn19 = new String[]{
            "77","Asn1916","sn1901","sn1902","sn1903","sn1904","sn1905","sn1906","sn1907","sn1908","sn1909","sn1910",
            "sn1911","sn1912","sn1915","sn1916","sn1917","sn1918","sn1919","sn191a","sn1920","sn1921","sn1923","sn1924",
            "sn1925","sn1926","sn19301","sn19302","sn19304","sn19305","sn19306","sn19307","sn19311","sn19313","sn19314",
            "sn19315","sn19316","sn19317","sn19318","sn19319","sn19320","sn19321","sn19322","sn19323","sn19324","sn19325",
            "sn19326","sn19328","sn19331","sn19332","sn1940","sn19400","sn19416"
    };

    public static final int sn04 = 0; //炽雪城
    public static final int sn05 = sn04 + 1; //皇天城
    public static final int sn06 = sn05 + 1; //四方阵
    public static final int sn13 = sn06 + 1; //金华城
    public static final int sn19 = sn13 + 1; //洛阳城

}
