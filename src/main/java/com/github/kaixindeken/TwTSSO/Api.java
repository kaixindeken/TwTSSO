package com.github.kaixindeken.TwTSSO;

import com.github.kaixindeken.TwTSSO.curl.CurlFactory;
import com.github.kaixindeken.TwTSSO.curl.CurlLib;
import com.github.kaixindeken.TwTSSO.curl.Pointer;
import com.github.kaixindeken.TwTSSO.standard.*;
import net.minidev.json.JSONObject;

import java.util.Map;

import static com.github.kaixindeken.TwTSSO.curl.CurlOption.*;

public class Api {

    private String server = "login.twt.edu.cn";
    private String appid;
    private String appkey;
    private boolean https;

    public Api(String appid, String appkey, boolean https) {
        this.appid = appid;
        this.appkey = appkey;
        this.https = https;
    }

    public Api(String appid, String appkey) {
        this.appid = appid;
        this.appkey = appkey;
        this.https = true;
    }

    private String _getServer() {
        return (this.https ? "https" : "http") + "://" + this.server;
    }

    private String _getQuery() throws Exception {
        String query = "app_id=" + this.appid
                + "&time=" + System.currentTimeMillis()/1000;
        return query + "&sign=" + Hmac.hash_hmac("sha1",query,appkey);
    }

    private String _getQuery(Object source) throws Exception {
        String trans1 = JSONEncoder.json_encode(source);
        String trans2 = Base64Encoder.base64_encode(trans1);
        String trans3 = Str.strtr(trans2,"+/","-_");
        String trans4 = Str.rtrim(trans3,"=");
        System.out.println("source: "+trans4);

        String query = "app_id=" + this.appid
                + "&time=" + System.currentTimeMillis()/1000
                + "&source=" + trans4;
        return query + "&sign=" + Hmac.hash_hmac("sha1",query,appkey);
    }

    public String getLoginUrl() throws Exception {
        return _getServer()+"/sso/login?"+_getQuery();
    }

    public String getLoginUrl(Object redirUrl) throws Exception {
        return _getServer()+"/sso/login?"+_getQuery(redirUrl);
    }

    String getVerifiedPhoneQuery(String user, String token) throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("user",user);
        jo.put("token",token);
        return _getQuery(jo);
    }

    String getVerifiedPhoneSign(String phone, String token, String time) throws Exception {
        String biStr = Hmac.hash_hmac("sha256",phone+'@'+token+'@'+time,appkey,true);
        return Base64Encoder.base64_encode(biStr);
    }

    private JSONObject _request(String url){
        CurlLib curl = CurlFactory.getInstance();
        Pointer ch = curl.curl_init();
        curl.curl_setopt(ch, CURLOPT_URL, url);
        curl.curl_setopt(ch, CURLOPT_HEADER, false);
        curl.curl_setopt(ch, CURLOPT_RETURNTRANSFER, 1);

        return (JSONObject) curl.curl_exec(ch);
    }

    private JSONObject _request(String url, Map<String, Object> postData){
        CurlLib curl = CurlFactory.getInstance();
        Pointer ch = curl.curl_init();
        curl.curl_setopt(ch, CURLOPT_URL, url);
        curl.curl_setopt(ch, CURLOPT_HEADER, false);
        curl.curl_setopt(ch, CURLOPT_RETURNTRANSFER, 1);

        if (postData != null){
            curl.curl_setopt(ch, CURLOPT_POSTFIELDS, HttpBuildQuery.http_build_query(postData));
        }

        return (JSONObject) curl.curl_exec(ch);
    }

    JSONObject callApi(String api, Object source) throws Exception {
        return _request(_getServer()+api+'?'+_getQuery(source));
    }

    JSONObject callApi(String api, JSONObject source, Map<String, Object> postData) throws Exception {
        return _request(_getServer()+api+'?'+_getQuery(source), postData);
    }

    public JSONObject fetchUserInfo(Object token) throws Exception {
        return callApi("sso/getUserInfo",token);
    }

    JSONObject logout(Object token) throws Exception {
        return callApi("logout",token);
    }
}
