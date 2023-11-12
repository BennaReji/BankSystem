public class CommandProcessor {
    private Bank bank;

    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0];

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
        String accountId = parts[2];
        double apr = Double.parseDouble(parts[3]);

        Account account;
        if ("12345678".equals(accountId)) {
            account = new Checking(apr, accountId);
        } else if ("12345679".equals(accountId)) {
            account = new Savings(apr, accountId);
        } else {
            return;
        }

        bank.addAccount(account);
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
