package banking;

import java.util.ArrayList;
import java.util.List;

public class StoreCommands {
    private List<String> invalidCommands;
    private List<String> validCommands;
    private List<String> openAccounts;

    public StoreCommands() {
        this.invalidCommands = new ArrayList<>();
        this.validCommands = new ArrayList<>();
        this.openAccounts = new ArrayList<>();
    }


    public void addInvalidCommand(String command) {
        String UpperCaseCommand = Output.makeCommandUpperCase(command);
        invalidCommands.add(UpperCaseCommand);

    }

    public void addValidCommand(String command) {
        String UpperCaseCommand = Output.makeCommandUpperCase(command);
        validCommands.add(UpperCaseCommand);

    }

    public List<String> getAllInvalidCommands() {
        return invalidCommands;
    }

    public void addOpenAccount(Bank bank) {
        openAccounts = Output.getOpenAccounts(bank);
    }

    public ArrayList<String> getOutputString() {
        return Output.makeLists(openAccounts, validCommands, invalidCommands);
    }


}
