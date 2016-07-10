package com.meteor.gm.element;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventI;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Layer;
import com.jacky.engine.viewnode.Node2D;
import com.meteor.gm.util.Const;
import com.meteor.gm.util.NumUtil;

/**
 * Created by Administrator on 2016/3/31.
 */
public class ChenghaoLayer extends Layer{

    @Override
    public void init(){
        this.r2d = GenUITool.gen2d("chenghao.xui");

        for(int i=0;i<r2d.size();i++) {
            Node2D tmp = r2d.get(i);
            if("info0".equals(tmp.name)){
                info_s[0] = tmp;
            }else if("info1".equals(tmp.name)){
                info_s[1] = tmp;
            }else if("info2".equals(tmp.name)){
                info_s[2] = tmp;
            }else if("xz0".equals(tmp.name)){
                xzbtn[0] = tmp;
            }else if("xz1".equals(tmp.name)){
                xzbtn[1] = tmp;
            }else if("xz2".equals(tmp.name)){
                xzbtn[2] = tmp;
            }else if("num0s".equals(tmp.name)){
                num[0] = tmp;
            }else if("num0g".equals(tmp.name)){
                num[1] = tmp;
            }else if("num1s".equals(tmp.name)){
                num[2] = tmp;
            }else if("num1g".equals(tmp.name)){
                num[3] = tmp;
            }else if("num2s".equals(tmp.name)){
                num[4] = tmp;
            }else if("num2g".equals(tmp.name)){
                num[5] = tmp;
            }


        }
        reUI();
    }

    Node2D [] info_s = new Node2D[3];//选中信息
    Node2D [] num = new Node2D[6];//数字信息
    Node2D [] xzbtn = new Node2D[3];//选择按钮信息


    int [] xe = new int []{20,40,60};
    private void reUI(){
        int num_i = Const.getKillnum();
        int current = Const.getCh();
        if(current>99){
            current = 99;
        }
        int swnum = num_i%100/10;
        int gwnum = num_i%10;
        for(int i=0;i<3;i++){
             if(num_i>=xe[i]){
                 if(current==i){
                     info_s[i].isEnable = true;
                     xzbtn[i].isEnable = false;
                 }else{
                     info_s[i].isEnable = false;
                     xzbtn[i].isEnable = true;
                 }
             }else{
                 info_s[i].isEnable = false;
                 xzbtn[i].isEnable = false;
             }
            num[i*2].textureId = NumUtil.red_[swnum];
            num[i*2+1].textureId =  NumUtil.red_[gwnum];
        }



    }

    @Override
    public void update(){

    }

    @Override
    public void event(TouchEventJ tej){
        if(tej.event== TouchEventI.TOUCH_EVENT_END){
            for (int i = r2d.size() - 1; i >= 0; i--){
                Node2D dn = r2d.get(i);
                if (dn.isEnable && dn.node_type == Node2D.NODE_TYPE_BUTTON
                        && dn.isInRect(tej.tag_x, tej.tag_y)){

                    if ("close".equals(dn.name)){
                        backScene();
                    }else if("xz0".equals(dn.name)){
                        Const.saveCh(0);reUI();
                    }else if("xz1".equals(dn.name)){
                        Const.saveCh(1);reUI();
                    }else if("xz2".equals(dn.name)){
                        Const.saveCh(2);reUI();
                    }

                }
            }
        }else if(tej.event == TouchEventI.KEY_BACK){
            backScene();
        }
    }
    public void backScene(){//返回主场景
        isEnable = false;
        rescene.IsPause = false;
        Layer.LayerParameter lp = new Layer.LayerParameter();
        lp.layerid = Const.layer_chenghao;
        rescene.layerReturn(lp);
    }

}
