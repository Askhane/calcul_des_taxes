package taxcalculation;

import java.math.BigDecimal;

public enum TaxRule {


    STANDARD(new BigDecimal("0.1"), BigDecimal.ZERO),
    EXEMPTED(BigDecimal.ZERO, BigDecimal.ZERO),
    IMPORTED(STANDARD.rate, new BigDecimal("0.05")),
    EXEMPTED_IMPORTED(EXEMPTED.rate, IMPORTED.importedRate);

    public final BigDecimal rate;
    public final BigDecimal importedRate;

    TaxRule(BigDecimal rate, BigDecimal importedRate) {
        this.rate = rate;
        this.importedRate = importedRate;
    }

    public static TaxRule get(boolean isImported, boolean isExempted) {
        if (isImported && isExempted) return EXEMPTED_IMPORTED;
        if (isImported) return IMPORTED;
        if (isExempted) return EXEMPTED;
        return STANDARD;
    }
}
