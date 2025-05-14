package taxcalculation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class BasketTest {

    @Test
    void noItemsGivesZeroTaxAndTotalIs0() {
        assertThat(new Basket().receipt()).isEqualTo("Montant des taxes : 0.00 Total : 0.00");
    }

    @Test
    void aCompactDiscIsTaxedAt10() {
        assertThat(new Basket()
                .addItem("1 CD musical à 14.99")
                .receipt())
                .isEqualTo("1 CD musical : 16.49 Montant des taxes : 1.50 Total : 16.49");
    }
}
