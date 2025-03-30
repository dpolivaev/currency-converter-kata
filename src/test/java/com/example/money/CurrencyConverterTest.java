package com.example.money;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

public class CurrencyConverterTest {
    public static Bank createEuropeanBank() {
        Bank europeanBank = new Bank(Currency.EUR);
        europeanBank.addExchangeRate(Currency.USD, new BigDecimal("1.1"));
        europeanBank.addExchangeRate(Currency.GBP, new BigDecimal("0.84"));
        return europeanBank;
    }
    
    public static Bank createAmericanBank() {
        Bank americanBank = new Bank(Currency.USD);
        americanBank.addExchangeRate(Currency.MXN, new BigDecimal("18.5"));
        return americanBank;
    }
    
    public static CurrencyConverter createConverter() {
        CurrencyConverter converter = new CurrencyConverter();
        converter.addBank(createEuropeanBank());
        converter.addBank(createAmericanBank());
        return converter;
    }
    
    private static CurrencyConverter uut = createConverter();

    @Test
    void currencyConverter_convertsFromEurToUsd() {
        Money fromEur = new Money(new BigDecimal("2.00"), Currency.EUR);
        Money expectedUsd = new Money(new BigDecimal("2.20"), Currency.USD);

        Money actualUsd = uut.convert(fromEur, Currency.USD);

        assertThat(actualUsd).isEqualTo(expectedUsd);
    }

    @Test
    void currencyConverter_convertsFromUsdToEur() {
        Money fromUsd = new Money(new BigDecimal("2.20"), Currency.USD);
        Money expectedEur = new Money(new BigDecimal("2.00"), Currency.EUR);

        Money actualEur = uut.convert(fromUsd, Currency.EUR);

        assertThat(actualEur).isEqualTo(expectedEur);
    }

    @Test
    void currencyConverter_convertsFromUsdToGbp() {
        Money fromUsd = new Money(new BigDecimal("2.20"), Currency.USD);
        Money expectedGbp = new Money(new BigDecimal("1.68"), Currency.GBP);

        Money actualGbp = uut.convert(fromUsd, Currency.GBP);

        assertThat(actualGbp).isEqualTo(expectedGbp);
    }
} 