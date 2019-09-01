package ax.joint.bank.interfaces;


public class AccountingEntryDto {

    private String id;
    private String whenCharged;
    private String whenBooked;
    private String amount;

    public AccountingEntryDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getWhenCharged() {
        return whenCharged;
    }

    public void setWhenCharged(final String whenCharged) {
        this.whenCharged = whenCharged;
    }

    public String getWhenBooked() {
        return whenBooked;
    }

    public void setWhenBooked(final String whenBooked) {
        this.whenBooked = whenBooked;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

}
