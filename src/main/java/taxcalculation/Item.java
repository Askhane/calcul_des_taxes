package taxcalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Item {
    public static final BigDecimal TAX_RATE = new BigDecimal("0.1");
    private final double price;
    public final String name;

    Item(String name, String price) {
        this.name = name;
        this.price = Double.parseDouble(price);
    }

    public double taxAmount() {
        BigDecimal rawAmount = BigDecimal.valueOf(price).multiply(TAX_RATE);
        return roundToUpper5Hundredth(rawAmount);
    }

    private static double roundToUpper5Hundredth(BigDecimal value) {
        BigDecimal two = new BigDecimal("2");
        BigDecimal valueTimes2 = value.multiply(two);
        double roundedValueTimes2 = roundToUpperTenth(valueTimes2).doubleValue();
        return roundedValueTimes2 / 2;
    }

    private static BigDecimal roundToUpperTenth(BigDecimal value) {
        return value.setScale(1, RoundingMode.UP);
    }

    public double taxedPrice() {
        return price + taxAmount();
    }

}
