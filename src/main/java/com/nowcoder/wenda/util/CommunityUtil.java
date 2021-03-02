package com.nowcoder.wenda.util;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommunityUtil {


    //生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");

    }//MD加密

    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    //返回json数据
    public static String getJSONString(int code, String msg, Map<String, Object> map) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }

        return json.toString();
    }


    public static String getJSONString(int code, String msg) throws JSONException {

        return getJSONString(code, msg,null);
    }

    public static String getJSONString(int code) throws JSONException {

        return getJSONString(code,null,null);
    }

    public static void main(String[] args) throws JSONException {
        Map<String,Object> map=new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",23);
        System.out.println(getJSONString(0,"ok",map));
    }
}