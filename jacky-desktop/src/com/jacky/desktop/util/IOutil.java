package com.jacky.desktop.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.jacky.engine.resource.LocalFile;

public class IOutil implements LocalFile{

	public static final String ROOT_PATH = "../jacky-desktop/assets/";
	public static String getAssetsName(String filepath){
		return ROOT_PATH + filepath;
	}
	@Override
	public InputStream loadFile(String fileName) {
		InputStream in = null;
		try {
			in = new FileInputStream(getAssetsName(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}
}
