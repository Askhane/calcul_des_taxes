package taxcalculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Item> items = new ArrayList<>();

    public String receipt() {
        Receipt receipt = new Receipt(items, taxAmount(), totalAmount());
        return receipt.format();
    }

    private BigDecimal totalAmount() {
        return items.stream().map(Item::taxedPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal taxAmount() {
        return items.stream().map(Item::taxAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public Basket addItem(String itemDescription) {
        items.add(ItemParser.parseItem(itemDescription));
        return this;
    }
}
