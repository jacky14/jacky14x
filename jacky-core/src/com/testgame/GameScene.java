package com.testgame;

import java.util.List;

import com.jacky.engine.GenUITool;
import com.jacky.engine.input.TouchEventJ;
import com.jacky.engine.viewnode.Layer.LayerParameter;
import com.jacky.engine.viewnode.Node2D;
import com.jacky.engine.viewnode.Scene;

public class GameScene extends Scene{

	@Override
	public void initScene() {
		// TODO Auto-generated method stub
		List<Node2D> r2d = GenUITool.gen2d("TestGScene.xui");
		
		for(int i=0;i<r2d.size();i++){
			addChile2D(r2d.get(i));
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void event(TouchEventJ tej) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layerReturn(LayerParameter lp) {
		// TODO Auto-generated method stub
		
	}

}
