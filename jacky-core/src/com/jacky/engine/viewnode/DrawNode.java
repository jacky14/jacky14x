package com.jacky.engine.viewnode;

/**
 * Created by Administrator on 2015/12/9.
 */
public abstract class DrawNode {

    /**
     * ÿһ֡���Ƹýڵ�ʱ����
     */
     public abstract void draw();

    /**
     * ÿһ֡���¸ýڵ�ʱ����
     */
    public abstract void update();

    /**
     * ��ոû��ƽڵ���gpu�е��������㣬�������껺��
     */
    public abstract void release();




    public Scene rescene = null ;
    public  void refScene(Scene s){
        rescene = s;
    }
    /**
     * �жϴ���������Ƿ��ڶ������������
     * @param x
     * @param y
     * @return
     */
    public abstract boolean isInRect(float x,float y);

    public boolean isEnable  = true;

    //����ͼ��Ⱦ��ʽ TEX_MODE1 Ĭ����Ⱦ��ʽGL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA��һ���ձ���Ⱦ��ʽ��
    //				 TEX_MODE2 GL_ONE,GL_ONE  һ��������Ⱦ������Чͼ
    public static  int TEX_MODE1 = 0;
    public static  int TEX_MODE2 = TEX_MODE1 + 1;

    public static int ARRAY_MODE = 0;
    public static int ELEMENT_MODE = ARRAY_MODE + 1;
    //��������ģ��
    public static int ELEMENT_BONE_MODE = ELEMENT_MODE+ 1;
}
