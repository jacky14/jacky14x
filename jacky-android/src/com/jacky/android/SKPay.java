package com.jacky.android;

import android.content.Context;
import android.widget.Toast;
import com.jacky.android.util.AndroidCorePay;
import com.jacky.start.AppDelegate;



/**
 * Created by Administrator on 2016/3/30.
 */
public class SKPay {



    /*  public static final int PROPINDEX_0 = 0 ;//角色默认燕                  C
    public static final int PROPINDEX_1 = PROPINDEX_0+1 ;//角色默认萍    C
    public static final int PROPINDEX_2 = PROPINDEX_1+1 ;//角色龙纹燕    $
    public static final int PROPINDEX_3 = PROPINDEX_2+1 ;//角色龙纹魂    $
    public static final int PROPINDEX_4 = PROPINDEX_3+1 ;//角色黑色星    $


    public static final int PROPINDEX_5 = PROPINDEX_4+ 1;//武器冰火刀默认皮肤 C
    public static final int PROPINDEX_6 = PROPINDEX_5+1;//火焰刀           C

    public static final int PROPINDEX_7 = PROPINDEX_6+1;//霸道            $
    public static final int PROPINDEX_8 = PROPINDEX_7+1;//紫霞宝剑          $
    public static final int PROPINDEX_9 = PROPINDEX_8+1;//血刃            $
    public static final int PROPINDEX_10 = PROPINDEX_9+1;//焚寂剑红色    $
    public static final int PROPINDEX_11 = PROPINDEX_10+1;//焚寂剑黄色   $

    public static final int PROPINDEX_12 = PROPINDEX_11+1;//新手礼包        $

    public static final int PROPINDEX_13 = PROPINDEX_12+1;//攻击光环        $
    public static final int PROPINDEX_14 = PROPINDEX_13+1;//防御光环        $
    public static final int PROPINDEX_15 = PROPINDEX_14+1;//主角光环        $*/

    //String userId = "null";
    public SKPay(){
        //userId = PayConnect.getInstance(JackyActivity.jackyActivity).getDeviceId(JackyActivity.jackyActivity);
    }

    public String [][] payinfo = new String[][]{
            {"6","龙纹燕","龙纹套装燕"},
            {"6","龙纹魂","龙纹套装魂"},
            {"6","暗黑星","暗黑套装魂"},



            {"4","霸刀","王霸之刀"},
            {"4","紫霞宝剑","紫霞宝剑"},
            {"4","血刃","血色之刃"},
            {"4","焚寂剑红","焚寂剑红"},
            {"4","焚寂剑黄","焚寂剑黄"},

            {"6","新手礼包","新手礼包"},


            {"4","攻击光环","攻击光环"},
            {"4","防御光环","防御光环"},
            {"6","主角光环","携带此光环天下无敌！"},

    };
    public void skpay(){
        //AndroidCorePay.current_pay_id;//当前要购买的物品索引
        //当前sdk直接回调成功
        //JackyActivity.handler.sendEmptyMessage(2);

        //AppDelegate.share().lu.showMsg("测试游戏包无法购买付费道具。");
        JackyActivity.handler.sendEmptyMessage(2);//发放道具

       /* int idx = 0;
        switch (AndroidCorePay.current_pay_id){
            case Const.PROPINDEX_2:
                idx = 0;
                break;
            case Const.PROPINDEX_3:
                idx = 1;
                break;
            case Const.PROPINDEX_4:
                idx = 2;
                break;
            case  Const.PROPINDEX_7:
                idx = 3;
                break;
            case  Const.PROPINDEX_8:
                idx = 4;
                break;
            case  Const.PROPINDEX_9:
                idx = 5;
                break;
            case  Const.PROPINDEX_10:
                idx = 6;
                break;
            case  Const.PROPINDEX_11:
                idx = 7;
                break;
            case Const.PROPINDEX_12:
                idx = 8;
                break;
            case Const.PROPINDEX_13:
                idx = 9;
                break;
            case Const.PROPINDEX_14:
                idx = 10;
                break;
            case Const.PROPINDEX_15:
                idx = 11;
                break;

        }



        String[] paytmp = payinfo[idx];
        String orderId = "kdlx" + System.currentTimeMillis();
        PayConnect.getInstance(JackyActivity.jackyActivity).pay(JackyActivity.jackyActivity,orderId, userId,
                Float.valueOf(paytmp[0]), paytmp[1], paytmp[2], "" ,prl );*/


    }

    /*public PayResultListener prl = new PayResultListener() {
        @Override
        public void onPayFinish(Context payViewContext,String order_id,int  resultCode, String  resultString,  int payType,  float
                amount, String goods_name) {
            if(resultCode == 0){ // 支付成功
                JackyActivity.handler.sendEmptyMessage(2);//发放道具
                Toast.makeText(JackyActivity.jackyActivity, resultString + "：" + amount + "元", Toast.LENGTH_LONG).show();
                // 支付成功时关闭当前支付界面
                PayConnect.getInstance(JackyActivity.jackyActivity).closePayView(payViewContext);
                // TODO 在客户端处理支付成功的操作
                // 未指定 notifyUrl 的情况下，交易成功后，必须发送回执
                PayConnect.getInstance(JackyActivity.jackyActivity).confirm(order_id, payType);
            }else{// 支付失败
                Toast.makeText(JackyActivity.jackyActivity, resultString, Toast.LENGTH_LONG).show();
            }
        }
    };*/
}
