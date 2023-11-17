package banking;

public class DepositValidator {
    private Bank bank;

    public DepositValidator(Bank bank) {
        this.bank = bank;
    }


    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 3) {
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

        if (bank.checkIdExists(idNumber)) {
            return false;
        }

        return isValidDepositAmount(depositAmount);
    }

    private boolean isValidDepositAmount(String depositAmount) {
        double numberAmount = Double.parseDouble(depositAmount);
        if (numberAmount > 0 && numberAmount <= 3000) {
            return true;
        }
        return false;
    }

    private boolean isValidAccountNumber(String idNumber) {
        return idNumber.matches("\\d{8}");
    }
}
