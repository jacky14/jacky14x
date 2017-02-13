package lib.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import lib.net.util.DES;


public class HttpUtil {
public static void getUrl(final String url,final NetCall nc){
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
			
				System.out.println("¼ÓÔØµÄurlÊÇ£º>>>>>>>>>>>>>>>>>>>>>>"+url );
		        HttpGet httpGet = new HttpGet(url);
		       
		        HttpClient httpClient = new DefaultHttpClient();

		       

		        InputStream inputStream = null;
		        HttpResponse mHttpResponse = null;
		        HttpEntity mHttpEntity = null;
		        String result = "";
		        String line = "";
		        try
		        {
		            
		            mHttpResponse = httpClient.execute(httpGet);
		            
		            mHttpEntity = mHttpResponse.getEntity();
		            
		            

		           
		            inputStream = mHttpEntity.getContent();

		            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		          
		            
		            while (null != (line = bufferedReader.readLine()))
		            {
		                result += line;
		            }

		            
		        }
		        catch (Exception e)
		        {
		            e.printStackTrace();
		        }
		        finally
		        {
		            if(inputStream!=null){
		            	try {
							inputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        }
		        if(result!=null&&"".equals(result)){
		        	nc.geturlcall("service error");
		        } else {
		        	nc.geturlcall(DES.share().decode(result));
		        }
		        
			}
		}).start();
		
		
		
		
	}
}
