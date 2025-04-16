## International Market Kata

### 1. Currency Conversion

Implement a currency converter to support international trading. The converter must compute the selling price in a target currency, given the purchase price in a source currency using the formula:

`Y = rate * X` where `X` is the purchase price,  and`rate` is the exchange rate between source and target currencies.

Currency exchange rates are provided by `N` partner banks. Each bank publishes a list of exchange rates for various currencies, using their own base currency. For example:

- Bank A might define rates relative to **1 USD**,
- Bank B might define rates relative to **1 RUB**.

The converter must:

- Allow conversion between any two currencies from all provided lists, as long as there is **at least one common currency** among them (i.e. the exchange rate graph is connected).
- Return **no result** if the two currencies are in disjoint clusters, or if they are different and at least one of them is not present in any cluster.

**Optimize for speed**, since conversion is performed frequently.


### 2. Offering Service

Implement a service that:

- Receives a **purchase requisition** (a list of products with required quantities),
- Searches through a **combined catalog** of international supplier offers,
- Finds and returns the **cheapest valid offer** to fulfill the requisition.

Assume:

- Supplier prices are given in different currencies,
- Use the currency converter to convert supplier prices to the purchaser’s currency,
- The final customer price includes a **profit margin**, defined as a customer-specific **percentage** of the total cost.