package com.example.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.EnumMap;
import java.util.Optional;
import java.util.function.Supplier;

public class Bank {
    private final Currency baseCurrency;
    private final Map<Currency, Map<Currency, BigDecimal>> rateCache;
    
    public Bank() {
        this.baseCurrency = null;
        this.rateCache = new EnumMap<>(Currency.class);
    }
    public Bank(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
        this.rateCache = new EnumMap<>(Currency.class);
        computeExchangeRateIfAbsent(baseCurrency , baseCurrency, () -> BigDecimal.ONE);
    }

    BigDecimal computeExchangeRateIfAbsent(Currency baseCurrency, Currency targetCurrency, Supplier<BigDecimal> rateValueSupplier) {
        return rateCache.computeIfAbsent(baseCurrency, x -> new EnumMap<>(Currency.class))
        .computeIfAbsent(targetCurrency, x -> rateValueSupplier.get());
    }
      
    public void addExchangeRate(Currency currency, BigDecimal rate) {
        computeExchangeRateIfAbsent(baseCurrency, currency, () -> rate);
        BigDecimal inverseRate = BigDecimal.ONE.divide(rate, 10, RoundingMode.HALF_EVEN);
        computeExchangeRateIfAbsent(currency, baseCurrency, () -> inverseRate);
 
     }
    
    public Optional<BigDecimal> getExchangeRate(Currency from, Currency to) {
        return Optional.ofNullable(computeExchangeRateIfAbsent(from, to, () -> {
            if(from == baseCurrency || to == baseCurrency)
                return null;
                BigDecimal fromToBase = rateCache.get(from).get(baseCurrency);
                BigDecimal baseToTo = rateCache.get(baseCurrency).get(to);
                if (fromToBase != null && baseToTo != null) {
                    return fromToBase.multiply(baseToTo)
                        .setScale(10, RoundingMode.HALF_EVEN);
                }
                else
                    return null;
        }));
          
    }
    
    public Optional<Money> convert(Money money, Currency targetCurrency) {
        return getExchangeRate(money.currency(), targetCurrency)
            .map(rate -> money.convert(rate, targetCurrency));
    }
} 