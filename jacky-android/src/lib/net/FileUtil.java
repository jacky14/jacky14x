package lib.net;

import java.io.File;
import java.io.IOException;
/**
 * Created by yueyue on 2017/2/7.
 */

public class FileUtil {

    public static void creatFile(File file){
        File paren = file.getParentFile();
        if(!paren.exists()){
            paren.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static boolean chickFile(String path){
        File dirFile = new File(path);
        if(!dirFile.exists()){
            return false;
        }
        return true;
    }
}
