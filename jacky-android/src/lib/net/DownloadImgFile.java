package lib.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Environment;

import android.graphics.Bitmap;

/**
 * Created by yueyue on 2017/2/7.
 */

public class DownloadImgFile {

    public static String remote_dir = Const.service_url;

    public static Activity act;
    public static String root;
    public static void init(Activity a){
        act = a;
        root = act.getFilesDir() + "/download_test/";
    }
   /* public DownloadImgFile(String picname, NetCall n) {
        localpath = act.getFilesDir() + "/download_test/" + picname;
        this.url = remote_dir + picname;
        this.nc = n;
    }*/

    //NetCall nc;
    public Bitmap createBitmap(String bn){
        Bitmap bm = BitmapFactory.decodeFile(bn);
        return bm;
    }
    //private static String path = Environment.getExternalStorageDirectory().toString();
    public static void download(final String picname,final NetCall n) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String localpath = root + picname;
                    String url = remote_dir + picname;


                    byte[] data = readImage(url);
                    File myCaptureFile = new File(localpath);
                    FileUtil.creatFile(myCaptureFile);
                    OutputStream os = new FileOutputStream(myCaptureFile);
                    os.write(data);
                    os.flush();
                    os.close();
                    //下载完成
                    n.geturlcall(localpath);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*String url;
    String localpath;*/

    //声明称为静态变量有助于调用
    public static byte[] readImage(String path) throws Exception {
        URL url = new URL(path);
        // 记住使用的是HttpURLConnection类
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        //如果运行超过5秒会自动失效 这是android规定
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();
        //调用readStream方法
        return readStream(inStream);
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        //把数据读取存放到内存中去
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }
}
