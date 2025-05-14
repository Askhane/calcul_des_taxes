package taxcalculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.BigDecimal.TWO;

class Item {
    public static final BigDecimal STANDARD_TAX_RATE = new BigDecimal("0.1");
    public static final BigDecimal EXEMPTED_RATE = BigDecimal.ZERO;
    private final BigDecimal price;
    public final String name;

    Item(String name, String price) {
        this.name = name;
        this.price = new BigDecimal(price);
    }

    public BigDecimal taxAmount() {
        BigDecimal taxAmount = price.multiply(taxRate());
        return roundToUpper5Hundredth(taxAmount);
    }

    private BigDecimal taxRate() {
        return isExempted() ? EXEMPTED_RATE : STANDARD_TAX_RATE;
    }

    private boolean isExempted() {
        return name.contains("chocolat") || name.contains("pilules");
    }

    private static BigDecimal roundToUpper5Hundredth(BigDecimal value) {
        BigDecimal valueTimes2 = value.multiply(TWO);
        BigDecimal roundedValueTimes2 = roundToUpperTenth(valueTimes2);
        return roundedValueTimes2.divide(TWO, MathContext.DECIMAL32);
    }

    private static BigDecimal roundToUpperTenth(BigDecimal value) {
        return value.setScale(1, RoundingMode.UP);
    }

    public BigDecimal taxedPrice() {
        return price.add(taxAmount());
    }

}
