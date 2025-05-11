package com.managingCardsOfBank.service;

import com.managingCardsOfBank.model.CardDto;
import com.managingCardsOfBank.model.NewCardDto;
import com.managingCardsOfBank.model.UserDto;

import java.util.List;


public interface CardAdminService {

    String createNewCard(NewCardDto newCardDto, Integer userId);

    String blockCard (CardDto cardDto);

    CardDto activateCard (CardDto cardDto);

    String deleteCard (CardDto cardDto);

    List<CardDto> showAllCards();

    void editUsers(UserDto userDto, String email);

}
