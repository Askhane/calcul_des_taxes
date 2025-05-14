package taxcalculation;

class ItemParser {
    private static final String PRICE_SEPARATOR = " à ";

    static Item parseItem(String itemDescription) {
        int priceIndex = itemDescription.indexOf(PRICE_SEPARATOR);
        String name = itemDescription.substring(0, priceIndex);
        String price = itemDescription.substring(priceIndex + 3);
        return new Item(name, price);
    }
}
