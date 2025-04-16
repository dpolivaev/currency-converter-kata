package money;

import java.math.BigDecimal;

public record ExchangeRate (Currency source,
                                   Currency target,
                                   BigDecimal rate) {
}