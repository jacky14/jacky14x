package com.jacky.engine.viewnode;

import com.jacky.engine.buffer.BufferUtil;
import com.jacky.engine.math.Matrix4f;
import com.jacky.start.AppDelegate;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Administrator on 2016/3/9.
 */
public class Camera2D {

    public Matrix4f orthoM = new Matrix4f();
    public Camera2D(){
        float width_half = AppDelegate.design_width*0.5f;
        float hight_half = AppDelegate.design_high*0.5f;
        orthoM.orthoM(-width_half,width_half,
                -hight_half,hight_half ,
                1,1000
        );
        BufferUtil.setFloatBuffer(orthoM.matrix,tmpCM);
    }
    FloatBuffer tmpCM = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();

   /* public FloatBuffer get2DCamera(Matrix4f t){
        Matrix4f temp = t.clone();
        temp.multiplyMM(orthoM);
        BufferUtil.setFloatBuffer(temp.matrix,bianhua);
        return bianhua;
    }*/
    public FloatBuffer get2DCamera(){
        return tmpCM;
    }
}
