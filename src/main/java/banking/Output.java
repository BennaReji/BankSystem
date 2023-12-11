package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Output {

    public static ArrayList<String> getOpenAccounts(Bank bank) {
        ArrayList<String> openAccounts = new ArrayList<>();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);

        ArrayList<String> idInOrders;
        idInOrders = bank.getListOfAccountId();
        for (String idInOrder : idInOrders) {
            Account account = bank.getAccounts(idInOrder);
            double accountBalance = Double.parseDouble(decimalFormat.format(account.getBalance()));
            double APR = Double.parseDouble(decimalFormat.format(account.getAPRValue()));
            String formattedAccountInfo = account.getAccountType() + " " + account.getId() + " "
                    + decimalFormat.format(accountBalance) + " " + decimalFormat.format(APR);
            openAccounts.add(formattedAccountInfo);
        }
        return openAccounts;
    }

    public static ArrayList<String> makeLists(List<String> openAccounts, List<String> validCommands, List<String> invalidCommands) {
        ArrayList<String> finalOutput = new ArrayList<>();
        for (String openAccount : openAccounts) {
            String[] accountArguments = openAccount.split(" ");
            finalOutput.add(openAccount);
            for (String validCommand : validCommands) {
                String[] validCommandArguments = validCommand.split(" ");
                if (accountArguments[1].equals(validCommandArguments[1])
                        || accountArguments[1].equals(validCommandArguments[2])) {
                    finalOutput.add(validCommand);
                }
            }
        }
        finalOutput.addAll(invalidCommands);
        return finalOutput;
    }


    public static String makeCommandUpperCase(String command) {
        String[] parts = command.split(" ");

        if (parts.length > 0) {
            String[] recognizedActions = {"deposit", "withdraw", "transfer", "savings", "checking", "cd"};
            String firstPart = parts[0].toLowerCase();
            boolean isRecognizedAction = false;
            for (String action : recognizedActions) {
                if (firstPart.equals(action)) {
                    isRecognizedAction = true;
                    break;
                }
            }
            if (isRecognizedAction) {
                firstPart = firstPart.substring(0, 1).toUpperCase() + firstPart.substring(1);
            }
            StringBuilder capitalizedCommand = new StringBuilder(firstPart);
            for (int i = 1; i < parts.length; i++) {
                capitalizedCommand.append(" ").append(parts[i]);
            }

            return capitalizedCommand.toString();
        }
        return command;
    }
}
