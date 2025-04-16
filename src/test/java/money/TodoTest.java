
package money;
import java.util.List;

/*
 * DONE:
 *     Bank
 *         Publishes list of exchange rates
 *         Relative to a single base currency
 *     Exchange Rate
 *
 * COMMIT:
 *     Currency Conversion
 *         Using the given exchange rates:
 *             FRF -> USD 3
 *             DEM -> EUR 5
 *         Converts 1 USD to 3 FRF using the given exchange rate
 *
 * BACKLOG:
 *     Clusters
 *     Currency:
 *         Source Currency
 *         Target Currency
 *     Price
 *         Purchase Price
 *         Selling Price
 *     Money
 *         Amount + Currency
 *     Currency Conversion
 *         Using the given exchange rates:
 *             FRF -> USD 3
 *             FRF -> USD 4
 *             USD -> FRF 1/3
 *             DEM -> EUR 5
 *
 */
public class TodoTest {

    /**
     * @author dimitry
     *
     */
    public interface Bank {
        List<ExchangeRate> exchangeRates();
    }
}
