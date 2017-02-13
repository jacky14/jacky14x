package lib.net;

public class Const {
	public static final String char_code = "UTF-8";
    public static final String type_code = "DES";
    public static final String des_code = "DES/CBC/PKCS5Padding";
    public static final String service_url = "http://192.168.1.115:8080/df/";


    /*Request.req(
                        et.getText().toString(),
                        new NetCall() {
                            @Override
                            public void geturlcall(String dfds) {
                                // TODO Auto-generated method stub
                                str = dfds;
                                hand.sendEmptyMessage(1);
                                showStr("获得返回值=" + dfds);
                            }
                        });


                DownloadImgFile.download("3.jpg", new NetCall() {
        @Override
        public void geturlcall(String str) {
            bitname = str;
            hand.sendEmptyMessage(3);
        }
    });*/


}
