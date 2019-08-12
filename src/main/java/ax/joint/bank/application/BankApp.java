package ax.joint.bank.application;

import ax.joint.bank.model.Account;
import ax.joint.bank.model.AccountingEntry;

import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BankApp {

    private final List<AccountingEntry> accountingEntryList = new ArrayList<>();

    private final List<Account> accounts = new ArrayList<>();

    /**
     * Add some accounts so adding transactions can be done from the start.
     */
    public BankApp() {
        final Account bankAccount = new Account(1, "Bank Account");
        accounts.add(bankAccount);
        final Account creditCardAccount = new Account(2, "Credit Card Account");
        accounts.add(creditCardAccount);
        final Account loanAccount = new Account(3, "Loan Card Account");
        accounts.add(loanAccount);
    }

    /**
     * To keep it simple, two-legged transactions are used.
     * Multi-legged transactions could be implemented by a Transaction class with a list of AccountingEntry.
     *  @param whenCharged
     * @param whenBooked
     * @param amount
     * @param creditAccountId
     * @param debitAccountId
     * @return
     */
    public int addTransaction(final LocalDate whenCharged,
                              final LocalDate whenBooked,
                              final MonetaryAmount amount,
                              final int creditAccountId,
                              final int debitAccountId) {


        final AccountingEntry maxEntry = accountingEntryList.stream()
                .max(Comparator.comparing(AccountingEntry::getId))
                .orElse(null);
        final int newId = maxEntry != null ? maxEntry.getId() + 1 : 1;

        final Account creditAccount = findAccount(creditAccountId);
        final Account debitAccount = findAccount(debitAccountId);
        final AccountingEntry accountingEntry =
                new AccountingEntry(newId,
                        whenCharged,
                        whenBooked,
                        amount,
                        creditAccount,
                        debitAccount);
        accountingEntryList.add(accountingEntry);
        return newId;
    }

    public AccountingEntry findTransaction(final int id) throws AccountingEntryNotFoundException {
        if (accountingEntryList == null) {
            throw new AccountingEntryNotFoundException(String.valueOf(id));
        }
        if (accountingEntryList.size() == 0) {
            throw new AccountingEntryNotFoundException(String.valueOf(id));
        }
        for (AccountingEntry accountingEntry: accountingEntryList) {
            if (accountingEntry.getId() == id) {
                return accountingEntry;
            }
        }
        throw new AccountingEntryNotFoundException(String.valueOf(id));
    }

    private Account findAccount(final int accountId) {
        for (Account account: accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        throw new BankAccountNotFoundException(String.format("Account id %d was not found", accountId));
    }
}
