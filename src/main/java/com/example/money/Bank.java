package com.example.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Bank {
    private final Currency baseCurrency;
    private final Set<Currency> bankCurrencies = new HashSet<>();
    private final Map<CurrencyPair, BigDecimal> rateCache = new HashMap<>();
    
    public static class CurrencyPair {
        private final Currency from;
        private final Currency to;

        public CurrencyPair(Currency from, Currency to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CurrencyPair that = (CurrencyPair) o;
            return from == that.from && to == that.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
    
    public Bank(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
        bankCurrencies.add(baseCurrency);
        rateCache.put(new CurrencyPair(baseCurrency, baseCurrency), BigDecimal.ONE);
    }
    
    public Currency getBaseCurrency() {
        return baseCurrency;
    }
    
    public void addExchangeRate(Currency currency, BigDecimal rate) {
        bankCurrencies.add(currency);
        rateCache.put(new CurrencyPair(baseCurrency, currency), rate);
        BigDecimal inverseRate = BigDecimal.ONE.divide(rate, 10, RoundingMode.HALF_EVEN);
        rateCache.put(new CurrencyPair(currency, baseCurrency), inverseRate);
    }
    
    public Optional<BigDecimal> getExchangeRate(Currency from, Currency to) {
        CurrencyPair pair = new CurrencyPair(from, to);
        if (rateCache.containsKey(pair)) {
            return Optional.of(rateCache.get(pair));
        }
        
        if (from != baseCurrency && to != baseCurrency) {
            BigDecimal fromToBase = rateCache.get(new CurrencyPair(from, baseCurrency));
            BigDecimal baseToTo = rateCache.get(new CurrencyPair(baseCurrency, to));
            
            if (fromToBase != null && baseToTo != null) {
                return Optional.of(fromToBase.multiply(baseToTo)
                    .setScale(10, RoundingMode.HALF_EVEN));
            }
        }
        
        return Optional.empty();
    }
    
    public Optional<Money> convert(Money money, Currency targetCurrency) {
        return getExchangeRate(money.currency(), targetCurrency)
            .map(rate -> money.convert(rate, targetCurrency));
    }

    public Set<Currency> convertableCurrencies() {
        return bankCurrencies;
    }
} 