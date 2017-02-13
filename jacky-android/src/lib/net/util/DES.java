package lib.net.util;


import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import lib.net.Const;

/**
 * DES加密介绍
 * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
 * 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DES {
    private AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
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

    private final byte[] DESkey = {104,106,117,64,97,97,35,110,99,36,103,98,52,107,94,99};//"hju@aa#nc$gb4k^c".getBytes(char_code);// 设置密钥，略去
    private final byte[] DESIV = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};// 设置向量，略去
    public DES() throws Exception {
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        iv = new IvParameterSpec(DESIV);// 设置向量
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Const.type_code);//SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
    }
    public String encode(String data){
        String r = "";
        try {
            Cipher enCipher = Cipher.getInstance(Const.des_code);// 得到加密对象Cipher
            enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
            byte[] pasByte = enCipher.doFinal(data.getBytes(Const.char_code));//加密后的数据数组
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
