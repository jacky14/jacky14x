package com.jacky.desktop.util;

import com.jacky.engine.local.LocalUtile;

/**
 * Created by Administrator on 2016/4/13.
 */
public class LocalUtilDestop implements LocalUtile{
    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void showMsg(String msg) {
        System.out.println(msg);
    }
}
