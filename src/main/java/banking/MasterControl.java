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
            } else {
                storeCommands.addInvalidCommand(command);
            }
        }
        return storeCommands.getAllInvalidCommands();
    }

}
