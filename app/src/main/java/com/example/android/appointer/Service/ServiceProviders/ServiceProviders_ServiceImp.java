package com.example.android.appointer.Service.ServiceProviders;

import android.util.Log;

import com.example.android.appointer.Model.ServiceProvidersModel;
import com.example.android.appointer.Service.HttpCallsInterface;
import com.example.android.appointer.Service.HttpRetrofitClient;
import com.example.android.appointer.Service.ServiceFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit2.Call;

import static com.example.android.appointer.Common.SharedPreferenceConstants.SERVIVE_PROVIDERS_LIST;

/**
 * Created by Prasad on 22-Apr-18.
 */

public class ServiceProviders_ServiceImp implements ServiceProviders_Service {
    private HttpCallsInterface httpCallsInterface;
    private ServiceProvidersModel serviceProvidersModel = null;
    private Gson gson = new Gson();
    private HttpCallsInterface getHttpCallsInterface() {
        if (null == httpCallsInterface) {
            httpCallsInterface = HttpRetrofitClient.getClient().create(HttpCallsInterface.class);
        }
        return httpCallsInterface;
    }


    @Override
    public ServiceProvidersModel getServiceProviders(String type) {
        Log.i("nk","inside call methode");
        Gson gson = new Gson();
        Call<ServiceProvidersModel> getServiveProvidersList = getHttpCallsInterface().getListOfServiceProviders(type);
        try {
            serviceProvidersModel = getServiveProvidersList.execute().body();
            String json = gson.toJson(serviceProvidersModel);
            ServiceFactory.getSharedPreferencesService().save(SERVIVE_PROVIDERS_LIST,json);
        } catch (IOException e) {
            if(ServiceFactory.getSharedPreferencesService().contains(SERVIVE_PROVIDERS_LIST)){
                String json = ServiceFactory.getSharedPreferencesService().get(SERVIVE_PROVIDERS_LIST);
                Type bannerListType = new TypeToken<ServiceProvidersModel>() {}.getType();
                serviceProvidersModel = gson.fromJson(json, bannerListType);
            }
        }
        return serviceProvidersModel;
    }
}

