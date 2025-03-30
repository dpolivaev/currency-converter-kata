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
        
        // Try direct conversion first
        for (Bank bank : banks) {
            try {
                return bank.convert(money, targetCurrency);
            } catch (IllegalArgumentException e) {
                // This bank can't do the conversion, try the next one
            }
        }
        
        // If direct conversion failed, try using USD as intermediate currency
        Currency intermediate = Currency.USD;
        
        // Step 1: Convert from source to intermediate currency
        Money intermediateMoney = null;
        for (Bank bank : banks) {
            try {
                intermediateMoney = bank.convert(money, intermediate);
                break;
            } catch (IllegalArgumentException e) {
                // This bank can't do the conversion, try the next one
            }
        }
        
        // Step 2: Convert from intermediate to target currency
        if (intermediateMoney != null) {
            for (Bank bank : banks) {
                try {
                    return bank.convert(intermediateMoney, targetCurrency);
                } catch (IllegalArgumentException e) {
                    // This bank can't do the conversion, try the next one
                }
            }
        }
        
        throw new IllegalArgumentException(
            "No exchange rate found for " + money.currency() + " to " + targetCurrency);
    }
} 