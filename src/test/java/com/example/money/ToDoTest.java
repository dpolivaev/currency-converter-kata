package com.example.money;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

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

IN PROGRESS:
Part 2: Multi-bank Currency Conversion
- Enable conversion between any two connected currencies
  - Implement path-finding through currency networks

BACKLOG:
  - Find path between currencies with best conversion rate
  - Identify when currencies exist in separate networks
  - Provide meaningful responses for impossible conversions
  - Communicate conversion failure appropriately
- Optimize conversion performance

Part 3: Offering Service
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
    void currencyConverter_shouldFindPathBetweenCurrenciesFromDifferentBanks() {
        // Set up banks with currencies that require multiple hops
        Bank europeanBank = new Bank(Currency.EUR);
        europeanBank.addExchangeRate(Currency.USD, new BigDecimal("1.1"));
        
        Bank japaneseBank = new Bank(Currency.JPY);
        japaneseBank.addExchangeRate(Currency.USD, new BigDecimal("0.00909091"));
        
        CurrencyConverter converter = new CurrencyConverter();
        converter.addBank(europeanBank);
        converter.addBank(japaneseBank);
        
        Money fromEur = new Money(new BigDecimal("100.00"), Currency.EUR);
        
        // 100 EUR → 110 USD → 12,100 JPY
        Money expectedJpy = new Money(new BigDecimal("12100.00"), Currency.JPY);
        
        Money actualJpy = converter.convert(fromEur, Currency.JPY);
        
        assertThat(actualJpy).isEqualTo(expectedJpy);
    }

}
