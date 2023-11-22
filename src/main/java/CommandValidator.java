public class CommandValidator {
    private AccountValidator accountValidator;
    private DepositValidator depositValidator;


    public CommandValidator() {
        accountValidator = new AccountValidator();
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