package cn.edu.twt.TwTSSO.standard;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class Hmac {

    public static byte[] EncryptBinary(String algo, String data, String key) throws Exception{
        byte[] keyBytes = key.getBytes();
        String method = "Hmac"+algo.toUpperCase();
        SecretKeySpec signingkey = new SecretKeySpec(keyBytes, method);
        Mac mac = Mac.getInstance(method);
        mac.init(signingkey);
        return mac.doFinal(data.getBytes());
    }

    private static String Byte2Hex(byte[] b){
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (byte value : b) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            stmp = (Integer.toHexString(value & 0xFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString();
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
