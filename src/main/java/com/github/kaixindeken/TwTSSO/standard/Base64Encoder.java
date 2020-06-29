package com.github.kaixindeken.TwTSSO.standard;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encoder {

    public static String base64_encode(String data){
        String res = null;
        byte[] d = data.getBytes(StandardCharsets.UTF_8);
        res = Base64.getEncoder().encodeToString(d);
        return res;
    }

}
