package com.example.money;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

public class CurrencyConverter {
    private final List<Bank> banks = new ArrayList<>();
    private final Bank multiBank = new Bank();
    
    public void addBank(Bank bank) {
        banks.add(bank);
    }
    
    public Money convert(Money money, Currency targetCurrency) {
        if (money.currency() == targetCurrency) {
            return money;
        }

        multiBank.computeExchangeRateIfAbsent(money.currency(), targetCurrency, () -> {
            for (Bank bank : banks) {
                Optional<BigDecimal> result = bank.getExchangeRate(money.currency(), targetCurrency);
                if (result.isPresent()) {
                    return result.get();
                }
            }
            
            Currency intermediate = Currency.USD;
            
            Optional<BigDecimal> intermediateRate = Optional.empty();
            for (Bank bank : banks) {
                intermediateRate = bank.getExchangeRate(money.currency(), intermediate);
                if (intermediateRate.isPresent()) {
                    break;
                }
            }
            
            if (intermediateRate.isPresent()) {
                for (Bank bank : banks) {
                    Optional<BigDecimal> result = bank.getExchangeRate(intermediate, targetCurrency);
                    if (result.isPresent()) {
                        return intermediateRate.get().multiply(result.get());
                    }
                }
            }
            return null;
        });

        return multiBank.convert(money, targetCurrency)
        .orElseThrow(() -> new IllegalArgumentException(
            "No exchange rate found for " + money.currency() + " to " + targetCurrency));
       
     }
} 