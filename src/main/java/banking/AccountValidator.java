package banking;

public class AccountValidator {
    private Bank bank;

    public AccountValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 4) {
            return false;
        }

        String commandWord = parts[0].toLowerCase();

        if (!commandWord.equals("create")) {
            return false;
        }

        String accountType = parts[1].toLowerCase();
        String idNumber = parts[2];
        String apr = parts[3];

        if (!isValidAccountType(accountType)) {
            return false;
        }
        if (accountType.equals("cd") && parts.length < 5) {
            return false;
        }

        if (accountType.equals("cd") && !isValidInitialBalance(parts[4])) {
            return false;
        }

        if (!isValidAccountNumber(idNumber)) {
            return false;
        }
        if (bank.checkIdExists(idNumber)) {
            return false;
        }

        return isValidAPR(apr);
    }

    private boolean isValidAccountType(String accountType) {
        return accountType.equals("cd") || accountType.equals("checking") || accountType.equals("savings");
    }

    private boolean isValidAccountNumber(String idNumber) {
        return idNumber.matches("\\d{8}");
    }

    private boolean isValidAPR(String apr) {
        double numberApr = Double.parseDouble(apr);
        if (numberApr >= 1.0 && numberApr <= 10.0) {
            return true;
        }
        return false;
    }

    private boolean isValidInitialBalance(String initialBalance) {
        double numberInitialBalance = Double.parseDouble(initialBalance);
        if (numberInitialBalance > 1000 && numberInitialBalance <= 10000) {
            return true;
        }
        return false;
    }
}
