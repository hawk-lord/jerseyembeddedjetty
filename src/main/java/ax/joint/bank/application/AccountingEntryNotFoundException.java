package ax.joint.bank.application;

public class AccountingEntryNotFoundException extends RuntimeException {

    public AccountingEntryNotFoundException(final String message) {
        super(message);
    }

}
