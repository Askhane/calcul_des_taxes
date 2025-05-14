package taxcalculation;

class ItemParser {
    private static final String PRICE_SEPARATOR = " à ";

    static Item parseItem(String itemDescription) {
        int priceIndex = itemDescription.indexOf(PRICE_SEPARATOR);
        String name = itemDescription.substring(0, priceIndex);
        String price = itemDescription.substring(priceIndex + 3);

        TaxRule taxRule = getTaxRule(name);

        return new Item(name, price, taxRule);
    }

    private static TaxRule getTaxRule(String name) {
        boolean isImported = isImported(name);
        boolean isExempted = isExempted(name);
        return TaxRule.get(isImported, isExempted);
    }

    private static boolean isImported(String name) {
        return name.contains("importé");
    }

    private static boolean isExempted(String name) {
        return name.contains("chocolat") || name.contains("pilules") || name.contains("livre");
    }
}
