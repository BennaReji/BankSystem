package banking;

public class Checking extends Account {
    public Checking(double apr, String id) {
        super(apr, id);
    }

    @Override
    public boolean canDeposit() {
        return true;
    }

    @Override
    public boolean canDepositAmount(double amount) {
        return amount <= 1000;
    }

    @Override
    public boolean withdrawRange(double amount) {
        return amount <= 400;
    }
}
