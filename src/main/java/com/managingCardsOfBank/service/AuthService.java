package com.managingCardsOfBank.service;


import com.managingCardsOfBank.model.responce.AuthenticationRequest;
import com.managingCardsOfBank.model.responce.AuthenticationResponce;
import com.managingCardsOfBank.model.responce.RegisterRequest;

public interface AuthService {
    AuthenticationResponce registrationNewUser (RegisterRequest request);
    AuthenticationResponce authUser(AuthenticationRequest request);

}
