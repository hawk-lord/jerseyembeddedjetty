package ax.joint.bank.application;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAppTest {

    private final int duration = 24;
    private final BigDecimal interestRate = BigDecimal.valueOf(5);
    private final BigDecimal principalAmount = BigDecimal.valueOf(5000);
    private final LocalDate startDate = LocalDate.parse("2018-01-01");

    /**
     * SUT - System under test.
     */
    private final BankApp bankApp = new BankApp();

    @Test
    void calculate() {

        final List<Instalment> instalments = bankApp.calculate(duration, interestRate, principalAmount, startDate);
        assertNotNull(instalments);
        assertEquals(duration, instalments.size());
        final Instalment actualLastInstalment = instalments.get(duration - 1);
        assertEquals(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN),
                actualLastInstalment.getRemainingOutstandingPrincipal());
    }
}