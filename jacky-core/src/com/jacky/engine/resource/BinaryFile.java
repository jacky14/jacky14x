package com.jacky.engine.resource;

import java.io.IOException;
import java.io.InputStream;

public class BinaryFile {

	
	public byte[] dataBuffer;
	
	private int index = 0 ;
	
	
	public boolean isEnd(){
		if(index >= dataBuffer.length){
			return true;
		}else {
			return false;
		}
	}
	
	public BinaryFile(InputStream fileIn) {
		 
		try {
		int size = fileIn.available();
		dataBuffer = new byte[size];
		fileIn.read(dataBuffer);
		if(fileIn.available() != 0 ){
			System.out.println("BinaryFile Error!! file is not read finish!!!");
		}
		
		fileIn.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int readInt(){
		int result =(dataBuffer[3 + index] << 24) 
				+ ((dataBuffer[2 + index] << 24) >>> 8) 
				+ ((dataBuffer[1 + index] << 24) >>> 16) 
				+ ((dataBuffer[0 + index] << 24) >>> 24);
		this.index += 4;
		return result;
	}

	public static byte[] getByte(int i){
		byte[] tmp = new byte[4];
		tmp[0] = (byte)((i<<24)>>>24);
		tmp[1] = (byte)((i<<16)>>>24);
		tmp[2] = (byte)((i<<8)>>>24);
		tmp[3] = (byte)(i>>>24);
		return tmp;
	}


	public String readString(int size){
		byte[] dest = new byte[size];
		System.arraycopy(dataBuffer, index, dest, 0, size);
		index+=size;
		return new String(dest);
	}
	public final float readFloat(){
		int temp=this.readInt();
		return Float.intBitsToFloat(temp);
	}
	
	public final void skip(int count){
		this.index += count;
	}
	
	
	
	/**
	 * ��������ȡ
	 * ��ƽ̨���Խ��  win32ƽ̨�÷��������ݶ�������data1 ��ʱ200-300���� �� android ƽ̨��ʱ 1600-3100���� ��С��note �� С��1s��
	 * @param fileIn
	 * @throws IOException 
	 */
	public static void test1(InputStream fileIn) throws IOException{
		long t1 =  System.currentTimeMillis();
		//fileIn.available() �÷����Ƿ񷵻ص����ļ���С���������������� ���ܴ���bug
		int c =  fileIn.available() / 4;
		int [] data1 = new int [c];
		for(int i =0 ;i< c ;i++){
			byte[] buff = new byte[4];
			fileIn.read(buff);
			data1[i] = (buff[3] << 24) 
				+ ((buff[2] << 24) >>> 8) 
				+ ((buff[1] << 24) >>> 16) 
				+ ((buff[0] << 24) >>> 24);
			
		}
		
		System.out.println(System.currentTimeMillis() - t1);
		fileIn.close();
		System.out.println("end");
		
	}
	/**
	 * ���黺��
	 * ��ƽ̨���Խ�� win32ƽ̨�÷��������ݶ�������data1 ��ʱ0-30���� �� android ƽ̨��ʱ 90-180 ���루С��note �� С��1s��
	 * @param fileIn
	 * @throws IOException 
	 */
	public static void test2(InputStream fileIn) throws IOException{
		long t1 =  System.currentTimeMillis();
		int l =  fileIn.available() ;
		
		byte[] buffdata = new byte[l];
		
		fileIn.read(buffdata);
		
		int c = l / 4;
		int [] data1 = new int [c];
		for(int i =0 ;i< c ;i++){
			int index = i * 4 ;
			data1[i]=(buffdata[3 + index] << 24) 
			+ ((buffdata[2 + index] << 24) >>> 8) 
			+ ((buffdata[1 + index] << 24) >>> 16) 
			+ ((buffdata[0 + index] << 24) >>> 24);
			
		}
		
		System.out.println(System.currentTimeMillis() - t1);
		fileIn.close();
		System.out.println("end");
		
	}
	
	
}
