package com.meteor.gm.element;

import com.jacky.engine.JxbFileManger;
import com.jacky.engine.TextureManger;
import com.jacky.engine.viewnode.Node;

/**
 * Created by Administrator on 2016/3/4.
 */
public class Weapon extends Node {
    /**
     * 武器名称
     */
    public String name;
    /**
     * 当前武器技能
     */
    public int type = 0;
    public static final int weapon_type_dao = 0;
    public static final int weapon_type_jian = weapon_type_dao + 1;
    public static final int weapon_type_bishou = weapon_type_jian +1;

    public int wid;
    /**
     * 创建武器,根据武器id
     * @param id
     */
    public Weapon (int id){
        super(weapon_info[id][0],weapon_info[id][1]);
        wid = id;
        type = type_info[id];
    }
    public void reId(int id){
        release();
        rinit(weapon_info[id][0],weapon_info[id][1]);
        wid = id;
        type = type_info[id];
    }
    public static String [][] weapon_info = new String[][]{
            {"weapon/dao.jxb","weapon/dao.jpg"},
            {"weapon/jian.jxb","weapon/jian.jpg"},
            {"weapon/bishou.jxb","weapon/bishou.jpg"},

            {"weapon/bhd.jxb","weapon/bhd_0.jpg"},

            {"weapon/bhd.jxb","weapon/bhd.jpg"},
            {"weapon/badao.jxb","weapon/badao.jpg"},
            {"weapon/zixia.jxb","weapon/zixia.jpg"},
            {"weapon/gongheguo.jxb","weapon/gongheguo.jpg"},

            {"weapon/fjj.jxb","weapon/fjr.jpg"},
            {"weapon/fjj.jxb","weapon/fjy.jpg"},
    };
    public static int [] type_info = new int []{
        weapon_type_dao,
        weapon_type_jian,
        weapon_type_bishou,
        weapon_type_dao,
        weapon_type_dao,
        weapon_type_dao,
        weapon_type_jian,
        weapon_type_bishou,
        weapon_type_jian,
        weapon_type_jian,
    };
    //=======================武器索引========================
    public static final int weapon_id_dao = 0;//刀普通
    public static final int weapon_id_jian = weapon_id_dao + 1;//剑普通
    public static final int weapon_id_bishou = weapon_id_jian+ 1;//匕首普通

    public static final int weapon_id_def = weapon_id_bishou+ 1;//冰火刀  原默认皮肤  ￥
    public static final int weapon_id_bihuodao = weapon_id_def + 1;//冰火刀  火皮肤 ￥


    public static final int weapon_id_badao = weapon_id_bihuodao + 1;//霸道 红蓝彩色皮肤 $
    public static final int weapon_id_zixia = weapon_id_badao + 1;//紫霞宝剑  $
    public static final int weapon_id_gongheguo = weapon_id_zixia+1;//共和国之刃 $

    public static final int weapon_id_fenjired = weapon_id_gongheguo+1;//焚寂剑红色 $
    public static final int weapon_id_fenjiyellow = weapon_id_fenjired+1;//焚寂剑黄色 $

    //=======================武器索引========================
    //不同武器伤害值的比率
    public static final float[] weaponrate = new float [] {
            1,
            1,
            1,

            1.4f,
            1.4f,


            1.6f,
            1.6f,
            1.6f,
            1.6f,
            1.6f
    };
}
