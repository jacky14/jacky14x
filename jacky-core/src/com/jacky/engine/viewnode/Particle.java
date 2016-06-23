package com.jacky.engine.viewnode;

import com.jacky.engine.MuvInfo;
import com.jacky.engine.MuvManger;
import com.jacky.engine.TextureManger;
import com.jacky.engine.buffer.BufferUtil;
import com.jacky.engine.math.Matrix4f;
import com.jacky.engine.resource.jxb.Load;
import com.jacky.start.AppDelegate;

import java.nio.FloatBuffer;

/**
 * Created by Administrator on 2016/3/14.
 */
public class Particle extends  DrawNode{

    /**
     * 粒子数量
     */
    public int num;


    public int vid ;
    public int tid ;
    public int alltexid ;
    public int drawVerCount;
    public int bindid ;
    public FloatBuffer move;
    public float[] moveinfo ;

    public FloatBuffer fb;


    public Particle(){
    }
    /**
     * 与 Node2D.texarr 纹理坐标所对应
     */
    public static final float[] pv = new float[]{
           1,0,1,         -1,0,1,      -1,0,-1,
          -1,0,-1,        1,0,-1 ,     1,0,1,
    };
    //一个粒子对象对应一个粒子组，包含几个粒子
    public Particle(String []texrecs){
        float[] vexs = new float[texrecs.length * pv.length];
        float[] texs = new float[texrecs.length * Node2D.texarr.length];
        for(int i=0;i<vexs.length;i++){
            vexs[i] = pv[i%pv.length] *8;
        }

        int index = 0;
        for(int i=0;i<texrecs.length;i++){
            MuvInfo mi =  MuvManger.getRect(texrecs[i]);

            alltexid = mi.texid;
            for(int j =index;j<index+mi.uvcoord.length;j++){
                texs[j] = mi.uvcoord[j-index];
            }
            index = index + mi.uvcoord.length;
        }
        vid = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(vexs));
        tid = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(texs));
        drawVerCount = vexs.length/3;

        float[] bindf = new float[drawVerCount]; //2个三角形合成一个粒子对象
        for(int i=0;i<bindf.length;i++){
            bindf[i] = i / 6 ;
        }
        bindid = AppDelegate.share().graphicsTool.bindBuffer(BufferUtil.createFloatBuffer(bindf));
        moveinfo = new float[texrecs.length * 3 ];
        move = BufferUtil.createFloatBuffer(moveinfo);



    }
    public void updatePoist(){
        BufferUtil.setFloatBuffer(moveinfo,move);
    }
    public void updateRotate(){
        if(fb==null){
            fb = BufferUtil.createFloatBuffer(transfe.matrix);
        }else{
            BufferUtil.setFloatBuffer(transfe.matrix,fb);
        }
    }
    public Matrix4f transfe;


    @Override
    public void draw(){
        AppDelegate.share().graphicsTool.selectShader(GraphicsTool.particle_shader);
        AppDelegate.share().graphicsTool.setTexMode(TEX_MODE2);

        AppDelegate.share().graphicsTool.setBlend(true);
        AppDelegate.share().graphicsTool.DepthMask(false);

        AppDelegate.share().graphicsTool.drawIndexParticle(vid,tid,alltexid,drawVerCount,bindid,move,fb);
        AppDelegate.share().graphicsTool.setBlend(false);
        AppDelegate.share().graphicsTool.DepthMask(true);
    }

    @Override
    public void update(){
        //moveinfo   move
     for(int i=0;i<moveinfo.length/3;i++){
            //moveinfo[i*3] moveinfo[i*3 + 1]  moveinfo[i*3 + 2]
            if(moveinfo[i*3 + 2]>30){
                moveinfo[i*3] = (float)(Math.random()*100 - 50);
                moveinfo[i*3 + 1] = (float)(Math.random()*100 - 50);
                moveinfo[i*3 + 2] = 0;
            }else{
                moveinfo[i*3 + 2]+=0.1f;
            }
        }
        //moveinfo[2]+=0.01f;
        updatePoist();
    }

    @Override
    public void release(){
        //清除显卡中存储数据
        AppDelegate.share().graphicsTool.cleanBuff(vid);
        AppDelegate.share().graphicsTool.cleanBuff(tid);
        AppDelegate.share().graphicsTool.cleanBuff(bindid);
    }


    public void rotate(float a, float x, float y, float z){
        if(transfe == null){
            transfe = new Matrix4f();
        }
        transfe.rotate(a, x, y, z);
    }
    public  void scale( float x, float y, float z){
        if(transfe == null){
            transfe = new Matrix4f();
        }
        transfe.scaleM(x, y, z);
    }

    public void translate(float x,float y,float z){
        if(transfe == null){
            transfe = new Matrix4f();
        }
        transfe.translate(x, y, z);
    }
   /*
   * 初始化粒子纹理
   * */
   /* public static void initTexture(){
        for(int i=0;i< textures.length;i++){
            texids[i]=TextureManger.getTexture(textures[i]);
        }
    }
    public static void clearallTexture(){
        for(int i=0;i< textures.length;i++){
            TextureManger.clearTex(textures[i]);
        }
    }

    public static final int tex_lanse = 0;//蓝色粒子
    public static final int tex_qianhong = tex_lanse+1;//浅红色粒子
    public static final int tex_danlan = tex_qianhong+1;//淡蓝色粒子
    public static final int tex_luse = tex_danlan+1;//绿色粒子
    public static final int tex_huangse = tex_luse+1;//黄色粒子


    private static int [] texids = new int [textures.length];*/


    public static String [] textures = new String[]{
            "Particle/flashblue.jpg",
            "Particle/FLASH001.jpg",
            "Particle/ZSD1.jpg",
            "Particle/KANGL00.jpg"
    };

    @Override
    public boolean isInRect(float x, float y){
        return false;
    }
}
