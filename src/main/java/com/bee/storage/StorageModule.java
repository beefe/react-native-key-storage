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
    private static final String PASSWORD = "password";

    public StorageModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void setGenericPassword(String name, String password, Callback callback) {
        spf = getReactApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        boolean saveOK = spf.edit().putString(PASSWORD, password).commit();
        if (callback != null) {
            callback.invoke(saveOK);
        }
    }

    @ReactMethod
    public void getGenericPassword(Callback callback) {
        if (callback != null) {
            if (spf != null) {
                String password = spf.getString(PASSWORD, "");
                callback.invoke(password);
            }
        }
    }

    @ReactMethod
    public void resetGenericPassword(Callback callback) {
        if (callback != null) {
            if (spf != null) {
                boolean clearOK = spf.edit().clear().commit();
                callback.invoke(clearOK);
            }
        }

    }
}
