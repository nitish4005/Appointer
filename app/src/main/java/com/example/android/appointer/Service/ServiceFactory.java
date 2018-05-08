package com.example.android.appointer.Service;

import com.example.android.appointer.Service.NetworkCallsService.NetworkCalls_Service;
import com.example.android.appointer.Service.NetworkCallsService.NetworkCalls_ServiceImp;

/**
 * Created by Prasad on 19-Apr-18.
 */

public class ServiceFactory {

    private static SharedPreferencesService sharedPreferencesService = null;
    private static NetworkCalls_Service networkCalls_service = null;

    public static SharedPreferencesService getSharedPreferencesService() {
        if (sharedPreferencesService == null) {
            sharedPreferencesService = new SharedPreferencesServiceImpl() {
            };
        }
        return sharedPreferencesService;
    }

    public static NetworkCalls_Service getNetworkCalls_service() {
        if (networkCalls_service == null) {
            networkCalls_service = new NetworkCalls_ServiceImp() {
            };
        }
        return networkCalls_service;
    }
}