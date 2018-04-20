package com.example.android.appointer.Service;

/**
 * Created by Prasad on 19-Apr-18.
 */

public class ServiceFactory {

    private static SharedPreferencesService sharedPreferencesService = null;


    public static SharedPreferencesService getSharedPreferencesService() {
        if (sharedPreferencesService == null) {
            sharedPreferencesService = new SharedPreferencesServiceImpl() {
            };
        }
        return sharedPreferencesService;
    }
}