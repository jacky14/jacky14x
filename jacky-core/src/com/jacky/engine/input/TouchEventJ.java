package com.jacky.engine.input;

/**
 * Created by Administrator on 2016/3/14.
 */
public class TouchEventJ {
    /**
     * 触发该事件的唯一标识，用于区分是否是同一根手指进行的按下移动抬起
     */
    public int uuid;

    /**
     * 事件类型，按下，移动中，抬起  ,支付回调
     */
    public int event;
    /**
     * 用于标识触发该事件的目标位置
     */
    public float tag_x;
    public float tag_y;

    public int payid;//当该事件eventid 为支付回调时改属性标识购买了那个道具


}
