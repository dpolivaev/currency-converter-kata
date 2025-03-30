package com.example.money;

import java.util.List;
import java.util.ArrayList;

public class CurrencyConverter {
    private List<Bank> banks = new ArrayList<>();
    
    public void addBank(Bank bank) {
        banks.add(bank);
    }
    
    public Money convert(Money money, Currency targetCurrency) {
        if (money.currency() == targetCurrency) {
            return money;
        }
        
        for (Bank bank : banks) {
            try {
                return bank.convert(money, targetCurrency);
            } catch (IllegalArgumentException e) {
                // This bank can't do the conversion, try the next one
            }
        }
        
        throw new IllegalArgumentException(
            "No exchange rate found for " + money.currency() + " to " + targetCurrency);
    }
} 