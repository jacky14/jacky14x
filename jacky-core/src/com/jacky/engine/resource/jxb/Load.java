package com.jacky.engine.resource.jxb;

import com.jacky.engine.resource.BinaryFile;

import java.io.InputStream;

/**
 * Created by Administrator on 2015/12/10.
 */
public class Load {
    public float [] vertex;
    public float [] texture;
    public short [] index;
    public float [] bones;

    public Load(InputStream in){
        BinaryFile bf = new BinaryFile(in);
        int length = bf.readInt();
        vertex = new float[length];
        for(int i=0;i<length;i++){
            vertex[i] = bf.readFloat();
        }
        length = bf.readInt();
        texture= new float[length];
        for(int i=0;i<length;i++){
            texture[i] = bf.readFloat();
        }
        length =  bf.readInt();
        index = new short [length];
        for(int i=0;i<length;i++){
            index[i] = (short)bf.readInt();
        }
        if(!bf.isEnd()){
            length =  bf.readInt();
            bones=new float [length];
            for(int i=0;i<length;i++){
                bones[i] = bf.readFloat();
            }
        }

    }
}
