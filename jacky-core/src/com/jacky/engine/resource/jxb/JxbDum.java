package com.jacky.engine.resource.jxb;

import com.jacky.engine.resource.BinaryFile;
import com.jacky.start.AppDelegate;

/**
 * Created by Administrator on 2016/3/10.
 */
public class JxbDum {
    public static  float [][] gendata(String key){
        BinaryFile bf = new BinaryFile(AppDelegate.share().localFile.loadFile(key));
        int length = bf.readInt();
        float [][] weapon_marix = new float[length][];
        for(int i=0;i<length;i++){
            weapon_marix[i] = new float[16];
            for(int j=0;j<16;j++){
                weapon_marix[i][j] = bf.readFloat();
            }
        }
        return weapon_marix;
    }
}
