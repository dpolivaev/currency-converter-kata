package money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
 * DONE:
 * 
 * COMMIT:
 *     Bank
 *         Publishes list of exchange rates
 *         Relative to a single base currency
 *     Exchange Rate
 * 
 * BACKLOG:
 *     Clusters
 *     Currency Conversion
 *     Currency:
 *         Source Currency
 *         Target Currency
 *     Price
 *         Purchase Price
 *         Selling Price
 *     Money
 *         Amount + Currency
 * 
 */
public class TodoTest {
	public static class ExchangeRate {

	}

	public enum Currency {
		USD,
		FRF,
	}

	public static class Bank {

		private final Currency base;
		
		public Bank(final Currency base) {
			this.base = base;
		}

		public List<ExchangeRate> exchangeRates() {
			return Collections.singletonList(new ExchangeRate());
		}
	}

	@Test
	void testName() throws Exception {
		Currency base = Currency.USD;
		final Bank bank = new Bank(base);
		final List<ExchangeRate> exchangeRates = bank.exchangeRates();
		assertThat(exchangeRates).isNotEmpty();
	}
}
