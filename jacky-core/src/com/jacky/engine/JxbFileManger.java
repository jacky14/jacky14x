package com.jacky.engine;

import com.jacky.engine.buffer.BufferUtil;
import com.jacky.engine.resource.jxb.Load;
import com.jacky.start.AppDelegate;


import java.util.HashMap;

/**
 * Created by Administrator on 2016/1/5.
 */
public class JxbFileManger {

    /**
     * jbx 文件对应 openglid索引
     * key 文件名 int[] 0 顶点id 1 纹理坐标id 2 索引id 3 骨骼索引id  4绘制三角形总数     5 引用计数
     */
    public static HashMap<String,int[]> jbxDataIdxmap = new HashMap<String,int[]>();

    public static int[] getidxFromfname(String fname){
        int[] idxs =  jbxDataIdxmap.get(fname);
        if(idxs == null){
            idxs = new int [6];
            Load ld =new Load(AppDelegate.share().localFile.loadFile(fname));

            idxs[0] = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(ld.vertex));
            idxs[1]  =  AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(ld.texture));
            idxs[2]  = AppDelegate.share().graphicsTool.bindIndexBuffer(BufferUtil.createShortBuff(ld.index));
            if(ld.bones!=null){
                idxs[3]  = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(ld.bones));
            }else{
                idxs[3] = -1;
            }
            idxs[4] = ld.index.length;
            idxs[5]=0;
            jbxDataIdxmap.put(fname,idxs);
        }
        idxs[5]++;
        return idxs;
    }
    public static void clear(String fname){
        int[] idxs = jbxDataIdxmap.get(fname);
        if(idxs!=null){
            idxs[5]--;
            if(idxs[5]<=0){
                AppDelegate.share().graphicsTool.cleanBuff(idxs[0]);
                AppDelegate.share().graphicsTool.cleanBuff(idxs[1]);
                AppDelegate.share().graphicsTool.cleanBuff(idxs[2]);
                if(idxs[3]!=-1){
                    AppDelegate.share().graphicsTool.cleanBuff(idxs[3]);
                }
                jbxDataIdxmap.remove(fname);
            }
        }
    }


    //int[] 0 顶点id 1 纹理坐标id 2 索引id 3 骨骼索引id  4绘制三角形总数     5 引用计数
}
