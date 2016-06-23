

var file_hz = ".xui";
var out_path = [
    "F:\\mypro\\jacky-X\\jacky-android\\assets\\",
    "F:\\mypro\\jacky-X\\jacky-desktop\\assets\\"
];

var HelloWorldLayer = cc.Layer.extend({
    sprite:null,
    ctor:function () {
        //////////////////////////////
        // 1. super init first
        this._super();

        /////////////////////////////
        // 2. add a menu item with "X" image, which is clicked to quit the program
        //    you may modify it.
        // ask the window size
        var size = cc.winSize;
        // 0 游戏启动   1  场景关卡  2 游戏战斗 3加载界面
        // 4 礼包界面   5 人物选择层界面
        // 6 武器选择界面     7光环界面   8称号界面  9 暂停界面  10 通关界面
        var sceneIdx = 2;
        var datestr = null;
        if(sceneIdx == 0){//游戏启动界面
            datestr = [res.MainScene_json,"mainmenu"];
        }else if(sceneIdx == 1){//游戏关卡选择界面
            datestr = [res.level_scenejson,"levelscene"];
        }else if(sceneIdx == 2){//游戏战斗场景
            datestr = [res.main_gamejson,"maingame"];
        }else if(sceneIdx == 3){
            datestr = [res.load_scenejson,"loadscnen"];
        }else if(sceneIdx == 4){
            datestr = ["res/GiftLayer.json","giftlayer"];
        }else if(sceneIdx ==5){
            datestr = ["res/rolelayer.json","rolelayer"];
        }else if(sceneIdx ==6){
            datestr = ["res/EquipLayer.json","equipLayer"];
        }else if(sceneIdx ==7){
            datestr = ["res/GuanhuanLayer.json","guanhuanLayer"];
        }else if(sceneIdx == 8){
            datestr = ["res/Chenghao.json","chenghao"];
        }else if(sceneIdx == 9){
            datestr = ["res/pauseLayer.json","pauselayer"];
        }else if(sceneIdx == 10){
            datestr = ["res/gkendlayer.json","gkendlayer"];
        }
        var mainscene = ccs.load(datestr[0]);
        this.addChild(mainscene.node);

        var filename = datestr[1];
        var strmp ="";

        var chiles = mainscene.node.getChildren();


        for(var i =0 ;i<chiles.length;i++){
            var chile = chiles[i];
            strmp +=  chile.getName();strmp+=" ";
            strmp +=  chile.getTag();strmp+=" ";


            //* chile.getScaleX()
            var kuan_half = chile.getTextureRect().width * 0.5;
            var gao_half = chile.getTextureRect().height * 0.5;



            var zs = [-kuan_half,+gao_half];        var ys = [+kuan_half,+gao_half];


            var zx = [-kuan_half,-gao_half];         var yx = [+kuan_half,-gao_half];

            strmp+=this.array2str(zs); strmp+=this.array2str(ys); strmp+=this.array2str(yx);
            strmp+=this.array2str(yx); strmp+=this.array2str(zx); strmp+=this.array2str(zs);

            var pointx = - (size.width*0.5);
            var pointy = - (size.height*0.5);

            strmp+=(chile.getPosition().x+pointx).toFixed(2);strmp+=" "; strmp+=(chile.getPosition().y+pointy).toFixed(2);strmp+=" ";
            strmp+=chile.getScaleX().toFixed(2);strmp+=" "; strmp+=chile.getScaleY().toFixed(2);strmp+=" ";
            strmp+=chile.getRotation().toFixed(2);

            /*  console.log("文件名称:"+  chile.getName()+ "，宽度：" +chile.getTextureRect().width + "，高度："+chile.getTextureRect().height
              +"，x坐标"    +chile.getPosition().x.toFixed(2)    +"，y坐标：" +chile.getPosition().y.toFixed(2)
              +"标记："+ chile.getTag()
              );*/
            strmp+="#";
        }


        this.outtxt(strmp,filename);

        return true;
    },
    array2str:function(arr){
        var smpstr = "";
        for(var i=0;i<arr.length;i++){
            smpstr+=arr[i].toFixed(2);
            smpstr+=" ";
        }
        return smpstr;
    },
    outtxt:function(text,fname){
        for(var i=0;i<out_path.length;i++){
            if (jsb.fileUtils.writeStringToFile(text,out_path[i]+fname+file_hz))
            {
                console.log("写入文件："+fname+"，成功");
            }
            else
            {
                console.log("写入文件："+fname+"，失败！！");
            }
        }
    }
});

var HelloWorldScene = cc.Scene.extend({
    onEnter:function () {
        this._super();
        var layer = new HelloWorldLayer();
        this.addChild(layer);
    }
});

