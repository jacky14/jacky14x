package com.meteor.gm.util;

import com.jacky.engine.JxbbFileManger;
import com.jacky.engine.resource.BinaryFile;
import com.jacky.engine.resource.jxb.JbxBone;
import com.jacky.start.AppDelegate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * Created by Administrator on 2016/3/10.
 */
public class CommonAni {

    public static String [] aniname = new String[]{
            "base","bishou","dao","jian"
    };
    public static final int base_bone = 0;
    public static final int base_dum = base_bone + 1;

    public static final int bishou_bone = base_dum+1;
    public static final int bishou_dum = bishou_bone+1;

    public static final int dao_bone = bishou_dum+1;
    public static final int dao_dum = dao_bone+1;

    public static final int jian_bone = dao_dum+1;
    public static final int jian_dum = jian_bone+1;

    public static final int anilength = jian_dum+1;

    public static float [][][] allani = null;
    /**
     *
     */
    public static void load(){
        if(allani==null){
            allani = new float[anilength][][];
            for(int i=0;i<aniname.length;i++){
                allani[i*2] = JxbbFileManger.getInfo("role/commonani/"+aniname[i]+".jxbb").data;
                allani[i*2+1] =JxbbFileManger.getInfo("role/commonani/"+aniname[i]+".dum").data;
            }
        }
    }
    public static void clear(){
        if(allani!=null){

            for(int i=0;i<aniname.length;i++){
                JxbbFileManger.clear("role/commonani/"+aniname[i]+".jxbb");
                JxbbFileManger.clear("role/commonani/"+aniname[i]+".dum");
            }
            allani = null;
        }
    }
    public static void main(String[] args) throws Exception {
       /* BinaryFile bf = new BinaryFile(new FileInputStream("F:\\mypro\\jacky-X\\jacky-desktop\\assets\\role\\commonani\\character.jxbb"));
        int length = bf.readInt();
        allani =new float[length][];

        for(int i=0;i<length;i++){

            int  chillength = bf.readInt();
            allani[i] = new float[chillength];
            for(int j=0;j<chillength;j++){
                allani[i][j] = bf.readFloat();
            }

        }
        System.out.println("123");*/
    }

}
