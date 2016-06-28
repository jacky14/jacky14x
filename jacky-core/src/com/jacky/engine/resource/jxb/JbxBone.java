package com.jacky.engine.resource.jxb;

import com.jacky.engine.resource.BinaryFile;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/1/3.
 */
public class JbxBone {
    //帧数index 整体骨骼数据
    public  float [][] data;

    public JbxBone(InputStream in){
        BinaryFile bf = new BinaryFile(in);
        int length = bf.readInt();
        data =new float[length][];

        for(int i=0;i<length;i++){

            int  chillength = bf.readInt();
            data[i] = new float[chillength];
            for(int j=0;j<chillength;j++){
                data[i][j] = bf.readFloat();
            }

        }

    }

}
