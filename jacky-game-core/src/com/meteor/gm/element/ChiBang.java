package com.meteor.gm.element;

/**
 * Created by Administrator on 2016/3/31.
 */
public class ChiBang {
    public static final int chibang_gj  = 0 ;//�������
    public static final int chibang_fy  = chibang_gj + 1 ;//�������
    public static final int chibang_zj  = chibang_fy + 1 ;//���ǳ��
    public static final int chibang_null  = chibang_zj + 1;//�޳��

    //�⻷�����Ĺ����ͷ����ӳ�
    public static final float[] cbrate = new float[]{
            1.4f,
            1.4f,
            1.4f,
            1
    };

    //�����������ƣ��±������������ֵ
    public static final String[] cbstr = new String[]{
            "guanghuan2.jpg","guanghuan1.jpg","guanghuan3.jpg"
    };
}
