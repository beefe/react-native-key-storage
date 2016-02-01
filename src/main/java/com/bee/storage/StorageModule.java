package com.bee.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class StorageModule extends ReactContextBaseJavaModule {

    public static final String REACT_CLASS = "RCTStorage";

    private SharedPreferences spf = null;
    private static final String FILE_NAME = "storage";

    public StorageModule(ReactApplicationContext reactContext) {
        super(reactContext);
        spf = getReactApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void saveValue(String key, String value, Callback callback) {
        try {
            spf.edit().putString(key, value).apply();
        } catch (Exception e) {
            if (callback != null) {
                callback.invoke(e.getMessage());
            }
        }
    }

    @ReactMethod
    public void getValue(String key, Callback callback) {
        if (callback != null) {
            try {
                String value = spf.getString(key, "");
                callback.invoke(null, value);
            } catch (Exception e) {
                callback.invoke(e.getMessage());
            }
        }
    }

    @ReactMethod
    public void resetValue(String key, Callback callback) {
        try {
            spf.edit().putString(key, "").apply();
        } catch (Exception e) {
            if (callback != null) {
                callback.invoke(e.getMessage());
            }
        }
    }
}
