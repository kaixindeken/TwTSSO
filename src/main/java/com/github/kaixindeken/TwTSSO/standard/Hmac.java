package com.github.kaixindeken.TwTSSO.standard;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class Hmac {

    public static byte[] EncryptBinary(String algo, String data, String key) throws Exception{
        byte[] keyBytes = key.getBytes();
        String method = "Hmac"+algo.toUpperCase();
        //根据给定数组构造密钥
        SecretKeySpec signingkey = new SecretKeySpec(keyBytes, method);
        Mac mac = Mac.getInstance(method);
        mac.init(signingkey);
        return mac.doFinal(data.getBytes());
    }

    private static String Byte2Hex(byte[] b){
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            stmp = (Integer.toHexString(b[n] & 0xFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    public static String hash_hmac(String algo, String data, String key) throws Exception{
        byte[] secret = EncryptBinary(algo, data, key);
        return Byte2Hex(secret);
    }

    public static String hash_hmac(String algo, String data, String key, boolean raw_output) throws Exception{
        byte[] secret = EncryptBinary(algo, data, key);
        if (raw_output){
            return Arrays.toString(secret);
        }else{
            return Byte2Hex(secret);
        }

    }
}
