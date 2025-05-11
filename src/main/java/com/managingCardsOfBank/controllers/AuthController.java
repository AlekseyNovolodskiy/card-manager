package com.managingCardsOfBank.controllers;

import com.managingCardsOfBank.model.responce.AuthenticationRequest;
import com.managingCardsOfBank.model.responce.AuthenticationResponce;
import com.managingCardsOfBank.model.responce.RegisterRequest;
import com.managingCardsOfBank.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.managingCardsOfBank.controllers.ControllerConstant.CARD_CONTROLLER_HEAD;

@RestController

@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponce> authenticate(@RequestBody AuthenticationRequest authrequest) {
        return ResponseEntity.ok(authService.authUser(authrequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponce> regUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registrationNewUser(request));
    }

    @GetMapping
    public ResponseEntity<String> hi() {
        return ResponseEntity.ok("hi");

    }
}