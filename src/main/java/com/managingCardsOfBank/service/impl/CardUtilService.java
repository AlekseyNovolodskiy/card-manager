package com.managingCardsOfBank.service.impl;

public class CardUtilService {
    public static String maskCardNumber(String cardnumber){

        return "**** **** **** "+cardnumber.substring(12,16);
    }
}
