package com.managingCardsOfBank.model;

import lombok.Data;


public class UserAuthDto {
    private String password;
    private String login;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
