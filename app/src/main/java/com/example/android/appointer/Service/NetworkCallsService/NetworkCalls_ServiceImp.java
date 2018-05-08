package com.example.android.appointer.Service.NetworkCallsService;

import android.util.Log;

import com.example.android.appointer.Model.ServiceProvidersModel;
import com.example.android.appointer.Model.SignupModelClass;
import com.example.android.appointer.Model.SignupResponseModel;
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

public class NetworkCalls_ServiceImp implements NetworkCalls_Service {
    private HttpCallsInterface httpCallsInterface;

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
        ServiceProvidersModel serviceProvidersModel = null;
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
    @Override
    public SignupResponseModel signupUser(SignupModelClass signupModelClass) {
        Log.i("nk","inside call methode signup");
        SignupResponseModel signupResponseModel = null;
        Call<SignupResponseModel> sendSignupDetails = getHttpCallsInterface().sendSignupDetails(signupModelClass);
        try {
            signupResponseModel = sendSignupDetails.execute().body();
        } catch (IOException e) {
        }
        return signupResponseModel;
    }
}

