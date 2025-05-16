package com.managingCardsOfBank.controllers;

import com.managingCardsOfBank.model.responce.AuthenticationRequest;
import com.managingCardsOfBank.model.responce.AuthenticationResponce;
import com.managingCardsOfBank.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Контролер для аутентификации", description = "Можно получить jwt токен")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;
    }
    @Operation(summary = "используем 'useremail/password' для получения пользователя с ролью USER и 'adminmail/password' для ADMIN")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponce> authenticate(@RequestBody AuthenticationRequest authrequest) {

        return ResponseEntity.ok(authService.authUser(authrequest));
    }

}