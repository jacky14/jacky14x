package com.jacky.engine.sound;

/**
 * Created by Administrator on 2016/3/21.
 */
public interface SoundInterface {

    //��������
    public void revol();
    /**
     * ������Ч�ļ�
     * @param soundname
     */
    void playsound(String soundname);

    /**
     * ���ű�������Ч
     * @param musicname
     */
    void playmusic(String musicname);

    /**
     * ��ͣ����ĳ����Ч�򱳾���
     * @param name
     */
    void pause(String name);

    /**
     * �رղ���ͣ������Ч�������ļ�
     */
    void pauseall();

    /**
     * �����������ű�������
     */
    void restart();
}
