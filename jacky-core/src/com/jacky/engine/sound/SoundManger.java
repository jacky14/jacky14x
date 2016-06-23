package com.jacky.engine.sound;

/**
 * ��Ч������
 * Created by Administrator on 2016/3/21.
 */
public  class SoundManger {

    public boolean soundstate = true;
    private SoundManger (){}
    public SoundInterface sf;

    private String playmusicname ;
    /**
     * ��ʼ�������ӿ�
     * @param sf
     */
    public  void initInterface(SoundInterface sf){
        this.sf = sf;
    }

    private static SoundManger sm;
    /**
     * �����Ч������
     * @return
     */
    public static SoundManger share(){
        if(sm == null){
            sm = new SoundManger();
        }
        return sm;
    }

    /**
     * ������Ч�ļ�
     * @param soundname
     */
    public void playsound(String soundname){
        if(!soundstate){
            return;
        }
        sf.playsound(soundname);
    }

    /**
     * ���ű�������Ч
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
     * ��ͣ����ĳ����Ч�򱳾���
     * @param name
     */
    public void pause(String name){
        if(!soundstate){
            return;
        }
        sf.pause(name);
    }

    /**
     * ������������״̬ true ����  false�ر�
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
     * �رղ���ͣ������Ч�������ļ�
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
