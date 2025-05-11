package com.managingCardsOfBank.service.impl;

import com.managingCardsOfBank.entity.CardEntityInfo;
import com.managingCardsOfBank.entity.UserEntityInfo;
import com.managingCardsOfBank.exeption.CardInfoException;
import com.managingCardsOfBank.exeption.UserException;
import com.managingCardsOfBank.mapper.CardUsersMapper;
import com.managingCardsOfBank.model.CardDto;
import com.managingCardsOfBank.model.CardStatus;
import com.managingCardsOfBank.repository.CardRepository;
import com.managingCardsOfBank.repository.UserRepository;
import com.managingCardsOfBank.service.CardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.managingCardsOfBank.controllers.ControllerConstant.USER_NO_EXIST;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CardUserServiceImpl implements CardUserService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardUsersMapper cardUsersMapper;

    @Override
    public List<CardDto> showCards(String email) {

        UserEntityInfo byEmail = userRepository.findByEmail(email).orElseThrow(() -> new UserException(USER_NO_EXIST));
        List<CardEntityInfo> cardEntityInfo = cardRepository.showCardEntityByUser(byEmail.getId());

        return cardUsersMapper.cardEntityToCardDto(cardEntityInfo);
    }

    @Transactional
    @Override
    public CardDto requestCardBlock(String cardNumber, String email) {

        if (!userRepository.existsByEmail(email)) {
            throw new UserException(USER_NO_EXIST);
        }
        CardEntityInfo cardEntityInfoByCardNumber = cardRepository.findCardEntityInfoByCardNumber(cardNumber);
        if (cardEntityInfoByCardNumber.getCardStatus().equals(CardStatus.BLOCKED)) {
            throw new CardInfoException("Статус карты заблокирован");
        }
        cardEntityInfoByCardNumber.setCardStatus(CardStatus.BLOCKED);

        return cardUsersMapper.cardEntityToCardDto(cardEntityInfoByCardNumber);
    }

    @Transactional
    @Override
    public String cardsTransfer(String cardNumberSender, String CardNumberRecipient, String email, String amount) {

        BigDecimal bigDecimalAmount = BigDecimal.valueOf(Double.valueOf(amount));

        if (!userRepository.existsByEmail(email)) {
            throw new UserException(USER_NO_EXIST);
        }
        CardEntityInfo senderByCardNumber = cardRepository.findCardEntityInfoByCardNumber(cardNumberSender);
        CardEntityInfo recipientByCardNumber = cardRepository.findCardEntityInfoByCardNumber(CardNumberRecipient);

        if (isNull(senderByCardNumber)) {
            throw new CardInfoException("Карта не найдена");
        }
        if (isNull(recipientByCardNumber)) {
            throw new CardInfoException("Карта не найдена");
        }

        if (senderByCardNumber.getCardStatus().equals(CardStatus.BLOCKED)) {
            throw new CardInfoException("Статус карты заблокирован");
        }
        if (recipientByCardNumber.getCardStatus().equals(CardStatus.BLOCKED)) {
            throw new CardInfoException("Статус карты заблокирован");
        }
        int i = senderByCardNumber.getBalance().compareTo(bigDecimalAmount);
        if (i < 1){
            throw new CardInfoException("Недостаточно средств");
        }


        return "";
    }

    @Override
    public CardDto showBlance(String cardNumber, String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new UserException(USER_NO_EXIST);
        }
        CardEntityInfo cardEntityInfoByCardNumber = cardRepository.findCardEntityInfoByCardNumber(cardNumber);

        if (isNull(cardEntityInfoByCardNumber) || cardEntityInfoByCardNumber.getCardStatus().equals(CardStatus.BLOCKED)) {
            throw new CardInfoException("Карта не найдена или заблокирована");
        }
        return cardUsersMapper.cardEntityToCardDto(cardEntityInfoByCardNumber);
    }
}
