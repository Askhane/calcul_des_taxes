package taxcalculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.BigDecimal.TWO;

class Item {
    private final BigDecimal price;
    public final String name;
    private final TaxRule taxRule;

    Item(String name, String price, TaxRule taxRule) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.taxRule = taxRule;
    }

    public BigDecimal taxAmount() {
        BigDecimal standardTaxAmount = roundToUpper5Hundredth(price.multiply(taxRule.rate));
        BigDecimal importedTaxAmount = roundToUpper5Hundredth(price.multiply(taxRule.importedRate));

        return standardTaxAmount.add(importedTaxAmount);
    }

    public BigDecimal taxedPrice() {
        return price.add(taxAmount());
    }

    private static BigDecimal roundToUpper5Hundredth(BigDecimal value) {
        BigDecimal valueTimes2 = value.multiply(TWO);
        BigDecimal roundedValueTimes2 = roundToUpperTenth(valueTimes2);
        return roundedValueTimes2.divide(TWO, MathContext.DECIMAL32);
    }

    private static BigDecimal roundToUpperTenth(BigDecimal value) {
        return value.setScale(1, RoundingMode.UP);
    }

}
