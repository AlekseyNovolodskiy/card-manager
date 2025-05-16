package com.managingCardsOfBank.mapper;

import com.managingCardsOfBank.entity.CardEntityInfo;
import com.managingCardsOfBank.model.CardDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CardUsersMapper {


    List<CardDto> cardEntityToCardDto(List<CardEntityInfo> listCardEntities);


    CardDto cardEntityToCardDto (CardEntityInfo cardEntityInfo);

    @AfterMapping
    default void afterCardMapping(CardEntityInfo cardEntityInfo, @MappingTarget CardDto target) {

        if (cardEntityInfo.getCardNumber() != null) {
            String maskedNumber = "**** **** **** " + cardEntityInfo.getCardNumber().substring(12);
            target.setCardNumber(maskedNumber);
        }

    }

}
