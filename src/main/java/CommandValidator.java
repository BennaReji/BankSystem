public class CommandValidator {
    private AccountValidator accountValidator;


    public CommandValidator() {
        accountValidator = new AccountValidator();
    }

    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 1) {
            return false;
        }

        String commandWord = parts[0].toLowerCase();

        if (commandWord.equals("create")) {
            return accountValidator.validate(command);
        } else {
            return false;
        }
    }
}