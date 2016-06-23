package com.jacky.engine;

/**
 *
 * //合图中 坐标信息 合图纹理信息
 * Created by Administrator on 2016/4/20.
 */
public class MuvInfo {

    public MuvInfo(int i , float [] u){
        texid = i;
        uvcoord = u;
    }
    public int texid;
    public float [] uvcoord;

}
