package com.example.money;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        
        Currency intermediate = Currency.USD;
        
        Optional<Money> intermediateMoney = Optional.empty();
        for (Bank bank : banks) {
            intermediateMoney = bank.convert(money, intermediate);
            if (intermediateMoney.isPresent()) {
                break;
            }
        }
        
        if (intermediateMoney.isPresent()) {
            for (Bank bank : banks) {
                Optional<Money> result = bank.convert(intermediateMoney.get(), targetCurrency);
                if (result.isPresent()) {
                    return result.get();
                }
            }
        }
        
        throw new IllegalArgumentException(
            "No exchange rate found for " + money.currency() + " to " + targetCurrency);
    }

    public Collection<Currency> findDirectlyConvertableCurrencies(Currency currency) {
        Set<Currency> currencies = banks.stream()
            .map(bank -> bank.convertableCurrencies())
            .filter(x -> x.contains(currency))
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());
            currencies.remove(currency);
        return currencies;
    }
} 