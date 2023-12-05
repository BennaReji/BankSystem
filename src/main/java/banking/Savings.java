package banking;

public class Savings extends Account {
    public Savings(double apr, String id) {
        super(apr, id);
    }

    @Override
    public boolean canDeposit() {
        return true;
    }

    @Override
    public boolean canDepositAmount(double amount) {
        return amount <= 2500;
    }

    @Override
    public boolean withdrawRange(double amount) {
        return amount <= 1000;
    }
}