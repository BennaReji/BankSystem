public class CommandValidator {
    private final Bank bank;
    private AccountValidator accountValidator;
    private DepositValidator depositValidator;


    public CommandValidator(Bank bank) {
        this.bank = bank;
        accountValidator = new AccountValidator(bank);
        depositValidator = new DepositValidator();

    }

    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 1) {
            return false;
        }

        String commandWord = parts[0].toLowerCase();

        if (commandWord.equals("create")) {
            return accountValidator.validate(command);
        } else if (commandWord.equals("deposit")) {
            return depositValidator.validate(command);
        } else {
            return false;
        }
    }
}