package com.github.kaixindeken.TwTSSO.standard;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

public class HttpBuildQuery {

    public static String http_build_query(Map<String,Object> array) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();;
        for (Map.Entry<String,Object> entry: array.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        String res = sb.toString();
        res = JSONEncoder.json_encode(res);
        return res;
    }
}
