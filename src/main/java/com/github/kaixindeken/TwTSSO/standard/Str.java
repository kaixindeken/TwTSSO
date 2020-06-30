package com.github.kaixindeken.TwTSSO.standard;

public class Str {

    public static String strtr(String data, String from, String to){
        char[] f = from.toCharArray();
        char[] t = to.toCharArray();

        for (int i = 0; i < f.length; i++) {
            data = data.replace(f[i],t[i]);
        }

        return data;
    }

    public static String rtrim(String str, String ch){
        int num=str.length();
        for (int i = num- 1; i > -1; i--) {
            if (!(str.substring(i,i+1).equals(ch))) {
                return str.substring(0, i+1);
            }
        }
        return str;
    }

}
