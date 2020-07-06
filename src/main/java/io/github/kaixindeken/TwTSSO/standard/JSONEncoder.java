package io.github.kaixindeken.TwTSSO.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONEncoder {

    public static String json_encode(Object map) throws
            JsonProcessingException {
        String s = new ObjectMapper().writeValueAsString(map);
        s = s.replace("/", "\\/");
        return s;
    }

}
