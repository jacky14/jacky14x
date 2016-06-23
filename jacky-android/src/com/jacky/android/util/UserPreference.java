package com.jacky.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.jacky.android.JackyActivity;
import com.jacky.engine.savedata.UserPreferences;

/**
 * Created by Administrator on 2016/3/28.
 */
public class UserPreference extends UserPreferences {

    private SharedPreferences sp;
    public UserPreference(){
       sp =JackyActivity.jackyActivity.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    }

    @Override
    public void setString(String key, String val) {
        SharedPreferences.Editor ed =  sp.edit();
        ed.putString(key,val);
        ed.commit();
    }

    @Override
    public void setInt(String key, int val) {
        SharedPreferences.Editor ed =  sp.edit();
        ed.putInt(key,val);
        ed.commit();
    }

    @Override
    public void setBool(String key, boolean val) {
        SharedPreferences.Editor ed =  sp.edit();
        ed.putBoolean(key,val);
        ed.commit();
    }

    @Override
    public String getString(String key,String def) {
        return sp.getString(key,def);
    }

    @Override
    public int getInt(String key,int def) {
        return sp.getInt(key,def);
    }

    @Override
    public boolean getBool(String key,boolean def) {
        return sp.getBoolean(key,def);
    }
}
