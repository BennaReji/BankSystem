package banking;

import java.util.ArrayList;
import java.util.List;

public class PassTimeProcessor {
    private static final double MINIMUM_BALANCE_FEE = 25.0;
    private static final double MINIMUM_BALANCE_THRESHOLD = 100.0;
    private Bank bank;

    public PassTimeProcessor(Bank bank) {
        this.bank = bank;
    }

    public void passTime(int months) {
        List<Account> accounts = new ArrayList<>(bank.getAccounts().values());
        for (int i = 0; i < months; i++) {
            for (Account account : accounts) {
                handleMinimumBalanceFee(account);
                handleAccountClosure(account);
                calculateAPR(account, 1);
                account.addAge(1);

            }
            resetWithdrawalCountForSavings();


        }

    }

    private void handleMinimumBalanceFee(Account account) {
        if (account.getBalance() < MINIMUM_BALANCE_THRESHOLD) {
            account.withdraw(MINIMUM_BALANCE_FEE);
        }
    }

    private void handleAccountClosure(Account account) {
        if (account.getBalance() == 0) {
            bank.closeAccount(account.getId());
        }
    }

    private void calculateAPR(Account account, int months) {
        double monthlyAPR = account.getAPRValue() / 100 / 12;
        double balance = account.getBalance();

        for (int i = 0; i < months; i++) {
            double interest = account.calculateInterest(balance, monthlyAPR, account);
            balance += interest;

        }

        account.setBalance(balance);
    }

    private void resetWithdrawalCountForSavings() {
        for (Account account : bank.getAccounts().values()) {
            if (account.getAccountType().equals("savings")) {
                account.resetWithdrawalCount();
            }
        }
    }


}