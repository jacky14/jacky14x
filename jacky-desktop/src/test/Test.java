package test;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jacky.desktop.util.IOutil;
import com.jacky.engine.resource.BinaryFile;
import com.jacky.engine.resource.meteorFile.GmbFileFormat;

import javax.imageio.ImageIO;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		//BinaryFile bf =new BinaryFile(new FileInputStream(IOutil.getAssetsName("sn22.gmb")));
		
		//System.out.println(bf.readString(10));
		
		
		//BinaryFile.test2(new FileInputStream(IOutil.getAssetsName("sn22.gmb")));
		
		BinaryFile bf =new BinaryFile(new FileInputStream(IOutil.getAssetsName("sn01.gmb")));
		
		GmbFileFormat gf =new GmbFileFormat();
		
		gf.readDataFromFilename(bf);
		
		
		
		System.out.println("");
		
	}
	public static void main1(String[] args) throws IOException {

		long st = System.currentTimeMillis();
		BufferedImage image = ImageIO.read(new FileInputStream("G:\\androidWork\\jacky-X\\jacky-desktop\\assets\\grass.jpg"));
		System.out.println("load need  time is " + (System.currentTimeMillis()-st));
	}
}
