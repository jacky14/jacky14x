package com.jacky.engine.input;

/**
 * Created by Administrator on 2016/3/9.
 */
public class TouchEventI {
    public static final int TOUCH_EVENT_START = 0;
    public static final int TOUCH_EVENT_MOVE = TOUCH_EVENT_START +1;
    public static final int TOUCH_EVENT_END = TOUCH_EVENT_MOVE+1;
    public static final int PAY_CALLBACK = TOUCH_EVENT_END+1;//购买道具回调事件

    public static final int KEY_BACK = PAY_CALLBACK + 1;
}
