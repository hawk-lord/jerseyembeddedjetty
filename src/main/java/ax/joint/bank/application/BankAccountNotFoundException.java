package ax.joint.bank.application;

public class BankAccountNotFoundException extends RuntimeException {

    public BankAccountNotFoundException(final String message) {
        super(message);
    }


}
