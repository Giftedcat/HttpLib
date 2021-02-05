//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.giftedcat.httplib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author admin
 */
public class SpEditor {
    private static SpEditor INSTANCE;
    Context mContext;

    public SpEditor(Context context) {
        this.mContext = context;
    }

    public static synchronized SpEditor getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SpEditor(context);
        }

        return INSTANCE;
    }

    public String loadStringInfo(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        String value = preferences.getString(key, "");
        return value;
    }

    public void saveStringInfo(String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 清除cookie
     * */
    public void clearCookie(){
        clearInfo("cookie");
    }

    /**
     * 清除cookie和本地保存信息
     * */
    public void clearInfo(String key){
        removeStringInfo(key);
        removeCookie();
    }

    /**
     * 清除本地保存的信息
     * */
    public void removeStringInfo(String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除Cookie
     * */
    public void removeCookie() {
        CookieSyncManager.createInstance(mContext);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public int loadIntInfo(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        int value = preferences.getInt(key, 0);
        return value;
    }

    public void saveIntInfo(String key, int value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public long loadLongInfo(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        long value = preferences.getLong(key, 0L);
        return value;
    }

    public void saveLongInfo(String key, Long value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public float loadFloatInfo(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        float value = preferences.getFloat(key, 0.0F);
        return value;
    }

    public void saveFloatInfo(String key, float value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public boolean loadBooleanInfo(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        boolean value = preferences.getBoolean(key, false);
        return value;
    }

    public void saveBooleanInfo(String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void saveHashMapInfo(HashMap<String, Object> map) throws ClassCastException {
        if (map != null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
            Editor editor = preferences.edit();
            Iterator iterator = map.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry entry = (Entry) iterator.next();
                String key = (String) entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    editor.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                } else if (value instanceof Long) {
                    editor.putLong(key, (Long) value);
                } else if (value instanceof Float) {
                    editor.putFloat(key, (Float) value);
                } else {
                    if (!(value instanceof Boolean)) {
                        throw new ClassCastException("saveHashMapInfo不支持的转换类型");
                    }

                    editor.putBoolean(key, (Boolean) value);
                }
            }

            editor.apply();
        }
    }

    public void saveObject(String key, Object obj) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            String objBase64 = Base64.encodeToString(baos.toByteArray(), 0);
            Editor editor = preferences.edit();
            editor.putString(key, objBase64);
            editor.apply();
        } catch (IOException ignored) {

        }

    }

    public Object loadObject(String key) {
        Object obj = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
        String objBase64 = preferences.getString(key, "");
        byte[] buffer = Base64.decode(objBase64, 0);
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);

        try {
            ObjectInputStream bis = new ObjectInputStream(bais);

            try {
                obj = bis.readObject();
            } catch (ClassNotFoundException var9) {
                var9.printStackTrace();
            }
        } catch (StreamCorruptedException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        return obj;
    }
}
