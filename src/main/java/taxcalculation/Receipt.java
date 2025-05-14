package taxcalculation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

record Receipt(List<Item> items) {

    public String format() {
        StringBuilder receipt = new StringBuilder();

        receipt.append(itemList());
        if (!receipt.isEmpty()) {
            receipt.append(" ");
        }
        appendTotals(receipt);
        return receipt.toString();
    }

    private String itemList() {
        return items.stream().map(this::formattedItem).collect(Collectors.joining(" "));
    }

    private String formattedItem(Item item) {
        return item.name + " : " + formatPrice(item.taxedPrice());
    }

    private void appendTotals(StringBuilder receipt) {
        receipt
                .append("Montant des taxes : ").append(formatPrice(taxAmount()))
                .append(" Total : ").append(formatPrice(totalAmount()));
    }


    private BigDecimal totalAmount() {
        return items.stream().map(Item::taxedPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal taxAmount() {
        return items.stream().map(Item::taxAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private static String formatPrice(BigDecimal value) {
        return String.format(Locale.US, "%.2f", value);
    }
}
