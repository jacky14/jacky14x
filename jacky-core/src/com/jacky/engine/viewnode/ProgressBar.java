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
     * ���ý������Ľ���ֵ1Ϊ�����ȣ��ݲ�֧���Ѿ����й����ŵĽ�����
     * @param jd
     */
    public void setJD(float jd){
        currentjd =jd;
        tmpscale[0] =jd - 1;
        tmppoint[0]  = (rect[2] - rect[0])*0.5f*tmpscale[0];
        computeTran(false);
    }

}
