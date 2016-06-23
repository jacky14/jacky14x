package test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ImageUtil {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 BufferedImage bi =ImageIO.read(new File("assets/test1.jpg"));
		
		 int[] rgb = new int [bi.getWidth()*bi.getHeight()];
		 
		 bi.getRGB(0, 0,bi.getWidth(),bi.getHeight(), rgb, 0, bi.getWidth());
		 BufferedImage newbi = new BufferedImage(bi.getWidth(),bi.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		 for(int i=0;i<rgb.length;i++){
			
			
			//int a = (rgb[i] & 0xff000000) >> 24;
			int r = (rgb[i] & 0xff0000) >> 16;
			int g = (rgb[i] & 0xff00) >> 8;
			int b = (rgb[i] & 0xff);
			
			int max =  max(r, b, g);
			
			int a =max  ;
			
			
			/*float h = a==0?0:255 / a;
			
			hero = Math.round(hero * h);
            g = Math.round(g * h);
            b = Math.round(b * h);*/
			
			rgb[i] = a << 24 | b << 16 | g << 8 | r;
			
			
			 
		 }
		 
		newbi.setRGB(0, 0,bi.getWidth(),bi.getHeight(), rgb, 0, bi.getWidth());
		 
		ImageIO.write(newbi, "png", new File("d:/test1.png"));
		 
		 
		
	}
	public static  int max(int r ,int b,int g){
		if(r>=b&&r>=g){
			return r;
		}else if(b>=r&&b>=g){
			return b;
		}else if(g>=r&&g>=b){
			return g;
		}
		return 0;
	} 
}
