package banking;

public class DepositValidator extends CommandValidator {
    private Bank bank;

    public DepositValidator(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length != 3) {
            return false;
        }

        String commandWord = parts[0].toLowerCase();

        if (!commandWord.equals("deposit")) {
            return false;
        }


        String idNumber = parts[1];
        String depositAmount = parts[2];

        if (!isValidAccountNumber(idNumber)) {
            return false;
        }

        if (!bank.checkIdExists(idNumber)) {
            return false;
        }

        Account account = bank.getAccounts().get(idNumber);

        if (account.canDeposit()) {
            double amount = Double.parseDouble(depositAmount);

            if (amount < 0) {
                return false; // Cannot deposit a negative amount
            }

            if (!account.canDepositAmount(amount)) {
                return false; // Exceeds maximum deposit for the account
            }

            return true; // Valid deposit command
        }

        return false; // Cannot deposit into a CD account
    }


    private boolean isValidAccountNumber(String idNumber) {
        return idNumber.matches("\\d{8}");
    }
}

