package banking;

import java.util.HashMap;

public class CommandValidator {
    HashMap<String, CommandValidator> commandValidatorMap;
    private Bank bank;

    public CommandValidator(Bank bank) {
        this.bank = bank;
        commandValidatorMap = new HashMap<String, CommandValidator>();
        commandValidatorMap.put("create", new AccountValidator(bank));
        commandValidatorMap.put("deposit", new DepositValidator(bank));
        commandValidatorMap.put("withdraw", new WithdrawValidator(bank));
    }

    public CommandValidator() {
    }


    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 1) {
            return false;
        }
        String commandWord = parts[0].toLowerCase();

        if (commandValidatorMap.containsKey(commandWord)) {

            CommandValidator validator = commandValidatorMap.get(commandWord);
            return validator.validate(command);

        } else {
            return false;
        }
    }

    public boolean isValidAccountType(String accountType) {
        return accountType.equals("cd") || accountType.equals("checking") || accountType.equals("savings");
    }

    public boolean isValidAccountNumber(String idNumber) {
        return idNumber.matches("\\d{8}");
    }


}