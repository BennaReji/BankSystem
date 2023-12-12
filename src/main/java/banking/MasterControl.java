package banking;

import java.util.List;

public class MasterControl {
    private CommandValidator commandValidator;
    private CommandProcessor commandProcessor;
    private StoreCommands storeCommands;

    public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor, StoreCommands storeCommands) {
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.storeCommands = storeCommands;
    }

    public List<String> start(List<String> input) {
        for (String command : input) {
            if (commandValidator.validate(command)) {
                commandProcessor.processCommand(command);
                String[] listOfCommandParts = command.split(" ");
                if (!(listOfCommandParts[0].equalsIgnoreCase("create") || listOfCommandParts[0].equalsIgnoreCase("pass"))) {
                    storeCommands.addValidCommand(command);
                }

            } else {
                storeCommands.addInvalidCommand(command);
            }
        }
        Bank bank = commandProcessor.getBank();
        storeCommands.addOpenAccount(bank);
        return storeCommands.getOutputString();
    }

}
