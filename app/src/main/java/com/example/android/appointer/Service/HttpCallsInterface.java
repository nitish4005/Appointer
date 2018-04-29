package com.example.android.appointer.Service;

import com.example.android.appointer.Model.ServiceProvidersModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Prasad on 27-Mar-18.
 */

public interface HttpCallsInterface {
    @GET("getUsers/{type}")
    public Call<ServiceProvidersModel> getListOfServiceProviders(@Path("type") String type);
}
