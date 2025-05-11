package com.managingCardsOfBank.service.impl;

import com.managingCardsOfBank.entity.CardEntityInfo;
import com.managingCardsOfBank.entity.UserEntityInfo;
import com.managingCardsOfBank.exeption.CardInfoException;
import com.managingCardsOfBank.exeption.UserException;
import com.managingCardsOfBank.mapper.CardUsersMapper;
import com.managingCardsOfBank.model.CardDto;
import com.managingCardsOfBank.model.CardStatus;
import com.managingCardsOfBank.model.NewCardDto;
import com.managingCardsOfBank.model.UserDto;
import com.managingCardsOfBank.repository.CardRepository;
import com.managingCardsOfBank.repository.UserRepository;
import com.managingCardsOfBank.service.CardAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.managingCardsOfBank.controllers.ControllerConstant.USER_NO_EXIST;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CardAdminServiceImpl implements CardAdminService {

    private final static String CARD_EXIST = "Карта уже существует";
    private final static String CARD_NO_EXIST = "Карты не существует";
    public static final String CARD_DADDED = "Карта добавлена";
    public static final String CARD_BLOCKED = "Статус карты изменен на \"BLOCKED\"";
    public static final String CARD_IS_ALREADY_ACTIVE = "Карта уже активна";
    public static final String CARD_ACTIVATED = "Статус карты изменен на \"АКТИВНА\"";
    public static final String CARD_DELETED = "Карта удалена";

    private final CardRepository cardRepository;
    private final CardUsersMapper cardMapper;
    private final UserRepository userRepository;


    @Override
    public String createNewCard(NewCardDto newCardDto, Integer userId) {

        CardEntityInfo cardEntityInfoByCardNumber = cardRepository.findCardEntityInfoByCardNumber(newCardDto.getCardNumber());

        if (!isNull(cardEntityInfoByCardNumber)) {
            throw new CardInfoException(CARD_EXIST);
        }

        cardRepository.save(prepareCardForDb(newCardDto, userId));

        return CARD_DADDED;
    }

    @Transactional
    @Override
    public String blockCard(CardDto cardDto) {

        CardEntityInfo cardEntityInfoByCardNumber = cardRepository.findCardEntityInfoByCardNumber(cardDto.getCardNumber());

        if (isNull(cardEntityInfoByCardNumber)) {
            throw new CardInfoException(CARD_NO_EXIST);
        }
        cardEntityInfoByCardNumber.setCardStatus(CardStatus.BLOCKED);


        return CARD_BLOCKED;
    }

    @Transactional
    @Override
    public CardDto activateCard(CardDto cardDto) {

        CardEntityInfo cardEntityInfoByCardNumber = cardRepository.findCardEntityInfoByCardNumber(cardDto.getCardNumber());

        if (isNull(cardEntityInfoByCardNumber)) {
            throw new CardInfoException(CARD_NO_EXIST);
        }
        if (cardEntityInfoByCardNumber.getCardStatus().equals(CardStatus.ACTIVE)) {
            throw new CardInfoException(CARD_IS_ALREADY_ACTIVE);
        }
        cardEntityInfoByCardNumber.setCardStatus(CardStatus.ACTIVE);

        return cardMapper.cardEntityToCardDto(cardEntityInfoByCardNumber);
    }

    @Override
    public String deleteCard(CardDto cardDto) {

        CardEntityInfo cardEntityInfoByCardNumber = cardRepository.findCardEntityInfoByCardNumber(cardDto.getCardNumber());

        if (isNull(cardEntityInfoByCardNumber)) {
            throw new CardInfoException(CARD_NO_EXIST);
        }

        cardRepository.delete(cardEntityInfoByCardNumber);

        return CARD_DELETED;
    }

    @Override
    public List<CardDto> showAllCards() {

        List<CardEntityInfo> allCardEntities = cardRepository.findAll();
        return cardMapper.cardEntityToCardDto(allCardEntities);
    }

    @Transactional
    @Override
    public void editUsers(UserDto userDto, String email) {

        UserEntityInfo byEmail = userRepository.findByEmail(email)
                .orElseThrow(()->new UserException(USER_NO_EXIST));

        byEmail.setLastName(userDto.getLastName());
        byEmail.setFirstName(userDto.getLastName());
        byEmail.setEmail(userDto.getEmail());
    }


    private CardEntityInfo prepareCardForDb(NewCardDto newCardDto, Integer userId) {

        Long userLongId = userId.longValue();

        UserEntityInfo userById = userRepository.findById(userLongId).orElseThrow(() -> new UserException(USER_NO_EXIST));

        CardEntityInfo cardEntityInfo = new CardEntityInfo();
        cardEntityInfo.setCardNumber(newCardDto.getCardNumber());
        cardEntityInfo.setCardStatus(CardStatus.ACTIVE);
        cardEntityInfo.setBalance(BigDecimal.valueOf(0.00));
        cardEntityInfo.setExpiredDate(LocalDate.now().plusYears(3));
        cardEntityInfo.setUser(userById);

        return cardEntityInfo;
    }

}

