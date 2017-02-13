package lib.net;

import lib.net.util.DES;

public class Request {
	
	public static final String RE_URL = Const.service_url + "gk?X=";
	public static void req(String r,NetCall n){
		r = DES.share().encode(r);
		r = r.replace("+", "%2B");
		r = r.replace("/", "%2F");
		r = r.replace("=", "%3D");
		HttpUtil.getUrl(RE_URL + r, n);
	}
	/*JSONObject json = new JSONObject(str);
	String code = json.getString("code");
	JSONObject datajb = json.getJSONArray("data").getJSONObject(0);





	*/

	

}
