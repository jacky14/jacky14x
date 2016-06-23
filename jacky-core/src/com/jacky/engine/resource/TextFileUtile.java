package com.jacky.engine.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextFileUtile {

	public static String loadAsString(InputStreamReader fileReader){
		StringBuilder result = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(fileReader);
			String buffer = "";
			while((buffer = reader.readLine())!= null){
				result.append(buffer);
				result.append("\n");
			}
			reader.close();
			
		} catch(IOException e){
			System.err.println(e);
		}
		return result.toString();
	}
	

	
	
	
}
