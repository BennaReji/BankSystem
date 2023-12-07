package banking;

public class CertificateDeposit extends Account {

    public CertificateDeposit(String id, double apr, double initialBalance) {
        super(id, apr);
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
