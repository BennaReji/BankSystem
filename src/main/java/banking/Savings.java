package banking;

public class Savings extends Account {
    private int withdrawalCount; // Add a counter for withdrawals


    public Savings(String id, double apr) {
        super(id, apr);
        this.withdrawalCount = 0; // Initialize the counter


    }


    @Override
    public void resetWithdrawalCount() {
        withdrawalCount = 0;
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
        if (withdrawalCount < 1 && amount <= 1000) {
            withdrawalCount++;
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest(double balance, double monthlyAPR, Account account) {
        return balance * monthlyAPR;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }


}