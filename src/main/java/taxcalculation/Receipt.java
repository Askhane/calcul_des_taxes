package taxcalculation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record Receipt(List<Item> items, BigDecimal taxAmount, BigDecimal totalAmount) {

    public static final String ELEMENT_SEPARATOR = " ";

    public String format() {
        return Stream.concat(itemDescriptions(), totals()).collect(Collectors.joining(ELEMENT_SEPARATOR));
    }

    private Stream<String> itemDescriptions() {
        return items.stream().map(this::formattedItem);
    }

    private String formattedItem(Item item) {
        return item.name + " : " + formatPrice(item.taxedPrice());
    }

    private Stream<String> totals() {
        return Stream.of("Montant des taxes : " + formatPrice(taxAmount) + " Total : " + formatPrice(totalAmount));
    }

    private static String formatPrice(BigDecimal value) {
        return String.format(Locale.US, "%.2f", value);
    }
}
