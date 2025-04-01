package com.example.money;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;

public class CurrencyConverterTest {
     
    private static CurrencyConverter createConverter() {
        Bank europeanBank = new Bank(Currency.EUR);
        europeanBank.addExchangeRate(Currency.USD, new BigDecimal("1.1"));
        europeanBank.addExchangeRate(Currency.GBP, new BigDecimal("0.84"));

        Bank americanBank = new Bank(Currency.USD);
        americanBank.addExchangeRate(Currency.MXN, new BigDecimal("18.5"));

        Bank japaneseBank = new Bank(Currency.JPY);
        japaneseBank.addExchangeRate(Currency.USD, new BigDecimal("0.009091"));

        CurrencyConverter converter = new CurrencyConverter();
        converter.addBank(europeanBank);
        converter.addBank(americanBank);
        converter.addBank(japaneseBank);
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
    @Test
    void currencyConverter_shouldFindPathBetweenCurrenciesFromDifferentBanks() {
        Money fromEur = new Money(new BigDecimal("100.00"), Currency.EUR);
        Money expectedJpy = new Money(new BigDecimal("12100"), Currency.JPY);
        
        Money actualJpy = uut.convert(fromEur, Currency.JPY);
        
        assertThat(actualJpy).isEqualTo(expectedJpy);
    }
    @Test
    void currencyConverter_shouldFindAllCurrenciesDirectlyConvertableFromEUR() {
        Collection<Currency> convertableCurrencies = uut.findDirectlyConvertableCurrencies(Currency.EUR);
        assertThat(convertableCurrencies).containsExactlyInAnyOrder(Currency.GBP, Currency.USD);
    }
    @Test
    void currencyConverter_shouldFindAllCurrenciesDirectlyConvertableFromUSD() {
        Collection<Currency> convertableCurrencies = uut.findDirectlyConvertableCurrencies(Currency.USD);
        assertThat(convertableCurrencies).containsExactlyInAnyOrder(Currency.EUR, Currency.GBP, Currency.MXN, Currency.JPY);
    }

} 