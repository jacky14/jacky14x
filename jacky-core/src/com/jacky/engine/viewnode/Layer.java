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
     * 层对象的缓存索引，记录层对象中所有的子物体
     */
    public List<Node2D> r2d  = null;

    /**
     * 设置层对象状态是否可用
     * @param eb
     */
    public void setEnable(boolean eb){
        isEnable = eb;
    }

    public void update_render(){
        if(r2d==null){
            System.out.println("当前场景未添加任何对象！！");
            return;
        }
        for(DrawNode dn:r2d){
            if(dn.isEnable){//如果当前对象可见
                dn.update();
                dn.draw();
            }
        }
        this.update();
    }
    /**
     * 清理层对象中的元素
     */
    public void clear(){
        for(int i=0;i<r2d.size();i++){
            r2d.get(i).release();
        }
    }

    /**
     * 层对象初始化
     */
    public abstract void init();
    /**
     * 层每一帧更新方法
     */
    public abstract void update();


    /**
     * 调用该方法将会打开场景
     */
    public void openLayer(){
        this.isEnable = true;
        rescene.IsPause = true;
    }
    /**
     * 层对象接受事件
     * @param tej
     */
    public abstract void event(TouchEventJ tej);
    /**
     * 当前层被添加到的场景引用
     */
    public Scene rescene = null ;

    /**
     * 当层被添加到层时自动调用该方法
     * @param s
     */
    public  void refScene(Scene s){
        rescene = s;
       for(int i=0;i<r2d.size();i++){
            r2d.get(i).refScene(s);
       }
    }

    //层返回场景时传递给场景的参数
    public class LayerParameter{
       public  int  layerid;//层标识
       public  int  parameter;//层传递给场景的参数

    }

}
