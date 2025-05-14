package taxcalculation;

public class Basket {
    private Item item = ItemParser.parseItem(" à 0.00");

    public String receipt() {
        Receipt receipt = new Receipt(item);
        return receipt.format();
    }

    public Basket addItem(String itemDescription) {
        item = ItemParser.parseItem(itemDescription);
        return this;
    }
}
