package com.example.money;

public enum Currency {
    USD, EUR, GBP, MXN, JPY;

    public String code() {
        return name();
    }
} 