package com.jacky.engine;

import com.jacky.engine.resource.BinaryFile;
import com.jacky.start.AppDelegate;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/4/20.
 * 管理合成图片的类
 */
public class MuvManger {
    /**
     * key 具体图片的key值，int数组    0 对应大图的纹理索引，1 对应的uv坐标的缓存对象
     */
    public static HashMap<String,MuvInfo> muvs = new HashMap<String,MuvInfo>();
    public static MuvInfo getRect(String key){
        MuvInfo r = muvs.get(key);
        return r;
    }
    public static void load(String tex,String muv){
        int texid = TextureManger.getTexture(tex);
        BinaryFile bf = new BinaryFile(AppDelegate.share().localFile.loadFile(muv));
        String[] allstr = new String(bf.dataBuffer).split("#");

        for(int i=0;i<allstr.length;i++){
            float[] texarr = new float[12];
            String [] info = allstr[i].split(" ");
            for(int j=1;j<13;j++){
                texarr[j-1] = Float.valueOf(info[j]);
            }
            MuvInfo mi = new MuvInfo(texid,texarr);
            muvs.put(info[0],mi);
        }
    }





}
