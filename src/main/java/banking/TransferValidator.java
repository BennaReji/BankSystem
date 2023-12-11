package banking;

public class TransferValidator extends CommandValidator {
    private Bank bank;

    public TransferValidator(Bank bank) {
        this.bank = bank;
    }

    @Override

    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length != 4) {
            return false;
        }

        String commandWord = parts[0].toLowerCase();
        if (!commandWord.equals("transfer")) {
            return false;
        }

        String fromAccountId = parts[1];
        String toAccountId = parts[2];
        double transferAmount = Double.parseDouble(parts[3]);

        if (!isValidAccountNumber(fromAccountId) || !isValidAccountNumber(toAccountId)) {
            return false;
        }

        Account fromAccount = bank.getAccounts().get(fromAccountId);
        Account toAccount = bank.getAccounts().get(toAccountId);

        if (fromAccount == null || toAccount == null) {
            return false;
        }
        if (fromAccount == toAccount) {
            return false;
        }

        if (fromAccount.getClass() == CertificateDeposit.class || toAccount.getClass() == CertificateDeposit.class) {
            return false;
        }

        if (isCheckingAccount(fromAccount) && isCheckingAccount(toAccount)) {
            return transferWithinAccounts(fromAccountId, toAccountId, transferAmount);
        } else if (isSavingsAccount(fromAccount) && isSavingsAccount(toAccount)) {
            return transferWithinAccounts(fromAccountId, toAccountId, transferAmount);
        } else if (isCheckingAccount(fromAccount) && isSavingsAccount(toAccount)) {
            return transferWithinAccounts(fromAccountId, toAccountId, transferAmount);
        } else if (isSavingsAccount(fromAccount) && isCheckingAccount(toAccount)) {
            return transferWithinAccounts(fromAccountId, toAccountId, transferAmount);
        }

        return false;
    }

    private boolean isCheckingAccount(Account account) {
        return account.getClass() == Checking.class;
    }

    private boolean isSavingsAccount(Account account) {
        return account.getClass() == Savings.class;
    }

    private boolean transferWithinAccounts(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = bank.getAccounts().get(fromAccountId);
        Account toAccount = bank.getAccounts().get(toAccountId);
        double actualTransferAmount = Math.min(fromAccount.getBalance(), amount);

        if (actualTransferAmount <= 0) {
            return false;
        }

        if (fromAccount.withdrawRange(actualTransferAmount)) {
            fromAccount.withdraw(actualTransferAmount);
            toAccount.deposit(actualTransferAmount);
            return true;
        }

        return false;
    }


}