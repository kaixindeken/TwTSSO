package twt.sso.standard;

import java.util.Iterator;
import java.util.Map;

public class HttpBuildQuery {

    public static String http_build_query(Map<String,Object> array){
        String reString = null;
        //遍历数组形成akey=avalue&bkey=bvalue&ckey=cvalue形式的的字符串
        Iterator it = array.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,Object> entry =(Map.Entry) it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            reString += key+"="+value+"&";
        }
        reString = reString.substring(0, reString.length()-1);
        //将得到的字符串进行处理得到目标格式的字符串
        reString = java.net.URLEncoder.encode(reString);
        reString = reString.replace("%3D", "=").replace("%26", "&");
        return reString;
    }

}
