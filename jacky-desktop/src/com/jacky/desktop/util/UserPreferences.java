package com.jacky.desktop.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/3/28.
 */
public class UserPreferences extends com.jacky.engine.savedata.UserPreferences {

    public String fullpath = null;
    public Map<String,String> cache = new HashMap<>();

    public void loadMap(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fullpath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("读取用户信息时发生错误请检查！！！");
        }
        cache.clear();
        while(sc.hasNext()){
            String[] k_v = sc.nextLine().split(" ");
            cache.put(k_v[0],k_v[1]);
        }
        sc.close();
    }
    public void saveMap(){
        Iterator<String> keyit = cache.keySet().iterator();
        PrintWriter pw = null;
        try {
             pw = new PrintWriter(fullpath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("写入用户信息到文件时发生错误！！");
        }
        while(keyit.hasNext()){
            String key = keyit.next();
            pw.println(key + " " +cache.get(key));
        }
        pw.close();

    }
    public UserPreferences(){
        fullpath = IOutil.getAssetsName("userinfo");
        File file =  new File(fullpath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("创建用户信息存储时发生错误！");
            }
        }

    }

    @Override
    public void setString(String key, String val) {
        loadMap();
        cache.put(key,val);
        saveMap();
    }

    @Override
    public void setInt(String key, int val) {
        loadMap();
        String intstr = String.valueOf(val);
        cache.put(key,intstr);
        saveMap();
    }

    @Override
    public void setBool(String key, boolean val) {
        loadMap();

        String s = "0";
        if(val){
            s = "1";
        }else{
            s = "0";
        }
        cache.put(key,s);
        saveMap();
    }

    @Override
    public String getString(String key,String def) {
        loadMap();
        String str = cache.get(key);
        if(str==null){
            return def;
        }else{
            return str;
        }
    }

    @Override
    public int getInt(String key,int def) {
        loadMap();
        String int_str = cache.get(key);
        if(int_str==null){
            return def;
        }else{
            return Integer.valueOf(int_str);
        }

    }

    @Override
    public boolean getBool(String key,boolean def) {
        loadMap();
        String bool_str = cache.get(key);
        if(bool_str==null){
            return def;
        }else{
            if("0".equals(bool_str)){
                return false;
            }else if("1".equals(bool_str)){
                return true;
            }
            return def;
        }


    }
}
