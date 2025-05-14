package taxcalculation;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Item> items = new ArrayList<>();

    public String receipt() {
        Receipt receipt = new Receipt(items);
        return receipt.format();
    }

    public Basket addItem(String itemDescription) {
        items.add(ItemParser.parseItem(itemDescription));
        return this;
    }
}
