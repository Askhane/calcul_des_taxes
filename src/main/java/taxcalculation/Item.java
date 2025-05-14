package taxcalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Item {
    public static final double TAX_RATE = 0.1;
    private final double price;
    public final String name;

    Item(String name, String price) {
        this.name = name;
        this.price = Double.parseDouble(price);
    }

    public double taxAmount() {
        double rawAmount = price * TAX_RATE;
        double roundedValueTimes2 = BigDecimal.valueOf(rawAmount * 2).setScale(1, RoundingMode.UP).doubleValue();
        return roundedValueTimes2 / 2;
    }

    public double taxedPrice() {
        return price + taxAmount();
    }

}
