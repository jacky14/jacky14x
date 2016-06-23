package com.jacky.android.util;

import java.io.IOException;
import java.io.InputStream;

import com.jacky.android.JackyActivity;
import com.jacky.engine.resource.LocalFile;

public class LoadFile implements LocalFile{

	@Override
	public InputStream loadFile(String fileName) {
		InputStream in = null;
		try {
			in =JackyActivity.assetManager.open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in;
	}

}
