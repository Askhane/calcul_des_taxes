package taxcalculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

class Item {
    public static final BigDecimal STANDARD_TAX_RATE = new BigDecimal("0.1");
    private final BigDecimal price;
    public final String name;

    Item(String name, String price) {
        this.name = name;
        this.price = new BigDecimal(price);
    }

    public BigDecimal taxAmount() {
        BigDecimal taxRate = name.contains("chocolat") ? BigDecimal.ZERO : STANDARD_TAX_RATE;
        BigDecimal taxAmount = price.multiply(taxRate);
        return roundToUpper5Hundredth(taxAmount);
    }

    private static BigDecimal roundToUpper5Hundredth(BigDecimal value) {
        BigDecimal two = new BigDecimal("2");
        BigDecimal valueTimes2 = value.multiply(two);
        BigDecimal roundedValueTimes2 = roundToUpperTenth(valueTimes2);
        return roundedValueTimes2.divide(two, MathContext.DECIMAL32);
    }

    private static BigDecimal roundToUpperTenth(BigDecimal value) {
        return value.setScale(1, RoundingMode.UP);
    }

    public BigDecimal taxedPrice() {
        return price.add(taxAmount());
    }

}
