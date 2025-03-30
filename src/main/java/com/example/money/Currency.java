package com.example.money;

public enum Currency {
    USD(2), EUR(2), GBP(2), MXN(2), JPY(0);
    
    public final int scale;
    
    Currency(int scale) {
        this.scale = scale;
    }

    public String code() {
        return name();
    }
} 