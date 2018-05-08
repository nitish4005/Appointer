package com.example.android.appointer.Service.NetworkCallsService;

import com.example.android.appointer.Model.ServiceProvidersModel;
import com.example.android.appointer.Model.SignupModelClass;
import com.example.android.appointer.Model.SignupResponseModel;

/**
 * Created by Prasad on 22-Apr-18.
 */

public interface NetworkCalls_Service {
    ServiceProvidersModel getServiceProviders(String type);
    SignupResponseModel signupUser(SignupModelClass signupModelClass);
}
