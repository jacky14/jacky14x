package com.jacky.engine.viewnode;

import com.jacky.engine.TextureManger;
import com.jacky.engine.buffer.BufferUtil;
import com.jacky.engine.math.Matrix4f;
import com.jacky.start.AppDelegate;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Administrator on 2016/3/8.
 */
public class Node2D  extends   DrawNode{

    public static float[] texarr= new float[]{
            0,0,
            1,0,
            1,1,

            1,1,
            0,1,
            0,0
    };
    public static int publictexidx = -9999;
    public Node2D(){
    }

    public int veridx;
    public int texidx;
    public int textureId;
    private int  drawVerCount;//绘制定点的个数
    public String texname;//纹理名称
    public String name;//当前节点的名称

    public Matrix4f transfe ;
    //缩放参数
    public float scale[] ;
    //旋转参数
    public float rotation ;
    //初始位移参数
    public float point[] ;

    public int node_type = 0;

    //位移增量缓存
    public float tmppoint[] = new float[2];
    //最终位移坐标点
    public float finshPoint[] = new float[2];
    
    
    //当前缩放比例增量
    public float tmpscale[] = new float [2];
    //*2D对象变化不频繁 缓存变化矩阵
    FloatBuffer bianhua = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();
    /**
     * 计算变化矩阵
     * 参数是否需要旋转，当前引擎不支持缩放后旋转 若旋转则不再缩放
     */
    public void computeTran(boolean isrotation){
        if(transfe==null){
            transfe = new Matrix4f();
        }else{
            transfe.reinit();
        }
        transfe.scaleM(scale[0]+tmpscale[0],scale[1]+tmpscale[1],1);
        if(isrotation){
            transfe.rotate(rotation,0,0,1);
        }
        
        finshPoint[0] = transfe.matrix[12] = point[0] + tmppoint[0];
        finshPoint[1] = transfe.matrix[13] = point[1] + tmppoint[1];
        
        transfe.multiplyMM(AppDelegate.share().camera2D.orthoM);
        BufferUtil.setFloatBuffer(transfe.matrix,bianhua);
    }
    public void setPoint(float x,float y){
    	if(transfe==null){
            transfe = new Matrix4f();
        }else{
            transfe.reinit();
        }
    	finshPoint[0] = transfe.matrix[12] = x;
    	finshPoint[1] = transfe.matrix[13] = y;
    	transfe.multiplyMM(AppDelegate.share().camera2D.orthoM);
    	BufferUtil.setFloatBuffer(transfe.matrix,bianhua);
    }

    public Node2D(float[] ver ,String texName){
        veridx = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(ver));
        if(publictexidx == -9999){
            publictexidx = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(texarr));
        }

        texidx = publictexidx;
        textureId = TextureManger.getTexture(texName);
        drawVerCount = 6;
        texname = texName;

        node_type = NODE_TYPE_IMG;
    }
    @Override
    public void draw(){
        AppDelegate.share().graphicsTool.setCamera(bianhua);
        AppDelegate.share().graphicsTool.draw(veridx,texidx,textureId,drawVerCount);
    }

    @Override
    public void update() {
    }

    @Override
    public void release(){
        TextureManger.clearTex(texname);
        AppDelegate.share().graphicsTool.cleanBuff(veridx);
        //AppDelegate.share().graphicsTool.cleanBuff(texidx);
    }

    //0，1 左上x 左上y   2,3右下x 右下y
    public float rect[];
    @Override
    public boolean isInRect(float x,float y){
        if(x>rect[0]&&y>rect[1] && x<rect[2]&&y<rect[3]){
            return true;
        }
        return false;
    }



    @Override
    public void refScene(Scene s){
        super.refScene(s);
    }

    public static final int NODE_TYPE_IMG = 0;
    public static final int NODE_TYPE_BUTTON = NODE_TYPE_IMG + 1;
    public static final int NODE_TYPE_PROGRESSBAR = NODE_TYPE_BUTTON + 1;
    public static final int NODE_TYPE_NUM = NODE_TYPE_PROGRESSBAR + 1;
}
