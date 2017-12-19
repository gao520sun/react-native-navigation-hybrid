package com.navigationhybrid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.Log;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Listen on 2017/11/17.
 */

public class ReactBridgeManager {

    public interface ReactModuleRegistryListener {
        void onStartRegisterReactModule();
        void onEndRegisterReactModule();
    }

    private static final String TAG = "ReactNative";

    public static ReactBridgeManager instance = new ReactBridgeManager();

    private HashMap<String, Class<? extends NavigationFragment>> nativeModules = new HashMap<>();
    private HashMap<String, Bundle> reactModules = new HashMap<>();
    private CopyOnWriteArrayList<ReactModuleRegistryListener> reactModuleRegistryListeners = new CopyOnWriteArrayList<>();

    private boolean isReactModuleInRegistry = true;

    public ReactBridgeManager() {

    }

    public void install(@NonNull ReactNativeHost reactNativeHost) {
        this.reactNativeHost = reactNativeHost;
        this.setup();
    }

    public void registerNativeModule(String moduleName, Class<? extends NavigationFragment> clazz) {
        nativeModules.put(moduleName, clazz);
    }

    public boolean hasNativeModule(String moduleName) {
        return nativeModules.containsKey(moduleName);
    }

    public Class<? extends NavigationFragment> nativeModuleClassForName(String moduleName) {
        return nativeModules.get(moduleName);
    }

    public void registerReactModule(String moduleName, Bundle options) {
        reactModules.put(moduleName, options);
    }

    public boolean isReactModuleInRegistry() {
        return isReactModuleInRegistry;
    }

    @UiThread
    public void startRegisterReactModule() {
        reactModules.clear();
        isReactModuleInRegistry = true;
        for (ReactModuleRegistryListener listener : reactModuleRegistryListeners) {
            listener.onStartRegisterReactModule();
        }
    }

    @UiThread
    public void endRegisterReactModule() {
        isReactModuleInRegistry = false;
        for (ReactModuleRegistryListener listener : reactModuleRegistryListeners) {
            listener.onEndRegisterReactModule();
        }
    }

    @UiThread
    public void addReactModuleRegistryListener(ReactModuleRegistryListener listener) {
        reactModuleRegistryListeners.add(listener);
    }

    @UiThread
    public void removeReactModuleRegistryListener(ReactModuleRegistryListener listener) {
        reactModuleRegistryListeners.remove(listener);
    }

    public boolean hasReactModule(String moduleName) {
        return reactModules.containsKey(moduleName);
    }

    public Bundle reactModuleOptionsForKey(String moduleName) {
        return reactModules.get(moduleName);
    }

    public void sendEvent(String eventName, WritableMap data) {
        DeviceEventManagerModule.RCTDeviceEventEmitter emitter = getReactInstanceManager().getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        emitter.emit(eventName, data);
    }

    public void sendEvent(String eventName) {
        sendEvent(eventName, Arguments.createMap());
    }


    ReactNativeHost getReactNativeHost() {
        checkReactNativeHost();
        return reactNativeHost;
    }

    public ReactInstanceManager getReactInstanceManager() {
        checkReactNativeHost();
        return reactNativeHost.getReactInstanceManager();
    }

    private ReactNativeHost reactNativeHost;


    private void setup() {
        Log.w(TAG, toString() + " bridge manager setup");
        final ReactInstanceManager reactInstanceManager = getReactInstanceManager();
        reactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
            @Override
            public void onReactContextInitialized(ReactContext context) {
                Log.w(TAG, toString() + " react context initialized");
            }
        });
        reactInstanceManager.createReactContextInBackground();
    }

    private void checkReactNativeHost() {
        if (reactNativeHost == null) {
            throw new IllegalStateException("must call ReactBridgeManager#install first");
        }
    }

}