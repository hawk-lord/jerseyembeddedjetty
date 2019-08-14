package ax.joint.bank.model;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

/**
 * An immutable object.
 *
 * If for instance the credit card balance is paid from the bank account, the bank account is credited and
 * the credit card account is debited.
 * If a salary is paid to the bank account, the salary income account is credited and bank account is debited.
 *
 */
public class AccountingEntry implements Comparable {

    private final int id;
    private final LocalDate whenCharged;
    private final LocalDate whenBooked;
    private final MonetaryAmount amount;
    private final Account creditAccount;
    private final Account debitAccount;


    /**
     *
     * @param id unique
     * @param whenCharged when transaction was done
     * @param whenBooked when transaction was booked to the account
     * @param amount the amount, normally positive
     * @param creditAccount bookkeeping account
     * @param debitAccount bookkeeping account
     */
    public AccountingEntry(final int id, final LocalDate whenCharged, final LocalDate whenBooked,
                           final MonetaryAmount amount,
                           final Account creditAccount, final Account debitAccount) {
        this.id = id;
        this.whenCharged = whenCharged;
        this.whenBooked = whenBooked;
        this.amount = amount;
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
    }


    public int getId() {
        return id;
    }

    /**
     * When the transaction was paid.
     * @return the charged date
     */
    public LocalDate getWhenCharged() {
        return whenCharged;
    }

    /**
     * When the transaction was booked to the account.
     * Equal to or later than whenCharged.
     * @return the booked date
     */
    public LocalDate getWhenBooked() {
        return whenBooked;
    }

    /**
     *
     * @return
     */
    public MonetaryAmount getAmount() {
        return amount;
    }

    /**
     *
     * @return
     */
    public Account getCreditAccount() {
        return creditAccount;
    }

    /**
     *
     * @return
     */
    public Account getDebitAccount() {
        return debitAccount;
    }

    @Override
    public int compareTo(final Object o) {
        if (o instanceof AccountingEntry) {
            return this.getId() - ((AccountingEntry) o).getId();
        }
        return 0;
    }
}
