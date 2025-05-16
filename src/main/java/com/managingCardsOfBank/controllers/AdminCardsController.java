package com.managingCardsOfBank.controllers;

import com.managingCardsOfBank.model.CardDto;
import com.managingCardsOfBank.model.NewCardDto;
import com.managingCardsOfBank.model.UserDto;
import com.managingCardsOfBank.service.CardAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.managingCardsOfBank.controllers.ControllerConstant.*;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping(CARD_CONTROLLER_HEAD)
@RequiredArgsConstructor
@Tag(name = "Управление аккаунтом для пользователей со статусом 'Администратор'", description = "Api для управления аккаунтом с доступом ADMIN")
public class AdminCardsController {

    private final CardAdminService cardAdminService;

    @Operation(summary = "добавление карты")
    @PostMapping (NEW_CARD)
    public String addNewCard (@RequestBody NewCardDto newCardDto, @RequestParam Integer userid){

        return cardAdminService.createNewCard(newCardDto,userid);
    }

    @Operation(summary = "удаление карты")
    @PostMapping (DELETE_CARD)
    public String deleteCard (@RequestBody CardDto cardDto){

        return cardAdminService.deleteCard(cardDto);
    }

    @Operation(summary = "изменение статуса карты на 'АКТИВНЫЙ'")
    @PostMapping (ACTIVATE_CARD)
    public CardDto activateCard (@RequestBody CardDto cardDto){

        return cardAdminService.activateCard(cardDto);
    }

    @Operation(summary = "изменение статуса карты на 'ЗАБЛОКИРОВАННЫЙ'")
    @PostMapping (BLOCK_CARD)
    public String blockCard (@RequestBody CardDto cardDto){

        return cardAdminService.blockCard(cardDto);
    }

    @Operation(summary = "Просмотр информации по картам'")
    @GetMapping (SHOW_CARDS_INFO)
    public List<CardDto> showCards(){

      return   cardAdminService.showAllCards();
    }

    @Operation(summary = "Редактирование данных пользователя")
    @PostMapping (EDIT_USERS)
    public void editUsers (@RequestBody UserDto userDto,@RequestParam String email){

        cardAdminService.editUsers(userDto,email);
    }
}

