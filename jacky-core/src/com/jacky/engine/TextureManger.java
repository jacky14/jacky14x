package com.jacky.engine;

import com.jacky.start.AppDelegate;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/12/17.
 */
public class TextureManger {

    /**
     * 纹理缓存，
     * key 文件名称
     * value  size = 2   ;  0---纹理索引  1------当前纹理引用计数
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
     * 纹理缓存value数组下标意义
     */
    public static final int TEXIDINDEX = 0;
    public static final int TEXCOUNT = TEXIDINDEX + 1;//纹理引用计数


}
