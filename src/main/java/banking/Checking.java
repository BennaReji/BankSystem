package banking;

public class Checking extends Account {
    public Checking(String id, double apr) {
        super(id, apr);
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
