package taxcalculation;

import java.math.BigDecimal;
import java.util.Locale;

record Receipt(Item item) {

    public String format() {
        StringBuilder receipt = new StringBuilder();
        if (item.taxedPrice().compareTo(BigDecimal.ZERO) != 0) {
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


    private BigDecimal totalAmount() {
        return item.taxedPrice();
    }

    private BigDecimal taxAmount() {
        return item.taxAmount();
    }


    private static String formatPrice(BigDecimal value) {
        return String.format(Locale.US, "%.2f", value);
    }
}
