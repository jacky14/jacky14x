package com.jacky.engine.viewnode;

/**
 * Created by Administrator on 2016/3/21.
 */
public class ProgressBar extends Node2D{
    public ProgressBar(float[] ver,String texName){
        super(ver,texName);
        node_type = NODE_TYPE_PROGRESSBAR;
    }

    public float currentjd = 1;

    /**
     * 设置进度条的进度值1为满进度，暂不支持已经进行过缩放的进度条
     * @param jd
     */
    public void setJD(float jd){
        currentjd =jd;
        tmpscale[0] =jd - 1;
        tmppoint[0]  = (rect[2] - rect[0])*0.5f*tmpscale[0];
        computeTran(false);
    }

}
