package com.jacky.desktop.opengl.shaderUtil;

import com.jacky.engine.ShaderStrsource.StrSource;

import static org.lwjgl.opengl.GL20.glGetAttribLocation;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ShaderParticle extends ShaderBase{
    public  int point_move; //粒子位移坐标列表id
    public  int _idx;//顶点位移对应的索引下标
    public int rotatleM;//旋转矩阵引用
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
        //获得粒子位移列表id
        point_move = glGetUniformLocation(program,"point_move");
        _idx = glGetAttribLocation(program,"vec_move_idx");
        rotatleM = glGetUniformLocation(program,"rotatleM");
    }
}
