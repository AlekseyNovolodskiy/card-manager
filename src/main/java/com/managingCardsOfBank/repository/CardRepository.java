package com.managingCardsOfBank.repository;

import com.managingCardsOfBank.entity.CardEntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntityInfo,Long> {

    CardEntityInfo findCardEntityInfoByCardNumber(String number);

    @Query(value = "select s from CardEntityInfo s where s.user.id = :userId")
    List<CardEntityInfo> showCardEntityByUser(Long userId);
}
