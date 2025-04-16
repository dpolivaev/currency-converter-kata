package money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class CurrencyConverter {
    private final Map<Currency, Map<Currency, ExchangeRate>> exchangeRates;

	public CurrencyConverter(final Collection<ExchangeRate> exchangeRates) {
    	this.exchangeRates = new EnumMap<>(Currency.class);
    	
    	exchangeRates.stream()
    		.forEach(r -> this.exchangeRates.computeIfAbsent(r.source(), x -> new EnumMap<>(Currency.class)).put(r.target(), r));
    	
    	exchangeRates.stream()
    		.forEach(r -> this.exchangeRates.computeIfAbsent(r.target(), x -> new EnumMap<>(Currency.class)).put(r.source(),
    				new ExchangeRate(r.target(), r.source(), BigDecimal.ONE.divide(r.rate(), 10, RoundingMode.HALF_EVEN))));
   	}

    public Money convert(final Money money, final Currency target) {
    	return money.convert(new Money(this.exchangeRates.get(money.currency()).get(target).rate(), target));
    }
}