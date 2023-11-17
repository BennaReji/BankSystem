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
            default:
                break;
        }
    }

    private void processCreateCommand(String[] parts) {
        String accountType = parts[1].toLowerCase();
        String accountId = parts[2];

        double apr = Double.parseDouble(parts[3]);


        switch (accountType) {
            case "checking":
                bank.addAccount(new Checking(apr, accountId));
                break;
            case "savings":
                bank.addAccount(new Savings(apr, accountId));
                break;

            // Handle other account types if needed
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
}

