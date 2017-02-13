package lib.net.util;


import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import lib.net.Const;

/**
 * DES���ܽ���
 * DES��һ�ֶԳƼ����㷨����ν�ԳƼ����㷨�������ܺͽ���ʹ����ͬ��Կ���㷨��DES�����㷨����IBM���о���
 * ����������������ʽ���ã�֮��ʼ�㷺���������ǽ�Щ��ʹ��Խ��Խ�٣���ΪDESʹ��56λ��Կ�����ִ�����������
 * 24Сʱ�ڼ��ɱ��ƽ⡣��Ȼ��ˣ���ĳЩ��Ӧ���У����ǻ��ǿ���ʹ��DES�����㷨�����ļ򵥽���DES��JAVAʵ��
 * ��
 * ע�⣺DES���ܺͽ��ܹ����У���Կ���ȶ�������8�ı���
 */
public class DES {
    private AlgorithmParameterSpec iv = null;// �����㷨�Ĳ����ӿڣ�IvParameterSpec������һ��ʵ��
    private Key key = null;
    public static DES des = null;
    public static DES share(){
        if(des == null){
            try {
                des = new DES();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return des;
    }

    private final byte[] DESkey = {104,106,117,64,97,97,35,110,99,36,103,98,52,107,94,99};//"hju@aa#nc$gb4k^c".getBytes(char_code);// ������Կ����ȥ
    private final byte[] DESIV = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};// ������������ȥ
    public DES() throws Exception {
        DESKeySpec keySpec = new DESKeySpec(DESkey);// ������Կ����
        iv = new IvParameterSpec(DESIV);// ��������
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Const.type_code);//SecretKeyFactory.getInstance("DES");// �����Կ����
        key = keyFactory.generateSecret(keySpec);// �õ���Կ����
    }
    public String encode(String data){
        String r = "";
        try {
            Cipher enCipher = Cipher.getInstance(Const.des_code);// �õ����ܶ���Cipher
            enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// ���ù���ģʽΪ����ģʽ��������Կ������
            byte[] pasByte = enCipher.doFinal(data.getBytes(Const.char_code));//���ܺ����������
            r = Base64.encode(pasByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public String decode(String data)  {
        String r = "";
        try {
            Cipher deCipher = Cipher.getInstance(Const.des_code);
            deCipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] pasByte = deCipher.doFinal(Base64.decode(data, Const.char_code));
            return new String(pasByte, Const.char_code);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
    
}
