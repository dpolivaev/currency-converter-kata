package com.example.money;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
TODO:

Part 1: Currency Conversion
- Implement currency converter (rate * amount)
  - Define currencies and their identifiers
  - Create monetary values with currency denomination
  - Handle direct conversions using published rates
  - Support conversion in both directions
  - Enable conversion between different currency pairs
- Support multiple banks with different base currencies
  - Represent banks and their published rates
  - Maintain separate rate tables for each bank
- Enable conversion between any two connected currencies
  - Implement path-finding through currency networks
  - Find optimal conversion sequence
  - Support multi-hop currency exchange
- Handle disconnected currencies
  - Identify when currencies exist in separate networks
  - Provide meaningful responses for impossible conversions
  - Communicate conversion failure appropriately
- Optimize conversion performance
  - Cache conversion rates
  - Use efficient lookup structures
  - Ensure thread safety for market operations

Part 2: Offering Service
- Create purchase requisition processing service
  - Define core business operations
  - Implement requisition fulfillment workflow
  - Establish necessary dependencies
- Model purchase requisitions with items and quantities
  - Define product specifications
  - Create requisition structure
  - Ensure requisition validity
- Manage supplier catalogs and offers
  - Represent supplier information and capabilities
  - Structure offer format and constraints
  - Combine multiple supplier catalogs
- Find most economical offer for a requisition
  - Develop offer comparison methodology
  - Address partial fulfillment scenarios
  - Implement offer ranking
- Convert supplier prices to purchaser's currency
  - Apply currency conversion
  - Handle conversion edge cases
  - Standardize price representation
- Apply margin calculation for final pricing
  - Store customer-specific margin rules
  - Calculate margins based on business rules
  - Determine final customer price
 */
public class ToDoTest {

    @Test
    void currency_hasUniqueCode() {
        Currency usd = Currency.USD;
        assertThat(usd.code()).isEqualTo("USD");
    }

    @Test
    void money_combinesAmountAndCurrency() {
        Money tenDollars = new Money(new BigDecimal("10"), Currency.USD);

        assertThat(tenDollars.amount()).isEqualTo(new BigDecimal("10"));
        assertThat(tenDollars.currency()).isEqualTo(Currency.USD);
        assertThat(tenDollars).isEqualTo(new Money(new BigDecimal("10"), Currency.USD));
    }

    @Test
    void currencyConverter_convertsFromEurToUsd() {
        Bank europeanBank = new Bank(Currency.EUR);
        europeanBank.addExchangeRate(Currency.USD, new BigDecimal("1.1"));
        europeanBank.addExchangeRate(Currency.GBP, new BigDecimal("0.84"));
        
        CurrencyConverter converter = new CurrencyConverter();
        converter.addBank(europeanBank);

        Money fromEur = new Money(new BigDecimal("2.00"), Currency.EUR);
        Money expectedUsd = new Money(new BigDecimal("2.20"), Currency.USD);

        Money actualUsd = converter.convert(fromEur, Currency.USD);

        assertThat(actualUsd).isEqualTo(expectedUsd);
    }

    @Test
    void currencyConverter_convertsFromUsdToEur() {
        Bank europeanBank = new Bank(Currency.EUR);
        europeanBank.addExchangeRate(Currency.USD, new BigDecimal("1.1"));
        
        CurrencyConverter converter = new CurrencyConverter();
        converter.addBank(europeanBank);

        Money fromUsd = new Money(new BigDecimal("2.20"), Currency.USD);
        Money expectedEur = new Money(new BigDecimal("2.00"), Currency.EUR);

        Money actualEur = converter.convert(fromUsd, Currency.EUR);

        assertThat(actualEur).isEqualTo(expectedEur);
    }

    @Test
    void currencyConverter_convertsFromUsdToGbp() {
        Bank europeanBank = new Bank(Currency.EUR);
        europeanBank.addExchangeRate(Currency.USD, new BigDecimal("1.1"));
        europeanBank.addExchangeRate(Currency.GBP, new BigDecimal("0.84"));
        
        CurrencyConverter converter = new CurrencyConverter();
        converter.addBank(europeanBank);

        Money fromUsd = new Money(new BigDecimal("2.20"), Currency.USD);
        Money expectedGbp = new Money(new BigDecimal("1.68"), Currency.GBP);

        Money actualGbp = converter.convert(fromUsd, Currency.GBP);

        assertThat(actualGbp).isEqualTo(expectedGbp);
    }
}
