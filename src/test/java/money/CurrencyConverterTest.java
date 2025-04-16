package money;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CurrencyConverterTest {
	
    private final static CurrencyConverter uut = new CurrencyConverter(Arrays.asList(
    		new ExchangeRate(Currency.USD, Currency.FRF, new BigDecimal("3"))
	));

    @ParameterizedTest
    @MethodSource("provideCurrenciesAndAmounts")
    void converts1USDto3FRFUsingTheGivenExchangeRate(
    		Currency sourceCurrency, String sourceAmount,
    		Currency targetCurrency, String targetAmount
		) throws Exception {
        assertThat(uut.convert(new Money(new BigDecimal(sourceAmount), sourceCurrency), targetCurrency))
        	.isEqualTo(new Money(new BigDecimal(targetAmount), targetCurrency));
    }
    
    static Stream<Arguments> provideCurrenciesAndAmounts() {
        return Stream.of(
                Arguments.of(Currency.USD, "1",  Currency.FRF, "3"),
                Arguments.of(Currency.USD, "10", Currency.FRF, "30"),
                Arguments.of(Currency.FRF, "3",  Currency.USD, "1")
        );
    }
}
