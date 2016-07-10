package com.meteor.gm.util;

import com.jacky.engine.TextureManger;

/**
 * ��Ϸ�����ֹ�����
 * Created by Administrator on 2016/3/23.
 */
public class NumUtil {
    /**
     * ��ɫ������
     */
    public static int [] red_ = new int [11];

    /**
     * ��ɫ������
     */
    public static int [] green_ = new int [11];

    /**
     * ��ʼ������
     */
    public static void init(){
        for(int i=0;i<red_.length;i++){
            red_[i] = TextureManger.getTexture("number/red/"+i+".png");
        }
        for(int i=0;i<green_.length;i++){
            green_[i] = TextureManger.getTexture("number/green/"+i+".png");
        }
    }
    public static void clear(){
        for(int i=0;i<red_.length;i++){
            TextureManger.clearTex("number/red/"+i+".png");
        }
        for(int i=0;i<green_.length;i++){
            TextureManger.clearTex("number/green/"+i+".png");
        }
    }


}
