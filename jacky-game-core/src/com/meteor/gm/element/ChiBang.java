package com.meteor.gm.element;

/**
 * Created by Administrator on 2016/3/31.
 */
public class ChiBang {
    public static final int chibang_gj  = 0 ;//攻击翅膀
    public static final int chibang_fy  = chibang_gj + 1 ;//防御翅膀
    public static final int chibang_zj  = chibang_fy + 1 ;//主角翅膀
    public static final int chibang_null  = chibang_zj + 1;//无翅膀

    //光环带来的攻击和防御加成
    public static final float[] cbrate = new float[]{
            1.4f,
            1.4f,
            1.4f,
            1
    };

    //翅膀的纹理名称，下标就是上面索引值
    public static final String[] cbstr = new String[]{
            "guanghuan2.jpg","guanghuan1.jpg","guanghuan3.jpg"
    };
}
