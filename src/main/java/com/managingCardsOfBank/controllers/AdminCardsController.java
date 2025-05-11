package com.managingCardsOfBank.controllers;

import com.managingCardsOfBank.model.CardDto;
import com.managingCardsOfBank.model.NewCardDto;
import com.managingCardsOfBank.model.UserDto;
import com.managingCardsOfBank.service.CardAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.managingCardsOfBank.controllers.ControllerConstant.*;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping(CARD_CONTROLLER_HEAD)
@RequiredArgsConstructor
public class AdminCardsController {

    private final CardAdminService cardAdminService;

    @PostMapping (NEW_CARD)
    public String addNewCard (@RequestBody NewCardDto newCardDto, @RequestParam Integer userid){

        return cardAdminService.createNewCard(newCardDto,userid);
    }

    @PostMapping (DELETE_CARD)
    public String deleteCard (@RequestBody CardDto cardDto){

        return cardAdminService.deleteCard(cardDto);
    }

    @PostMapping (ACTIVATE_CARD)
    public CardDto activateCard (@RequestBody CardDto cardDto){

        return cardAdminService.activateCard(cardDto);
    }

    @PostMapping (BLOCK_CARD)
    public String blockCard (@RequestBody CardDto cardDto){

        return cardAdminService.blockCard(cardDto);
    }

    @GetMapping (SHOW_CARDS_INFO)
    public List<CardDto> showCards(){
      return   cardAdminService.showAllCards();
    }

    @PostMapping (EDIT_USERS)
    public void editUsers (@RequestBody UserDto userDto,@RequestParam String email){

        cardAdminService.editUsers(userDto,email);
    }
}

