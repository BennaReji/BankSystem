package banking;

public class Checking extends Account {
    private int withdrawalCount;

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

    @Override
    public double calculateInterest(double balance, double monthlyAPR, Account account) {
        return balance * monthlyAPR;
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }

    @Override
    public int getWithdrawalCount() {
        withdrawalCount = 2;
        return withdrawalCount;
    }


}


