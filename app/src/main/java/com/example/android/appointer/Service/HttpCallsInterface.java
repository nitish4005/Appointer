package com.example.android.appointer.Service;

import com.example.android.appointer.Model.ServiceProvidersModel;
import com.example.android.appointer.Model.SignupModelClass;
import com.example.android.appointer.Model.SignupResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Prasad on 27-Mar-18.
 */

public interface HttpCallsInterface {
    @GET("getUsers/{type}")
    public Call<ServiceProvidersModel> getListOfServiceProviders(@Path("type") String type);

    @POST("sign-up")
    public Call<SignupResponseModel> sendSignupDetails(@Body SignupModelClass signupModelClass);

}
