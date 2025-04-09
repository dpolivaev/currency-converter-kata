package com.example.money;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

public class CurrencyConverterTest {
     
    private static CurrencyConverter createConverter() {
        CurrencyConverter converter = new CurrencyConverter();
        Bank europeanBank = new Bank(Currency.EUR);
        europeanBank.addExchangeRate(Currency.USD, new BigDecimal("1.1"));
        europeanBank.addExchangeRate(Currency.GBP, new BigDecimal("0.84"));
        converter.addBank(europeanBank);
 
        Bank americanBank = new Bank(Currency.USD);
        americanBank.addExchangeRate(Currency.MXN, new BigDecimal("18.5"));
        converter.addBank(americanBank);
 
        Bank britishBank = new Bank(Currency.GBP);
        britishBank.addExchangeRate(Currency.CNY, new BigDecimal("9.36"));
        converter.addBank(britishBank);
 
        Bank japaneseBank = new Bank(Currency.JPY);
        japaneseBank.addExchangeRate(Currency.USD, new BigDecimal("0.009091"));
        converter.addBank(japaneseBank);

        return converter;
 
    }
    
    private static CurrencyConverter uut = createConverter();

    @Test
    void convertsFromEurToUsd() {
        Money fromEur = new Money(new BigDecimal("2.00"), Currency.EUR);
        Money expectedUsd = new Money(new BigDecimal("2.20"), Currency.USD);

        Money actualUsd = uut.convert(fromEur, Currency.USD);

        assertThat(actualUsd).isEqualTo(expectedUsd);
    }

    
    @Test
    void convertsFromUsdToEur() {
        Money fromUsd = new Money(new BigDecimal("2.20"), Currency.USD);
        Money expectedEur = new Money(new BigDecimal("2.00"), Currency.EUR);

        Money actualEur = uut.convert(fromUsd, Currency.EUR);

        assertThat(actualEur).isEqualTo(expectedEur);
    }

    @Test
    void convertsFromUsdToGbp() {
        Money fromUsd = new Money(new BigDecimal("2.20"), Currency.USD);
        Money expectedGbp = new Money(new BigDecimal("1.68"), Currency.GBP);

        Money actualGbp = uut.convert(fromUsd, Currency.GBP);

        assertThat(actualGbp).isEqualTo(expectedGbp);
    }
    
    @Disabled
    @Test
    void shouldFindPath_EURtoUSDtoJPY() {
        Money fromEur = new Money(new BigDecimal("100.00"), Currency.EUR);
        Money expectedJpy = new Money(new BigDecimal("12100"), Currency.JPY);
        
        Money actualJpy = uut.convert(fromEur, Currency.JPY);
        
        assertThat(actualJpy).isEqualTo(expectedJpy);
    }
    
    @Disabled
    @Test
    void shouldFindPath_EURtoGBPtoCNY() {
        Money fromEur = new Money(new BigDecimal("100.00"), Currency.EUR);
        Money expectedCny = new Money(new BigDecimal("786.2"), Currency.CNY);
        
        Money actualCny = uut.convert(fromEur, Currency.CNY);
        
        assertThat(actualCny).isEqualTo(expectedCny);
    }
 } 