package com.example.money;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

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
            Optional<Money> result = bank.convert(money, targetCurrency);
            if (result.isPresent()) {
                return result.get();
            }
        }
        
        throw new IllegalArgumentException(
            "No exchange rate found for " + money.currency() + " to " + targetCurrency);
    }
} 