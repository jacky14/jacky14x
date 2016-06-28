package com.jacky.engine;

import com.jacky.engine.resource.BinaryFile;
import com.jacky.engine.viewnode.Button;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.engine.viewnode.Number2D;
import com.jacky.engine.viewnode.ProgressBar;
import com.jacky.start.AppDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/3/9.
 */
public class GenUITool {

    public static float z = -10;
    public static int nameidx = 0;
    public static int typeidx = 1;
    public static int vidx = 2;

    public static int PositionX = 14;
    public static int PositionY = 15;

    public static int ScaleX = 16;
    public static int ScaleY = 17;

    public static int Rotation = 18;


    public static float half_design_w = AppDelegate.design_width*0.5f;
    public static float half_design_h = AppDelegate.design_high*0.5f;


    public static List<Node2D> gen2d(String uifilename){
        BinaryFile bf = new BinaryFile(AppDelegate.share().localFile.loadFile(uifilename));

        String[] allstr = new String(bf.dataBuffer).split("#");
        //Scanner sc = new Scanner(AppDelegate.share().localFile.loadFile(uifilename));
        List<Node2D> r2d = new ArrayList<>();
        //while(sc.hasNext()){
        for(String iii: allstr){
            String[] code =iii.split(" ");
            int type = Integer.valueOf(code[typeidx]);
            float[] vex = new float[18];
            int idxstart = vidx;
            for(int i=0;i<6;i++){
                vex[i*3] = Float.valueOf(code[idxstart]);idxstart++;
                vex[i*3+1] = Float.valueOf(code[idxstart]);idxstart++;
                vex[i*3+2] = z;
            }
            Node2D tmpn2d = null;
            String[] name_file= code[nameidx].split("&");
            String texname ;
            String nodename = null;
            if(name_file.length>1){
                nodename = name_file[0];
                texname = name_file[1];
            }else{
                texname = name_file[0];
            }
            if(type==Node2D.NODE_TYPE_BUTTON){
                tmpn2d = new Button(vex,texname);
            }else if(type==Node2D.NODE_TYPE_PROGRESSBAR){
                tmpn2d = new ProgressBar(vex,texname);
            }else if(type==Node2D.NODE_TYPE_NUM){
                tmpn2d = new Number2D(vex,texname);
            }else {
                //如果tag 不是以上其中任意一个类型则设置为默认类型图片Node2D.NODE_TYPE_IMG
                tmpn2d = new Node2D(vex,texname);
            }

            tmpn2d.name = nodename;
            tmpn2d.point=new float[]{Float.valueOf(code[PositionX]),Float.valueOf(code[PositionY])};
            tmpn2d.scale = new float[]{Float.valueOf(code[ScaleX]),Float.valueOf(code[ScaleY])};
            tmpn2d.rotation = -Float.valueOf(code[Rotation]);
            //=====================
            float kuan_half = Math.abs(vex[0]);
            float gao_half = Math.abs(vex[1]);

            tmpn2d.rect =new float[]{
                    tmpn2d.point[0]-kuan_half+half_design_w,half_design_h - (tmpn2d.point[1]+gao_half),
                    tmpn2d.point[0]+kuan_half+half_design_w,half_design_h - (tmpn2d.point[1]-gao_half)
            };
            tmpn2d.rectpoint=new float[]{
                    tmpn2d.point[0]-kuan_half, tmpn2d.point[1]-gao_half,
                    tmpn2d.point[0]+kuan_half, tmpn2d.point[1]+gao_half
            };
            if(Math.abs(tmpn2d.rotation) > 5){
                tmpn2d.computeTran(true);
            }else{
                tmpn2d.computeTran(false);
            }
            r2d.add(tmpn2d);



        }

        return r2d;
    }

}
