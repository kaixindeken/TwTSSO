package io.github.kaixindeken.TwTSSO;

import com.alibaba.fastjson.*;
import io.github.kaixindeken.TwTSSO.https.MyX509TrustManager;
import io.github.kaixindeken.TwTSSO.standard.Base64Encoder;
import io.github.kaixindeken.TwTSSO.standard.Hmac;
import io.github.kaixindeken.TwTSSO.standard.JSONEncoder;
import io.github.kaixindeken.TwTSSO.standard.Str;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class Api {

    private final String appid;
    private final String appkey;
    private final boolean https;

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
        String server = "login.twt.edu.cn";
        return (this.https ? "https" : "http") + "://" + server;
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

        String query = "app_id=" + this.appid
                + "&time=" + System.currentTimeMillis()/1000
                + "&source=" + trans4;
        System.out.println("source: "+trans4);
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

    private JSONObject _request(String url) throws Exception {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        TrustManager[] tm = {new MyX509TrustManager()};
        sslContext.init(null,tm,new SecureRandom());
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL u = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) u.openConnection();
        connection.setRequestMethod("GET");
        connection.setSSLSocketFactory(ssf);
        connection.connect();

        StringBuilder res = new StringBuilder();
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String line = "";
        while ((line = reader.readLine()) != null){
            res.append(line);
        }
        return JSON.parseObject(res.toString());
    }

    JSONObject callApi(String api, Object source) throws Exception {
        return _request(_getServer()+api+'?'+_getQuery(source));
    }

    public JSONObject fetchUserInfo(Object token) throws Exception {
        return callApi("/sso/getUserInfo",token);
    }

    Object logout(Object token) throws Exception {
        return callApi("/logout",token);
    }
}