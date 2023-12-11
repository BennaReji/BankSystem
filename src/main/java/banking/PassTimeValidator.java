package banking;

public class PassTimeValidator extends CommandValidator {
    private static final int MIN_MONTHS = 1;
    private static final int MAX_MONTHS = 60;


    @Override
    public boolean validate(String command) {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            return false;
        }

        String commandWord = parts[0].toLowerCase();
        if (!commandWord.equals("pass")) {
            return false;
        }

        int months;
        try {
            months = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        return validMonthRange(months);
    }

    private boolean validMonthRange(int months) {
        return months >= MIN_MONTHS && months <= MAX_MONTHS;
    }
}

