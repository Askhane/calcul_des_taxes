package taxcalculation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class BasketTest {

    @Test
    void noItemsGivesZeroTaxAndTotalIs0() {
        assertThat(new Basket().receipt()).isEqualTo("Montant des taxes : 0.00 Total : 0.00");
    }

    @ParameterizedTest
    @CsvSource({
            "14.99, 16.49, 1.50",
            "12.00, 13.20, 1.20"
    })
    void aCompactDiscIsTaxedAt10(String itemPrice, String taxedPrice, String taxAmount) {
        assertThat(new Basket()
                .addItem("1 CD musical à " + itemPrice)
                .receipt())
                .isEqualTo("1 CD musical : " + taxedPrice + " Montant des taxes : " + taxAmount + " Total : " + taxedPrice);
    }

    @Test
    void aPerfumeFlaskIsTaxedAt10() {
        assertThat(
                new Basket()
                        .addItem("1 flacon de parfum à 18.99")
                        .receipt()
        ).isEqualTo("1 flacon de parfum : 20.89 Montant des taxes : 1.90 Total : 20.89");
    }

    @ParameterizedTest
    @CsvSource({
            "18.40, 20.25, 1.85",
            "12.00, 13.20, 1.20",
    })
    void taxAmountIsRoundedToUpper5Hundredths(String price, String taxedPrice, String taxAmount) {
        Assertions.assertThat(
                new Basket()
                        .addItem("1 flacon de parfum à " + price)
                        .receipt()
        ).isEqualTo("1 flacon de parfum : " + taxedPrice + " Montant des taxes : " + taxAmount + " Total : " + taxedPrice);
    }
}
