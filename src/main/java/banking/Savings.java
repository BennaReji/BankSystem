package banking;

public class Savings extends Account {
    public Savings(String id, double apr) {
        super(id, apr);
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