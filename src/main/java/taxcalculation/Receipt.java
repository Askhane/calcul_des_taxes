package taxcalculation;

import java.util.Locale;

record Receipt(Item item) {

    public String format() {
        StringBuilder receipt = new StringBuilder();
        if (item.taxedPrice() != 0) {
            receipt.append(formattedItem());
        }
        appendTotals(receipt);
        return receipt.toString();
    }

    private String formattedItem() {
        return item.name + " : " + formatPrice(item.taxedPrice()) + " ";
    }

    private void appendTotals(StringBuilder receipt) {
        receipt
                .append("Montant des taxes : ").append(formatPrice(taxAmount()))
                .append(" Total : ").append(formatPrice(totalAmount()));
    }


    private double totalAmount() {
        return item.taxedPrice();
    }

    private double taxAmount() {
        return item.taxAmount();
    }


    private static String formatPrice(double value) {
        return String.format(Locale.US, "%.2f", value);
    }
}
