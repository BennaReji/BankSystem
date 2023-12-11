package banking;

public class CommandProcessor {
    private Bank bank;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0].toLowerCase();

        switch (action) {
            case "create":
                processCreateCommand(parts);
                break;
            case "deposit":
                processDepositCommand(parts);
                break;
            case "withdraw":
                processWithdrawCommand(parts);
                break;
            case "transfer":
                processTransferCommand(parts);
                break;
            case "pass":
                passTimeProcessor(parts);
                break;

        }

    }


    private void processCreateCommand(String[] parts) {
        String accountType = parts[1].toLowerCase();
        String accountId = parts[2];

        double apr = Double.parseDouble(parts[3]);


        switch (accountType) {
            case "checking":
                bank.addAccount(new Checking(accountId, apr));
                break;
            case "savings":
                bank.addAccount(new Savings(accountId, apr));
                break;
            case "cd":
                if (parts.length >= 5) {
                    double initialBalance = Double.parseDouble(parts[4]);
                    bank.addAccount(new CertificateDeposit(accountId, apr, initialBalance));
                } else {
                    throw new IllegalArgumentException("Missing initial balance for CD account creation");
                }
                break;


        }
    }

    private void processDepositCommand(String[] parts) {
        String accountId = parts[1];
        double amount = Double.parseDouble(parts[2]);

        Account account = bank.getAccounts().get(accountId);
        if (account != null) {
            account.deposit(amount);
        }
    }

    private void processWithdrawCommand(String[] parts) {
        String accountId = parts[1];
        double amount = Double.parseDouble(parts[2]);

        Account account = bank.getAccounts().get(accountId);
        if (account != null) {
            account.withdraw(amount);
        }
    }

    private void processTransferCommand(String[] parts) {
        String fromAccountId = parts[1];
        String toAccountId = parts[2];
        double amount = Double.parseDouble(parts[3]);

        Account fromAccount = bank.getAccounts().get(fromAccountId);
        Account toAccount = bank.getAccounts().get(toAccountId);

        double actualTransferAmount = Math.min(fromAccount.getBalance(), amount);

        if (fromAccount.withdrawRange(actualTransferAmount)) {
            fromAccount.withdraw(actualTransferAmount);
            toAccount.deposit(actualTransferAmount);
        }

    }

    private void passTimeProcessor(String[] parts) {
        int months = Integer.parseInt(parts[1]);

        PassTimeProcessor passTimeProcessor = new PassTimeProcessor(bank);
        passTimeProcessor.passTime(months);

    }


    public Bank getBank() {
        return bank;
    }
}


