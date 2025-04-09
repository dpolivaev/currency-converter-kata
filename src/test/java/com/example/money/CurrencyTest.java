package com.example.money;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyTest {
    
    @Test
    void hasUniqueCode() {
        Currency usd = Currency.USD;
        assertThat(usd.code()).isEqualTo("USD");
    }
} 