package com.example.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal amount() {
        return amount;
    }

    public Currency currency() {
        return currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        return amount.equals(other.amount) && currency == other.currency;
    }

    public Money convert(BigDecimal rateValue, Currency to) {
        return new Money(amount.multiply(rateValue)
                .setScale(amount.scale(), RoundingMode.HALF_EVEN), to);
    }

    @Override
    public String toString() {
        return "Money [amount=" + amount + ", currency=" + currency + "]";
    }
} 