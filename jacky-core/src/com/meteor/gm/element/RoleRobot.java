package com.meteor.gm.element;

import com.meteor.gm.GameTestScene;
import com.meteor.gm.util.Const;

/**
 *
 * 机器人类，与正常人物类相同，但发出的动作有每一帧更新方法中计算并更新
 * Created by Administrator on 2016/3/21.
 */
public class RoleRobot extends Role{

    /**
     * 机器人所在的区域
     */
    public int area = 0;

    public float[] pointxy = new float[2];

    /**
     * 设置机器人的活动区域
     */
    public void setArea(int a){
        this.area = a;
        Map.robot_point(this.area);
        setPoint(Map.dxy[0],Map.dxy[1]);
        Map.robot_point(this.area);
        pointxy[0] = Map.dxy[0];
        pointxy[1] = Map.dxy[1];

    }
    public RoleRobot(int role_, int camp_){
        super(role_, camp_);
        speec = speec+ (float)Math.random()*0.8f;
    }


    public static final int robot_state_daiji = 0;//站立待机
    public static final int robot_state_movetagp = robot_state_daiji + 1;//向随机目标坐标移动状态
    public static final int robot_state_movehero = robot_state_movetagp + 1;//向英雄，目标敌人移动

    //机器人当前的状态
    public int robot_state = robot_state_movetagp ;


    //机器人需要待机的时间，以帧为单位，默认1秒钟有60帧
    int daiji = 0;
    //机器人的奔跑速度，以防止跟随主角时出现人物重叠
    float speec = 1.3f;

    //移动带英雄身边定义的距离
    float leng_hero = 20;

    /**
     * 机器人更新类，每一帧检测是否要更新机器人状态
     */
    @Override
    public void update(){
        if(pause){//当pause为真 要么角色已经死亡，要么动态设定为死亡
            //System.out.println("hero pause ----------" + frameidx);
            return;
        }
        //1 检测模块：检测当前游戏状态设定机器人状态
        if(robot_state != robot_state_daiji){
            if(Map.isInArea(GameTestScene.hero.pointX,GameTestScene.hero.pointY,area)){
                float lx  = GameTestScene.hero.pointX - this.pointX;
                float ly  = GameTestScene.hero.pointY - this.pointY;
                if(lx>-200&& lx<200 && ly>-200 && ly<200){//检测到英雄,开始向英雄移动
                    /*int movehe = 85;//、85%的可能移动向英雄
                    if(GameTestScene.hero.current_aniid == ani_dao_s3 ||
                            GameTestScene.hero.current_aniid == ani_bishou_s3 ||
                            GameTestScene.hero.current_aniid == ani_jian_s3){
                        movehe = 50;
                    }
                    double rmm = (Math.random()*100);
                    if(rmm<movehe){
                        robot_state = robot_state_movehero;
                    }else{
                        robot_state = robot_state_movetagp;
                    }*/

                    robot_state = robot_state_movehero;
                }else{
                    robot_state = robot_state_movetagp;
                }
            }else {
                robot_state = robot_state_movetagp;
            }
        }
        //2 执行模块 根据1设定的机器人状态完成相应状态任务
        if(robot_state == robot_state_daiji){ //站立待机或站立攻击状态下不允许执行其他任何游戏逻辑
            daiji--;
            setAni(Role.ani_daoji);
            if(daiji<=0){//如果等待时间耗尽，开始根据游戏状态更新机器人状态
                //首先检测和英雄人物之间的距离
                float lx  = GameTestScene.hero.pointX - this.pointX;
                float ly  = GameTestScene.hero.pointY - this.pointY;
                if(lx>-leng_hero&& lx<leng_hero && ly>-leng_hero && ly<leng_hero){
                    //距离英雄在可攻击范围，发动技能
                    double d = Math.random()*100;//随机发动一项技能
                    role_tag_angle = (float)(Math.atan2(lx,-ly)* Const.HD2JD);
                    if(d <= 31){
                        setSkill(0);
                    }else if(d <= 62){
                        setSkill(1);
                    }else if(d <= 93){
                        setSkill(2);
                    }else{
                        setSkill(3);
                    }
                    daiji = (int)(Math.random()*90) + 60;
                }else{
                    robot_state = robot_state_movehero;
                }
            }
        }else if(robot_state == robot_state_movetagp){
            float dx = pointxy[0] - pointX ;
            float dy = pointxy[1] - pointY;
            if(   (dx<10f&&dx>-10f)   &&  (dy<10f && dy>-10f)){
                Map.robot_point(this.area);
                pointxy[0] = Map.dxy[0];
                pointxy[1] = Map.dxy[1];
            }else{
                float len =speec/(float)Math.sqrt(dx*dx + dy*dy);
                setPoint(dx*len,dy*len);
            }
        }else if(robot_state == robot_state_movehero){
            float lx  = GameTestScene.hero.pointX - this.pointX;
            float ly  = GameTestScene.hero.pointY - this.pointY;
            if(   (lx<leng_hero&&lx>-leng_hero )   &&  (ly<leng_hero && ly>-leng_hero)){//移动到英雄身边 有一定概率待机上几秒 然后攻击英雄
                robot_state = robot_state_daiji;
                //追上玩家后等待一定时间
                daiji = (int)(Math.random()*90);
                leng_hero = 30f + (float)Math.random()*15f;
            }else{
                float len = speec/(float)Math.sqrt(lx*lx + ly*ly);
                setPoint(lx*len,ly*len);
            }
            //移动到英雄身边时更新状态为待机状态
        }
        super.update();
    }




}
