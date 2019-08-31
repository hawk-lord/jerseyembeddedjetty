package ax.joint.bank.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankApp {

    private static final int DAYS_IN_MONTH = 30;
    private static final int DAYS_IN_YEAR = 360;
    private static final int PER_CENT = 100;


    public List<Instalment> calculate(final int duration, final BigDecimal interestRate,
                                      final BigDecimal principalAmount, final LocalDate startDate) {

        // Using double instead of BigDecimal to simplify the code.
        double interestRateMonth = interestRate.doubleValue();
        interestRateMonth /= PER_CENT;
        interestRateMonth *= DAYS_IN_MONTH;
        interestRateMonth /= DAYS_IN_YEAR;

        final BigDecimal interestRateMonthBD = BigDecimal.valueOf(interestRateMonth);

        final double interestDouble = interestRateMonth * principalAmount.doubleValue();
        final double annuityDouble = interestDouble / (1 - Math.pow((1 + interestRateMonth), -duration));

        BigDecimal annuityBD = BigDecimal.valueOf(annuityDouble).setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal initialOutstandingPrincipalBD = principalAmount;

        final List<Instalment> instalments = new ArrayList<>();

        for (int i = 0; i < duration; i++) {

            final LocalDate date = startDate.plusMonths(i);

            final BigDecimal interestBD = initialOutstandingPrincipalBD
                    .multiply(interestRateMonthBD).setScale(2, RoundingMode.HALF_EVEN);
            if (i + 1 == duration) {
                annuityBD = initialOutstandingPrincipalBD.add(interestBD);
            }
            final BigDecimal principalBD = annuityBD.subtract(interestBD);
            final BigDecimal remainingOutstandingPrincipalBD = initialOutstandingPrincipalBD.subtract(principalBD);
            final Instalment instalment = new Instalment(annuityBD, date, initialOutstandingPrincipalBD, interestBD,
                    principalBD, remainingOutstandingPrincipalBD);
            instalments.add(instalment);

            initialOutstandingPrincipalBD = remainingOutstandingPrincipalBD;

        }

        return instalments;
    }

}
