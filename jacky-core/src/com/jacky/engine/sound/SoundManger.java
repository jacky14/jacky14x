package com.jacky.engine.sound;

/**
 * 音效管理类
 * Created by Administrator on 2016/3/21.
 */
public  class SoundManger {

    public boolean soundstate = true;
    private SoundManger (){}
    public SoundInterface sf;

    private String playmusicname ;
    /**
     * 初始化声音接口
     * @param sf
     */
    public  void initInterface(SoundInterface sf){
        this.sf = sf;
    }

    private static SoundManger sm;
    /**
     * 获得音效管理类
     * @return
     */
    public static SoundManger share(){
        if(sm == null){
            sm = new SoundManger();
        }
        return sm;
    }

    /**
     * 播放音效文件
     * @param soundname
     */
    public void playsound(String soundname){
        if(!soundstate){
            return;
        }
        sf.playsound(soundname);
    }

    /**
     * 播放背景音音效
     * @param musicname
     */
    public void playmusic(String musicname){

        if(!soundstate){
            playmusicname = musicname;
            return;
        }
        playmusicname =null;
        sf.playmusic(musicname);
    }

    /**
     * 暂停播放某个音效或背景音
     * @param name
     */
    public void pause(String name){
        if(!soundstate){
            return;
        }
        sf.pause(name);
    }

    /**
     * 设置声音开关状态 true 开启  false关闭
     * @param state
     */
    public void setState(boolean state){
        soundstate = state;
        if(soundstate){
            if(playmusicname!=null){
                sf.playmusic(playmusicname);
            }else{
                restart();
            }
        }else{
            pauseall();
        }
    }
    /**
     * 关闭并暂停所有音效背景音文件
     */
    public void pauseall(){
        if(sf!=null){
            sf.pauseall();
        }

    }

    public void restart(){
        if(!soundstate){
            return;
        }
        if(sf!=null){
            sf.restart();
        }
    }



}
