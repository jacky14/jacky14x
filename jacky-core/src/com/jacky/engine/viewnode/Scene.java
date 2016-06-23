package com.jacky.engine.viewnode;

import com.jacky.engine.input.TouchEventJ;
import com.jacky.start.AppDelegate;
import java.util.ArrayList;
import java.util.List;


public abstract class Scene {
		//当前场景是否加载完成
		public boolean isInitFinish = false;

	/**
	 * 场景是否暂停状态，，，默认打开一个层界面时，当前场景自动进入暂停状态
	 */
		public boolean IsPause = false;
		private List<DrawNode> chiles = new ArrayList<>();
		private List<DrawNode> chiles_translucence =  new ArrayList<>();

	public  List<DrawNode> chiles_2d = new ArrayList<>();

	/**
	 * 层对象列表，方便展示场景中的层，层对象的释放不属于场景控制
	 * 场景仅仅负责绘制层对象
	 */
	public   List<Layer>  layers = new ArrayList<>();


	public int uuid = 0;//当前场景的唯一标识，方便场景子对象区分当前所属的场景
	public void init(){
		if(isInitFinish){
			return;
		}
		initScene();
		isInitFinish = true;
	}
	public void addLayer(Layer layer){
		layer.init();
		layer.refScene(this);
		layers.add(layer);
	}
		public boolean isInitFinish(){
			return isInitFinish;
		}

		public void addChile(DrawNode dn){
			dn.refScene(this);
			chiles.add(dn);

		}

	public void addChile2D(DrawNode dn){
		dn.refScene(this);
		chiles_2d.add(dn);
	}

	/**
	 * 添加一个半透明的模型
	 * @param dn
	 * @return
     */
		public void addTranslucenceChile(DrawNode dn){
			dn.refScene(this);
			chiles_translucence.add(dn);
		}

		public void removeChile(DrawNode dn){
			dn.release();
			boolean isremove = chiles.remove(dn);
			if(!isremove){
				isremove = chiles_translucence.remove(dn);
			}
			if(!isremove){
				isremove = chiles_2d.remove(dn);
			}
		}
		public void runFrame(){

			//=====================渲染3D对象=========================
			renderDns(chiles);//渲染普通对象
			renderDns(chiles_translucence);//渲染透明对象

			//=====================渲染3D对象=========================

			//============================================绘制2D对象
			if(chiles_2d.size()>0||layers.size()>0){
				AppDelegate.share().graphicsTool.selectShader(GraphicsTool.def_shader);
				//切换到2D相机
				AppDelegate.share().graphicsTool.setTexMode(DrawNode.TEX_MODE1);

				AppDelegate.share().graphicsTool.setBlend(true);
				AppDelegate.share().graphicsTool.DepthMask(false);


				renderDns(chiles_2d);//渲染2D对象
				for(Layer lr: layers){//渲染层对象
					if(lr.isEnable){
						lr.update_render();
					}
				}

				AppDelegate.share().graphicsTool.setBlend(false);
				AppDelegate.share().graphicsTool.DepthMask(true);
				//如果3D相机存在，则切换到3D相机
				if(AppDelegate.share().camera!=null){
					AppDelegate.share().graphicsTool.setCamera(AppDelegate.share().camera.getCameraM());
				}

			}
			//============================================绘制2D对象
			//更新场景逻辑
			update();



		}

	/**
	 * 渲染更新绘制对象列表
	 * @param dns
     */
	private final void renderDns(List<DrawNode> dns){
		for(DrawNode dn:dns){
			if(dn.isEnable){//如果当前对象可见
				if(!IsPause){
					dn.update();
				}
				dn.draw();
			}
		}
	}

		public void exit(){
			isInitFinish = false;
			//清空GPU中图片及顶点等数据缓存
			clearDnList(chiles);
			clearDnList(chiles_translucence);
			clearDnList(chiles_2d);

			System.gc();
			exitScene();
			AppDelegate.share().nextSceneCallBack();
		}
	public void accept_event(TouchEventJ tej){
		for(int i = layers.size()-1;i>=0;i--){
			Layer tmp = layers.get(i);
			if(tmp.isEnable){
				tmp.event(tej);
				return;
			}
		}
		this.event(tej);
	}
	/**
	 * 清空绘制对象集合
	 * @param dns
     */
		private void clearDnList(List<DrawNode> dns){
			for(DrawNode dn:dns){
				dn.release();
			}
			dns.clear();
		}

		/**
		 * 初始化场景
		 */
		public abstract void initScene();
		
		/**
		 * 更新场景
		 */
		public abstract void update();

		/**
		 * 退出场景
		 */
		public abstract void exitScene();
		
		
		public abstract void event(TouchEventJ tej);

		//当场景中层关闭时提示场景层被关闭，方便场景处理游戏逻辑
		public abstract void layerReturn(Layer.LayerParameter lp);



}
