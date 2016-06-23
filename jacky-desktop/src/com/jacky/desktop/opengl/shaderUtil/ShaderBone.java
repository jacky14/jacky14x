package com.jacky.desktop.opengl.shaderUtil;

import com.jacky.engine.ShaderStrsource.StrSource;

import static org.lwjgl.opengl.GL20.glGetAttribLocation;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;


/**
 * Created by Administrator on 2016/1/3.
 */
public class ShaderBone extends ShaderBase{

    public  int bone_marixid; //骨骼矩阵列表id
    public  int verBone_id;//顶点对应骨骼索引

    public static ShaderBone sb;
    public static ShaderBone share(){
        if(sb==null){
            sb = new ShaderBone();
        }
        return sb;
    }

    public  void init(){
        program = create(StrSource.bone_vert, StrSource.default_frag);
        super.initBase();
        //获得骨骼矩阵引用id
        bone_marixid = glGetUniformLocation(program,"u_matrixPalette");

        verBone_id = glGetAttribLocation(program,"verbone_idx");
    }

}
