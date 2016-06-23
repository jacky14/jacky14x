package com.jacky.engine.viewnode;

/**
 * Created by Administrator on 2016/3/9.
 */
public class Button extends Node2D{


    public Button(float[] ver ,String texName){
        super(ver,texName);
        node_type = NODE_TYPE_BUTTON;
    }

    float a = 0.005f;
    @Override
    public void update() {
            if(tmpscale[0]>0.2f){
                a = -a;
            }else if(tmpscale[0]<0){
                a = -a;
            }
            tmpscale[0]+=a;
            tmpscale[1]+=a;
            computeTran(false);
    }



}
