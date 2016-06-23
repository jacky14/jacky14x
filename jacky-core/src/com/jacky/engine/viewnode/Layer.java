package com.jacky.engine.viewnode;

import com.jacky.engine.input.TouchEventJ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public abstract class Layer {

    public boolean isEnable = true;

    /**
     * �����Ļ�����������¼����������е�������
     */
    public List<Node2D> r2d  = null;

    /**
     * ���ò����״̬�Ƿ����
     * @param eb
     */
    public void setEnable(boolean eb){
        isEnable = eb;
    }

    public void update_render(){
        if(r2d==null){
            System.out.println("��ǰ����δ����κζ��󣡣�");
            return;
        }
        for(DrawNode dn:r2d){
            if(dn.isEnable){//�����ǰ����ɼ�
                dn.update();
                dn.draw();
            }
        }
        this.update();
    }
    /**
     * ���������е�Ԫ��
     */
    public void clear(){
        for(int i=0;i<r2d.size();i++){
            r2d.get(i).release();
        }
    }

    /**
     * ������ʼ��
     */
    public abstract void init();
    /**
     * ��ÿһ֡���·���
     */
    public abstract void update();


    /**
     * ���ø÷�������򿪳���
     */
    public void openLayer(){
        this.isEnable = true;
        rescene.IsPause = true;
    }
    /**
     * ���������¼�
     * @param tej
     */
    public abstract void event(TouchEventJ tej);
    /**
     * ��ǰ�㱻��ӵ��ĳ�������
     */
    public Scene rescene = null ;

    /**
     * ���㱻��ӵ���ʱ�Զ����ø÷���
     * @param s
     */
    public  void refScene(Scene s){
        rescene = s;
       for(int i=0;i<r2d.size();i++){
            r2d.get(i).refScene(s);
       }
    }

    //�㷵�س���ʱ���ݸ������Ĳ���
    public class LayerParameter{
       public  int  layerid;//���ʶ
       public  int  parameter;//�㴫�ݸ������Ĳ���

    }

}
