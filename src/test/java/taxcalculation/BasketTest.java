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
            "13.0, 14.30, 1.30",
            "13.01, 14.36, 1.35",
            "13.10, 14.45, 1.35",
            "13.16, 14.51, 1.35",
            "10.00, 11.00, 1.00",
            "15.00, 16.50, 1.50"
    })
    void taxAmountIsRoundedToUpper5Hundredths(String price, String taxedPrice, String taxAmount) {
        Assertions.assertThat(
                new Basket()
                        .addItem("1 flacon de parfum à " + price)
                        .receipt()
        ).isEqualTo("1 flacon de parfum : " + taxedPrice + " Montant des taxes : " + taxAmount + " Total : " + taxedPrice);
    }

    @Test
    void given2TaxedItemsThenTaxAmountIsTheSumOfItemsTaxAndTotalIsTheSumOfItemsPrice() {
        assertThat(
                new Basket()
                        .addItem("1 CD musical à 14.99")
                        .addItem("1 flacon de parfum à 18.99")
                        .receipt()
        ).isEqualTo("1 CD musical : 16.49 1 flacon de parfum : 20.89 Montant des taxes : 3.40 Total : 37.38");
    }

    @Test
    void foodIsExemptedAndTotalIsFoodPrice() {
        Assertions.assertThat(
                new Basket()
                        .addItem("1 barre de chocolat à 0.85")
                        .receipt()
        ).isEqualTo("1 barre de chocolat : 0.85 Montant des taxes : 0.00 Total : 0.85");
    }

    @Test
    void medicineIsNotTaxedAndTotalIsMedicinePrice() {
        Assertions.assertThat(
                new Basket()
                        .addItem("1 boîte de pilules contre la migraine à 9.75")
                        .receipt()
        ).isEqualTo("1 boîte de pilules contre la migraine : 9.75 Montant des taxes : 0.00 Total : 9.75");
    }

    @Test
    void bookIsNotTaxedAndTotalIsBookPrice() {
        Assertions.assertThat(
                new Basket()
                        .addItem("1 livre à 12.49")
                        .receipt()
        ).isEqualTo("1 livre : 12.49 Montant des taxes : 0.00 Total : 12.49");
    }

    @Test
    void basketMixingTaxedAndExemptedItemsGivesAppropriateTaxesAndTotal() {
        Assertions.assertThat(
                new Basket()
                        .addItem("1 livre à 12.49")
                        .addItem("1 CD musical à 14.99")
                        .addItem("1 barre de chocolat à 0.85")
                        .receipt()
        ).isEqualTo("1 livre : 12.49 1 CD musical : 16.49 1 barre de chocolat : 0.85 " +
                "Montant des taxes : 1.50 Total : 29.83");
    }

}
