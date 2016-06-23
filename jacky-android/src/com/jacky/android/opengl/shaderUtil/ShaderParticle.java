package com.jacky.android.opengl.shaderUtil;

import com.jacky.engine.ShaderStrsource.StrSource;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ShaderParticle extends ShaderBase{
    public  int point_move; //����λ�������б�id
    public  int _idx;//����λ�ƶ�Ӧ�������±�
    public int rotatleM;//��ת��������
    public static ShaderParticle sp;

    public static ShaderParticle share(){
        if(sp==null){
            sp = new ShaderParticle();
        }
        return sp;
    }
    public  void init(){
        program = create(StrSource.particle_vert, StrSource.default_frag);
        super.initBase();
        //�������λ���б�id
        point_move = glGetUniformLocation(program,"point_move");
        _idx = glGetAttribLocation(program,"vec_move_idx");
        rotatleM = glGetUniformLocation(program,"rotatleM");
    }

}
