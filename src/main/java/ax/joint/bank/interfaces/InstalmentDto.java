package ax.joint.bank.interfaces;

import ax.joint.bank.application.Instalment;

class InstalmentDto {

    private final String borrowerPaymentAmount;
    private final String date;
    private final String initialOutstandingPrincipal;
    private final String interest;
    private final String principal;
    private final String remainingOutstandingPrincipal;

    InstalmentDto(final Instalment instalment) {
        this.borrowerPaymentAmount = instalment.getBorrowerPaymentAmount().toString();
        this.date = instalment.getDate().toString();
        this.initialOutstandingPrincipal = instalment.getInitialOutstandingPrincipal().toString();
        this.interest = instalment.getInterest().toString();
        this.principal = instalment.getPrincipal().toString();
        this.remainingOutstandingPrincipal = instalment.getRemainingOutstandingPrincipal().toString();
    }

    public String getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public String getDate() {
        return date;
    }

    public String getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public String getInterest() {
        return interest;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }



}
