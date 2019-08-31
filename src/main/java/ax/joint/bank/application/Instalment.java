package ax.joint.bank.application;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Instalment {

    private final BigDecimal borrowerPaymentAmount;
    private final LocalDate date;
    private final BigDecimal initialOutstandingPrincipal;
    private final BigDecimal interest;
    private final BigDecimal principal;
    private final BigDecimal remainingOutstandingPrincipal;

    Instalment(final BigDecimal borrowerPaymentAmount, final LocalDate date, final BigDecimal initialOutstandingPrincipal,
               final BigDecimal interest, final BigDecimal principal, final BigDecimal remainingOutstandingPrincipal) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        this.date = date;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.interest = interest;
        this.principal = principal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    public BigDecimal getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public BigDecimal getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }



}
