package com.example.money;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

public class MoneyTest {
    
    @Test
    void money_combinesAmountAndCurrency() {
        Money tenDollars = new Money(new BigDecimal("10"), Currency.USD);

        assertThat(tenDollars.amount()).isEqualTo(new BigDecimal("10"));
        assertThat(tenDollars.currency()).isEqualTo(Currency.USD);
        assertThat(tenDollars).isEqualTo(new Money(new BigDecimal("10"), Currency.USD));
    }
} 