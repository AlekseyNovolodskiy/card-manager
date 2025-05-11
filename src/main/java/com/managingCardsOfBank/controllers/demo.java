package com.managingCardsOfBank.controllers;

import com.managingCardsOfBank.entity.UserEntityInfo;
import com.managingCardsOfBank.exeption.UserException;
import com.managingCardsOfBank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/api/v1/auth")
public class demo {

   private final UserRepository userRepository;
    @GetMapping("/demo")
    public String hi(Authentication jwtauth) {

       UserEntityInfo byEmail = userRepository.findByEmail(jwtauth.getName()).orElseThrow(()->new UserException(""));
        System.out.println("Текущий пользователь: " + jwtauth.getName());
        System.out.println("Роли: " + jwtauth.getAuthorities());

        return byEmail.getEmail() + byEmail.getFirstName() + byEmail.getLastName();
    }
}
