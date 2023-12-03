package banking;

public class CertificateDeposit extends Account {

    public CertificateDeposit(double apr, String id, double initialBalance) {
        super(apr, id);
        if (initialBalance >= 1000) {
            deposit(initialBalance);
        }
    }

}
