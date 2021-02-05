package com.giftedcat.httplib.utils;

import android.text.TextUtils;

import com.giftedcat.httplib.BuildConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * http请求验签工具类
 *
 * @author GiftedCat
 */
public class EncryptionUtil {

    /**
     * 获取验签值
     */
    public static String encryptionParams(Request.Builder builder) throws UnsupportedEncodingException {

        FormBody body = (FormBody) (builder.build().body());

        TreeMap<String, String> options = new TreeMap<>();
        StringBuffer stringBuffer = new StringBuffer();

        options.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        options.put("appid", "jiabaotu" + BuildConfig.VERSION_NAME);
        options.put("nonce", "test_app");

        addBasicsData(builder, options);

        for (int i = 0; i < body.size(); i++) {
            /** 添加接口请求数据*/
            options.put(body.encodedName(i), body.encodedValue(i));
        }

        Iterator mIterator = options.entrySet().iterator();
        while (mIterator.hasNext()) {
            Map.Entry mEntry = (Map.Entry) mIterator.next();
            try {
                stringBuffer.append((String) mEntry.getKey());
                stringBuffer.append("=");
                stringBuffer.append(URLEncoder.encode((String) mEntry.getValue(), "UTF-8"));
                if (mIterator.hasNext()) {
                    stringBuffer.append("&");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        stringBuffer.append("&secret=" + BuildConfig.SECRET);

        options.clear();

        return md5(stringBuffer.toString());
    }

    /**
     * 头部添加基础数据
     * */
    public static void addBasicsData(Request.Builder builder, TreeMap<String, String> map){
        Iterator mIterator = map.entrySet().iterator();
        while (mIterator.hasNext()) {
            Map.Entry mEntry = (Map.Entry) mIterator.next();
            builder.addHeader(mEntry.getKey().toString(), mEntry.getValue().toString());
        }
    }

    /**
     * MD5加密
     *
     * @param string 需要加密的字符串
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
