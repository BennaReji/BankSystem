public class CertificateDeposit extends Account {

    public CertificateDeposit(double apr, int id, double initialBalance) {
        super(apr, id);
        if (initialBalance >= 0) {
            deposit(initialBalance);
        }
    }

}
