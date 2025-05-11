package com.managingCardsOfBank.service;

import com.managingCardsOfBank.model.CardDto;

import java.util.List;

public interface CardUserService {

    List<CardDto> showCards(String email);

    CardDto requestCardBlock(String cardNumber, String email);

    String cardsTransfer(String cardNumberSender, String CardNumberRecipient, String email, String amount);

    CardDto showBlance (String cardNumber, String email);
}
