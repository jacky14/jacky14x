package com.jacky.android.util;

import com.jacky.android.JackyActivity;
import com.jacky.engine.local.LocalUtile;

/**
 * Created by Administrator on 2016/4/13.
 */
public class LocalUtileAndroid implements LocalUtile{
    @Override
    public void exit() {
        JackyActivity.jackyActivity.finish();
    }

    @Override
    public void showMsg(String msg) {
        JackyActivity.MsgInfo = msg;
        JackyActivity.handler.sendEmptyMessage(0);
    }
}
