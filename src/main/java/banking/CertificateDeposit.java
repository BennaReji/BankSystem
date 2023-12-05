package banking;

public class CertificateDeposit extends Account {

    public CertificateDeposit(double apr, String id, double initialBalance) {
        super(apr, id);
        if (initialBalance >= 1000) {
            deposit(initialBalance);
        }
    }

    @Override
    public boolean canDeposit() {
        return false;
    }

    @Override
    public boolean canDepositAmount(double amount) {
        return false;
    }

    @Override
    public boolean withdrawRange(double amount) {
        if (amount <= getBalance()) {
            return true;
        }
        return false;
    }
}
