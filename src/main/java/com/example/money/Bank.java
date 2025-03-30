package com.example.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class Bank {
    private Currency baseCurrency;
    private Map<CurrencyPair, BigDecimal> rateCache = new HashMap<>();
    
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
        rateCache.put(new CurrencyPair(baseCurrency, baseCurrency), BigDecimal.ONE);
    }
    
    public Currency getBaseCurrency() {
        return baseCurrency;
    }
    
    public void addExchangeRate(Currency currency, BigDecimal rate) {
        rateCache.put(new CurrencyPair(baseCurrency, currency), rate);
        BigDecimal inverseRate = BigDecimal.ONE.divide(rate, 10, RoundingMode.HALF_EVEN);
        rateCache.put(new CurrencyPair(currency, baseCurrency), inverseRate);
    }
    
    public BigDecimal getExchangeRate(Currency from, Currency to) {
        CurrencyPair pair = new CurrencyPair(from, to);
        if (rateCache.containsKey(pair)) {
            return rateCache.get(pair);
        }
        
        if (from != baseCurrency && to != baseCurrency) {
            BigDecimal fromToBase = rateCache.get(new CurrencyPair(from, baseCurrency));
            BigDecimal baseToTo = rateCache.get(new CurrencyPair(baseCurrency, to));
            
            if (fromToBase != null && baseToTo != null) {
                return fromToBase.multiply(baseToTo)
                    .setScale(10, RoundingMode.HALF_EVEN);
            }
        }
        
        throw new IllegalArgumentException("Cannot convert from " + from + " to " + to);
    }
    
    public Money convert(Money money, Currency targetCurrency) {
        BigDecimal rate = getExchangeRate(money.currency(), targetCurrency);
        return money.convert(rate, targetCurrency);
    }
} 