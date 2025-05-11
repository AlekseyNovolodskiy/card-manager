package com.managingCardsOfBank.exeption;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CardManagerExceptionHandler {

    @ExceptionHandler(UserException.class)
    public String catchUserExeption(UserException u){
        return u.getMessage();
    }


    @ExceptionHandler(CardInfoException.class)
    public String catchTaskExeption(CardInfoException t){
        return t.getMessage();
    }
}
