package com.example.money;

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
- Replace CurrencyPairs by enclosed enum maps for speed

Part 2: Multi-bank Currency Conversion
- Enable conversion between any two connected currencies
  - Implement path-finding through currency networks
    - Convert 10 EUR to 12100 JPY using a list of banks
      - Find path: EUR(European bank) → USD(European bank, Japanese bank) → JPY(Japanese bank)
        - Find all currencies directly convertible from EUR
          - Find all banks that work with given currency (EUR) => European bank
          - Find all their currencies

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

  
  
}
