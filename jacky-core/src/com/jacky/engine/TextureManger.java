package com.jacky.engine;

import com.jacky.start.AppDelegate;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/12/17.
 */
public class TextureManger {

    /**
     * �����棬
     * key �ļ�����
     * value  size = 2   ;  0---��������  1------��ǰ�������ü���
     */
    public static HashMap<String,int[]> texturemap = new HashMap<String,int[]>();
    public static int getTexture(String texturename){
       int[] id = texturemap.get(texturename);
        if(id == null){
            id = new int[]{
                    AppDelegate.share().graphicsTool.loadTex(texturename),
                    0
            };
            texturemap.put(texturename,id);
        }
        id[TEXCOUNT]++;
        return id[TEXIDINDEX];


    }

    public static void  clearTex(String texturename){
        int[] id = texturemap.get(texturename);
        if(id!=null){
            id[TEXCOUNT]--;
            if(id[TEXCOUNT] <= 0){
                AppDelegate.share().graphicsTool.cleanTexture(id[TEXIDINDEX]);
                texturemap.remove(texturename);
            }
        }
    }
    /**
     * ������value�����±�����
     */
    public static final int TEXIDINDEX = 0;
    public static final int TEXCOUNT = TEXIDINDEX + 1;//�������ü���


}
