package money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount, Currency currency) {
	Money convert(final Money rate) {
		return new Money(
				rate.amount().multiply(amount()).setScale(amount().scale(), RoundingMode.HALF_EVEN),
				rate.currency()
		);
	}
}