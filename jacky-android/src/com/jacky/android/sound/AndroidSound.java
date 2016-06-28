package com.jacky.android.sound;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import com.jacky.android.JackyActivity;
import com.jacky.engine.sound.SoundInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/21.
 */
public class AndroidSound implements SoundInterface {




    private float mLeftVolume = 0.5f;
    private float mRightVolume = 0.5f;

    //短音效缓存
    private Map<String,Integer>  spcache = new HashMap<String,Integer>();

    private SoundPool soundPool = null;

    //最大音量
    int maxVolume = 0;
    //当前音量
    int currentVolume = 0;

    public void init(){
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM,5);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status){
                soundPool.play(sampleId , mLeftVolume , mRightVolume ,  0,  0,  1);
            }
        });
        AudioManager mAudioManager = (AudioManager) JackyActivity.jackyActivity.getSystemService(JackyActivity.jackyActivity.AUDIO_SERVICE);
        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        revol();
    }
    public void revol(){
        AudioManager mAudioManager = (AudioManager) JackyActivity.jackyActivity.getSystemService(JackyActivity.jackyActivity.AUDIO_SERVICE);
        currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mLeftVolume = (float)currentVolume / (float)maxVolume;
        mRightVolume = mLeftVolume;
    }


    @Override
    public void playsound(String soundname){
        Integer soundid = spcache.get(soundname);
        if(soundid == null){//加载声音文件
            try {
                soundid =  soundPool.load(JackyActivity.assetManager.openFd(soundname),0);
            }catch (IOException e){
                System.err.println("加载音效文件“"+soundname+"”时发生错误!!!");
            }
            spcache.put(soundname,soundid);
        }else{
            soundPool.play(soundid , mLeftVolume , mRightVolume ,  0,  0,  1);
        }

    }


    private MediaPlayer mediaPlayer = null;

    private String bakname = "";
    @Override
    public void playmusic(String musicname){
        if(bakname.equals(musicname)){
            return;
        }
        if(mediaPlayer!=null){
            mediaPlayer.release();
        }
        bakname = musicname;
        mediaPlayer = createMediaplayer(musicname);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }



    private MediaPlayer createMediaplayer(final String path){
        MediaPlayer mp = new MediaPlayer();
        try {
            final AssetFileDescriptor assetFileDescritor =JackyActivity.assetManager.openFd(path);
            mp.setDataSource(assetFileDescritor.getFileDescriptor(), assetFileDescritor.getStartOffset(), assetFileDescritor.getLength());
            mp.prepare();
            mp.setVolume(1f,1f);
        } catch (IOException e){
            //e.printStackTrace();
            System.err.println("加载背景音文件“"+path+"”时发生错误!!!");
        }
        return mp;
    }


    @Override
    public void pause(String name) {

    }

    @Override
    public void pauseall() {

        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }

    @Override
    public void restart() {


        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
}
