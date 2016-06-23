package com.jacky.desktop.util;

import com.jacky.GameStart;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.pay.CorePay;
import com.jacky.start.AppDelegate;

/**
 * Created by Administrator on 2016/3/30.
 */
public class DesktopPay implements CorePay{
    @Override
    public void pay(int id) {
        TouchEventJ tej = new TouchEventJ();
        tej.event = TouchEventI.PAY_CALLBACK;
        tej.payid = id;
        AppDelegate.share().currentScreen.accept_event(tej);
    }

    @Override
    public void paysuccecc() {

    }
}
