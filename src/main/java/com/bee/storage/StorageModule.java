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
    public void setGenericPassword(String key, String value, Callback callback) {
        if (spf != null) {
            boolean saveOK = spf.edit().putString(key, value).commit();
            if (callback != null) {
                if (saveOK) {
                    callback.invoke(null);
                } else {
                    callback.invoke("failed");
                }
            }
        }
    }

    @ReactMethod
    public void getGenericPassword(String key, Callback callback) {
        if (callback != null) {
            if (spf != null) {
                String value = spf.getString(key, "");
                callback.invoke(null, value);
            } else {
                callback.invoke("not found");
            }
        }
    }

    @ReactMethod
    public void resetGenericPassword(Callback callback) {
        if (callback != null) {
            if (spf != null) {
                boolean clearOK = spf.edit().clear().commit();
                if (clearOK) {
                    callback.invoke(null);
                } else {
                    callback.invoke("failed");
                }
            }
        }

    }
}
