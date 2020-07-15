package com.clean.way.rx.models;

public class Token {

    private String value;
    private Boolean isValid;

    public Token() {
        this.value = "abc-token";
        this.isValid = true;
    }

    public String getValue() {
        return value;
    }

    public Boolean isValid() {
        return isValid;
    }
}
