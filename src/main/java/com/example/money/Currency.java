package com.example.money;

public enum Currency {
    USD, EUR, GBP, MXN;

    public String code() {
        return name();
    }
} 