package com.example.money;

public enum Currency {
    USD, EUR, GBP;

    public String code() {
        return name();
    }
} 