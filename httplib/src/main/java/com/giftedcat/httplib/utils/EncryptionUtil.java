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
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * http请求验签工具类
 *
 * @author GiftedCat
 */
public class EncryptionUtil {

    /**
     * 新建Build
     * */
    public static Request.Builder newRequestBuilder(Request request) {
        Request.Builder builder = request.newBuilder();

        FormBody body = (FormBody) (request.body());

        TreeMap<String, String> options = new TreeMap<>();

        options.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        options.put("appid", "jiabaotu" + BuildConfig.VERSION_NAME);
        options.put("nonce", "test_app");

        addBasicsData(builder, options);

        if (body != null) {
            /** 参数在body中，添加接口请求数据*/
            for (int i = 0; i < body.size(); i++) {
                options.put(body.encodedName(i), body.encodedValue(i));
            }
        } else {
            HttpUrl params = request.url();
            /** 参数在url中，添加接口请求数据*/
            for (int i = 0; i < params.queryParameterNames().size(); i++) {
                options.put(params.queryParameterName(i), params.queryParameterValue(i));
            }
        }

        builder.addHeader("signature", encryptionParams(options));

        return builder;
    }

    /**
     * 获取验签值
     */
    public static String encryptionParams(TreeMap<String, String> options) {
        StringBuffer stringBuffer = new StringBuffer();

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
