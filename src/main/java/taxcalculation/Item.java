package taxcalculation;

class Item {
    public static final double TAX_RATE = 0.1;
    private final double price;
    public final String name;

    Item(String name, String price) {
        this.name = name;
        this.price = Double.parseDouble(price);
    }

    public double taxAmount() {
        return price * TAX_RATE;
    }

    public double taxedPrice() {
        return price + taxAmount();
    }

}
