package com.jacky.engine.viewnode;

import com.jacky.engine.TextureManger;
import com.jacky.engine.buffer.BufferUtil;
import com.jacky.start.AppDelegate;

/**
 * Created by Administrator on 2016/4/1.
 */
public class Number2D extends Node2D{

    int tcids[];
    public Number2D(float[] ver ,String texName){
        super(ver,texName);
        node_type =  NODE_TYPE_NUM;
    }
    public String numfile ;
    public void setText(String numberfile,int length){//给当前对象设置对应的数字纹理图，和图片中文字的长度
        numfile = numberfile;

       this.textureId =TextureManger.getTexture(numberfile);
       tcids = new int[length];
       float offset = 1.0f/length;

       float[]  texarr  = null;
       for(int i=0;i<length;i++){
           float s = i*offset;
           float e = (i+1)*offset;
           texarr = new float[]{
                   s,0,
                   e,0,
                   e,1,

                   e,1,
                   s,1,
                   s,0
           };
           tcids [i] =  AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(texarr));
       }
    }
    public void setNum(int num){
        this.texidx = tcids[num];
    }

    @Override
    public void release() {
        super.release();
        TextureManger.clearTex(numfile);
        for(int i=0;i<tcids.length;i++){
            AppDelegate.share().graphicsTool.cleanBuff(tcids[i]);
        }

    }
    /*public static float[] texarr= new float[]{
            0,0,
            1,0,
            1,1,

            1,1,
            0,1,
            0,0
    };*/

}
