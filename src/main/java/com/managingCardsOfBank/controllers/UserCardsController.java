package com.managingCardsOfBank.controllers;

import com.managingCardsOfBank.model.CardDto;
import com.managingCardsOfBank.service.CardUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Управление аккаунтом для пользователей со статусом 'Пользователь'", description = "Api для управления аккаунтом с доступом USER")
public class UserCardsController {

    private final CardUserService cardUserService;

    @GetMapping(TRANSFER_CARDS)
    @Operation(summary = "перевод денег")
    @Parameter(name = "cardSender", description = "номер карты донора", example = "1111 2222 3333 4444")
    @Parameter(name = "cardRecipient", description = "номер карты реципиента", example = "1111 2222 3333 4444")
    @Parameter(name = "amount", description = "количество переводимых средств", example = "11р 11к введите данные через запятую")
    public String transferCards(@RequestParam String cardSender, @RequestParam String cardRecipient, @RequestParam String amount, Authentication jwtauth) {
        return cardUserService.cardsTransfer(cardSender, cardRecipient, jwtauth.getName(), amount);
    }

    @Operation(summary = "Просмотр всех карт")
    @GetMapping(SHOW_USER_CARD)
    public List<CardDto> showUserCards(Authentication jwtauth) {

        return cardUserService.showCards(jwtauth.getName());
    }

    @Operation(summary = "Запрос на блокирование карты")
    @Parameter(name = "cardNumber", description = "номер карты", example = "1111 2222 3333 4444")
    @GetMapping(REQUEST_BLOCK_CARD)
    public CardDto requestBlockCards(@RequestParam String cardNumber, Authentication jwtauth) {

        return cardUserService.requestCardBlock(cardNumber, jwtauth.getName());
    }

    @Operation(summary = "Запрос на просмотр баланса")
    @Parameter(name = "cardNumber", description = "номер карты", example = "1111 2222 3333 4444")
    @GetMapping(SHOW_BALANCE)
    public CardDto showBalanceCard(@RequestParam String cardNumber, Authentication jwtauth) {

        return cardUserService.showBlance(cardNumber, jwtauth.getName());
    }
}
