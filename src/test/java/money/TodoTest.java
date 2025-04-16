package money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/*
 * DONE:
 *     Bank
 *         Publishes list of exchange rates
 *         Relative to a single base currency
 *     Exchange Rate
 *
 * COMMIT:
 *
 * BACKLOG:
 *     Bank
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
	    private final Currency source;
        private final Currency target;
        private final BigDecimal rate;

        public ExchangeRate(Currency source, Currency target, BigDecimal rate) {
            this.source = source;
            this.target = target;
            this.rate = rate;
	    }

        @Override
        public int hashCode() {
            return Objects.hash(rate, source, target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ExchangeRate other = (ExchangeRate) obj;
            return Objects.equals(rate, other.rate) && source == other.source
                    && target == other.target;
        }

        @Override
        public String toString() {
            return "ExchangeRate [source=" + source + ", target=" + target + ", rate=" + rate + "]";
        }


	}

	public enum Currency {
		USD,
		FRF,
	}

	/**
	 * @author dimitry
	 *
	 */
	public interface Bank {

		List<ExchangeRate> exchangeRates();

	}

	@Test
	void bank_publishesListOfExchangeRates() throws Exception {
		final Bank bank = Mockito.mock(Bank.class);
		@SuppressWarnings("unused")
        final List<ExchangeRate> exchangeRates = bank.exchangeRates();
	}
}
