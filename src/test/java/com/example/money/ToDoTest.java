package com.example.money;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

/*
DONE:
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

  TODO:
Part 1: Currency Conversion
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
    
    private static CurrencyConverter uut;
    private static Bank europeanBank;
    private static Bank americanBank;
    
    @BeforeAll
    static void setUpAll() {
        // Create banks
        europeanBank = new Bank(Currency.EUR);
        europeanBank.addExchangeRate(Currency.USD, new BigDecimal("1.1"));
        europeanBank.addExchangeRate(Currency.GBP, new BigDecimal("0.84"));
        
        americanBank = new Bank(Currency.USD);
        americanBank.addExchangeRate(Currency.MXN, new BigDecimal("18.5"));
        
        // Create the converter and add banks
        uut = new CurrencyConverter();
        uut.addBank(europeanBank);
        uut.addBank(americanBank);
    }

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
