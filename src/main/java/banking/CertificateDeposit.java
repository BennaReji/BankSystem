package banking;

public class CertificateDeposit extends Account {
    private int withdrawalCount;


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

    @Override
    public double calculateInterest(double balance, double monthlyAPR, Account account) {
        double totalInterest = 0;
        for (int i = 0; i < 4; i++) {
            totalInterest += balance * monthlyAPR;
        }
        return totalInterest;
    }

    @Override
    public int getWithdrawalCount() {
        withdrawalCount = 2;
        return withdrawalCount;
    }

    @Override
    public String getAccountType() {
        return "Cd";
    }


}
