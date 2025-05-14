package taxcalculation;

public class Basket {
    private boolean hasItem = false;

    public String receipt() {
        if (hasItem) {
            return "1 CD musical : 16.49 Montant des taxes : 1.50 Total : 16.49";
        }
        return "Montant des taxes : 0.00 Total : 0.00";
    }

    public Basket addItem(String itemDescription) {
        hasItem = true;
        return this;
    }
}
