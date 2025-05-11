package com.managingCardsOfBank.controllers;

import com.managingCardsOfBank.entity.UserEntityInfo;
import com.managingCardsOfBank.exeption.UserException;
import com.managingCardsOfBank.model.CardDto;
import com.managingCardsOfBank.repository.UserRepository;
import com.managingCardsOfBank.service.CardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.managingCardsOfBank.controllers.ControllerConstant.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER')")
@RequestMapping(CARD_CONTROLLER_HEAD)
public class UserCardsController {

    private final CardUserService cardUserService;

    @GetMapping(TRANSFER_CARDS)
    public String transferCards(@RequestParam String cardSender, @RequestParam String cardRecipient, @RequestParam String amount, Authentication jwtauth) {
        return cardUserService.cardsTransfer(cardSender, cardRecipient, jwtauth.getName(), amount);
    }

    @GetMapping(SHOW_USER_CARD)
    public List<CardDto> showUserCards(Authentication jwtauth) {

        return cardUserService.showCards(jwtauth.getName());
    }

    @GetMapping(REQUEST_BLOCK_CARD)
    public CardDto requestBlockCards(@RequestParam String cardNumber, Authentication jwtauth) {

        return cardUserService.requestCardBlock(cardNumber, jwtauth.getName());
    }

    @GetMapping(SHOW_BALANCE)
    public CardDto showBalanceCard(@RequestParam String cardNumber, Authentication jwtauth) {
        return cardUserService.showBlance(cardNumber, jwtauth.getName());
    }
}
