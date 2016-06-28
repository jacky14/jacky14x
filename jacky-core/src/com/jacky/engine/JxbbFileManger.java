package com.jacky.engine;

import com.jacky.engine.resource.jxb.JbxBone;
import com.jacky.engine.resource.jxb.JxbDum;
import com.jacky.start.AppDelegate;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/1/5.
 */
public class JxbbFileManger {

    /**
     * key 文件名称 value 帧动画数据
     */
    public static HashMap<String,JxbbInfo> jbxDataIdxmap = new HashMap<String,JxbbInfo>();

    public static JxbbInfo getInfo(String key){
        JxbbInfo ji = jbxDataIdxmap.get(key);
        if(ji == null){
            float[][] tmpdata = null;
            if("jxbb".equals(key.substring(key.length()-4))){
                JbxBone jb = new JbxBone(AppDelegate.share().localFile.loadFile(key));
                tmpdata = jb.data;
            }else if("dum".equals(key.substring(key.length()-3))){
                tmpdata = JxbDum.gendata(key);
            }
            ji = new JxbbInfo();
            ji.data = tmpdata;
            ji.ref = 0;
            jbxDataIdxmap.put(key,ji);
        }
        ji.ref ++;
        return ji;
    }

    public static void clear(String key){
        JxbbInfo ji = jbxDataIdxmap.get(key);
        if(ji!=null){
            ji.ref--;
            if(ji.ref<=0){
                jbxDataIdxmap.remove(key);
            }
        }

    }

}
