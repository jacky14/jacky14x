package com.jacky.engine.sound;

/**
 * Created by Administrator on 2016/3/21.
 */
public interface SoundInterface {

    //重置音量
    public void revol();
    /**
     * 播放音效文件
     * @param soundname
     */
    void playsound(String soundname);

    /**
     * 播放背景音音效
     * @param musicname
     */
    void playmusic(String musicname);

    /**
     * 暂停播放某个音效或背景音
     * @param name
     */
    void pause(String name);

    /**
     * 关闭并暂停所有音效背景音文件
     */
    void pauseall();

    /**
     * 重新启动播放背景音乐
     */
    void restart();
}
