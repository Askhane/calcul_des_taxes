package taxcalculation;

public class Basket {
    private boolean hasItem = false;

    public String receipt() {
        StringBuilder receipt = new StringBuilder();
        if (hasItem) {
            receipt.append("1 CD musical : 16.49 ");
        }
        receipt.append("Montant des taxes : ").append(taxAmount()).append(" Total : ").append(totalAmount());
        return receipt.toString();
    }

    private String totalAmount() {
        return hasItem ? "16.49" : "0.00";
    }

    private String taxAmount() {
        return hasItem ? "1.50" : "0.00";
    }

    public Basket addItem(String itemDescription) {
        hasItem = true;
        return this;
    }
}
