package com.jacky.engine.buffer;

import java.nio.*;

public class BufferUtil {
	// These 3 functions are slightly different ways to return
    // the data we'll need further down the line. They aren't
    // that important in the grand scheme of things so I wouldn't
    // worry too much with what they are doing.
	public static ByteBuffer createByteBuffer(byte[] array){
		ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		result.put(array).flip();
		return result;
	}

	public static void setFloatBuffer(float[] array, FloatBuffer result){
		result.clear();
		result.put(array).flip();
		//return result;
	}

	public static FloatBuffer createFloatBuffer(float[] array){
		FloatBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();

		result.put(array).flip();
		return result;
	}
	
	public static IntBuffer createIntBuffer(int[] array){
		IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		result.put(array).flip();
		return result;
	}
	public static ShortBuffer createShortBuff(short[] array){
		ShortBuffer result = ByteBuffer.allocateDirect(array.length<<1).order(ByteOrder.nativeOrder()).asShortBuffer();
		result.put(array).flip();
		return result;
	}

}
