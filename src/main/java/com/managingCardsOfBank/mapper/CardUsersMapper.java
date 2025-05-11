package com.managingCardsOfBank.mapper;

import com.managingCardsOfBank.entity.CardEntityInfo;
import com.managingCardsOfBank.model.CardDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CardUsersMapper {


    List<CardDto> cardEntityToCardDto(List<CardEntityInfo> listCardEntities);
    CardDto cardEntityToCardDto (CardEntityInfo cardEntityInfo);
}
