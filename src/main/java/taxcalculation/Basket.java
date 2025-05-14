package taxcalculation;

import java.util.Locale;

public class Basket {
    private boolean hasItem = false;

    private String itemPrice = "0.00";

    public String receipt() {
        StringBuilder receipt = new StringBuilder();
        if (hasItem) {
            receipt.append("1 CD musical : ").append(taxedPrice()).append(" ");
        }
        receipt.append("Montant des taxes : ").append(taxAmount()).append(" Total : ").append(totalAmount());
        return receipt.toString();
    }

    private String taxedPrice() {
        return String.format(Locale.US, "%.2f", Double.parseDouble(itemPrice) * 1.1);
    }

    private String totalAmount() {
        return taxedPrice();
    }

    private String taxAmount() {
        return String.format(Locale.US, "%.2f", Double.parseDouble(itemPrice) * 0.1);
    }

    public Basket addItem(String itemDescription) {
        itemPrice = itemDescription.substring(itemDescription.indexOf(" à ") + 3);
        hasItem = true;
        return this;
    }
}
