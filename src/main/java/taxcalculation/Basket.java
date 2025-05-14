package taxcalculation;

public class Basket {
    private Item item = new Item(" à 0.00");

    public String receipt() {
        Receipt receipt = new Receipt(item);
        return receipt.format();
    }

    public Basket addItem(String itemDescription) {
        item = new Item(itemDescription);
        return this;
    }
}
