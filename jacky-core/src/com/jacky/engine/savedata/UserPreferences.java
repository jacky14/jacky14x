package com.jacky.engine.savedata;

/**
 * Created by Administrator on 2016/3/28.
 */
public abstract class UserPreferences {


    public abstract void setString(String key,String val);
    public abstract void setInt(String key,int val);
    public abstract void setBool(String key,boolean val);

    public abstract String getString(String key,String def);
    public abstract int getInt(String key,int def);
    public abstract boolean getBool(String key,boolean def);


}
