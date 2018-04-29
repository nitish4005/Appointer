package com.example.android.appointer.Service;

import com.example.android.appointer.Service.ServiceProviders.ServiceProviders_Service;
import com.example.android.appointer.Service.ServiceProviders.ServiceProviders_ServiceImp;

/**
 * Created by Prasad on 19-Apr-18.
 */

public class ServiceFactory {

    private static SharedPreferencesService sharedPreferencesService = null;
    private static ServiceProviders_Service serviceProviders_service = null;

    public static SharedPreferencesService getSharedPreferencesService() {
        if (sharedPreferencesService == null) {
            sharedPreferencesService = new SharedPreferencesServiceImpl() {
            };
        }
        return sharedPreferencesService;
    }

    public static ServiceProviders_Service getServiceProviders_service() {
        if (serviceProviders_service == null) {
            serviceProviders_service = new ServiceProviders_ServiceImp() {
            };
        }
        return serviceProviders_service;
    }
}