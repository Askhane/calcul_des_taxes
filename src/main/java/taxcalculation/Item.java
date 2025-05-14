package taxcalculation;

class Item {
    public static final double TAX_RATE = 0.1;
    private final double price;

    public Item(String itemDescription) {
        String extractedPrice = itemDescription.substring(itemDescription.indexOf(" à ") + 3);
        price = Double.parseDouble(extractedPrice);
    }

    public double taxAmount() {
        return price * TAX_RATE;
    }

    public double taxedPrice() {
        return price + taxAmount();
    }
}
