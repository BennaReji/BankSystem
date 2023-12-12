package banking;

public class AccountValidator extends CommandValidator {
    private Bank bank;

    public AccountValidator(Bank bank) {
        this.bank = bank;
    }

    @Override
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
        if (bank.checkIdExists(idNumber)) {
            return false;
        }
        if (accountType.equals("cd") && parts.length < 5) {
            return false;
        }
        if ((accountType.equals("checking") || accountType.equals("savings")) && parts.length > 4) {
            return false;
        }

        if (accountType.equals("cd") && !isValidInitialBalance(parts[4])) {
            return false;
        }

        if (!isValidAccountNumber(idNumber)) {
            return false;
        } else {
            return isValidAPR(apr);
        }

    }


    private boolean isValidAPR(String apr) {
        try {
            double numberApr = Double.parseDouble(apr);
            return (numberApr > 0 && numberApr <= 10.0);
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private boolean isValidInitialBalance(String initialBalance) {
        try {
            double numberInitialBalance = Double.parseDouble(initialBalance);
            return (numberInitialBalance >= 1000 && numberInitialBalance <= 10000);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

