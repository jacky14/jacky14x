package com.jacky.android.util;

import android.app.Activity;
import android.content.Context;
import com.jacky.android.JackyActivity;
import com.jacky.android.MainView;
import com.jacky.engine.pay.CorePay;

/**
 * Created by Administrator on 2016/3/30.
 */
public class AndroidCorePay implements CorePay{

    MainView  mainView;
    public AndroidCorePay(MainView mv){
        mainView = mv;
    }

    public static int current_pay_id = 0;

    @Override
    public void pay(int id) {
        current_pay_id = id;
        JackyActivity.handler.sendEmptyMessage(1);
    }

    @Override
    public void paysuccecc(){
        mainView.addPayCallBack(current_pay_id);
    }


}
