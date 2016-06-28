package com.jacky.engine.viewnode;

/**
 * Created by Administrator on 2015/12/9.
 */
public abstract class DrawNode {

    /**
     * 每一帧绘制该节点时调用
     */
     public abstract void draw();

    /**
     * 每一帧更新该节点时调用
     */
    public abstract void update();

    /**
     * 清空该绘制节点在gpu中的纹理，顶点，纹理坐标缓存
     */
    public abstract void release();




    public Scene rescene = null ;
    public  void refScene(Scene s){
        rescene = s;
    }
    /**
     * 判断传入坐标点是否在对象绘制区域内
     * @param x
     * @param y
     * @return
     */
    public abstract boolean isInRect(float x,float y);

    public boolean isEnable  = true;

    //纹理图渲染方式 TEX_MODE1 默认渲染方式GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA，一般普遍渲染方式，
    //				 TEX_MODE2 GL_ONE,GL_ONE  一般用于渲染高亮光效图
    public static  int TEX_MODE1 = 0;
    public static  int TEX_MODE2 = TEX_MODE1 + 1;

    public static int ARRAY_MODE = 0;
    public static int ELEMENT_MODE = ARRAY_MODE + 1;
    //索引骨骼模型
    public static int ELEMENT_BONE_MODE = ELEMENT_MODE+ 1;
}
