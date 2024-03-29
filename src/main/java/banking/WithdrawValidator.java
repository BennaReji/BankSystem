package banking;

public class WithdrawValidator extends CommandValidator {
    private Bank bank;

    public WithdrawValidator(Bank bank) {
        this.bank = bank;


    }

    @Override
    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length != 3 || !parts[0].equalsIgnoreCase("withdraw")) {
            return false;
        }

        String idNumber = parts[1];
        String withdrawAmount = parts[2];

        if (!isValidAccountNumber(idNumber) || !bank.checkIdExists(idNumber)) {
            return false;
        }


        Account account = bank.getAccounts().get(idNumber);

        if (account.getAccountType().toLowerCase().equals("cd")) {
            if (account.getAge() < 12) {
                return false;
            }
        }
        double amount = Double.parseDouble(withdrawAmount);

        if (amount < 0) {
            return false;
        }

        return account.withdrawRange(amount);
    }


}

