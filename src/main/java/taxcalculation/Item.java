package taxcalculation;

class Item {
    public static final double TAX_RATE = 0.1;
    private final double price;
    public final String name;

    public Item(String itemDescription) {
        int priceIndex = itemDescription.indexOf(" à ");
        name = itemDescription.substring(0, priceIndex);
        String extractedPrice = itemDescription.substring(priceIndex + 3);
        price = Double.parseDouble(extractedPrice);
    }

    public double taxAmount() {
        return price * TAX_RATE;
    }

    public double taxedPrice() {
        return price + taxAmount();
    }
}
