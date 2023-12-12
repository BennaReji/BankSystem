package banking;

public abstract class Account {


    private double balance;
    private double apr;
    private String id;
    private int age;

    public Account(String id, double apr) {
        this.balance = 0;
        this.apr = apr;
        this.id = id;
        age = 0;
    }


    public double getBalance() {
        return balance;

    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public void deposit(double money) {
        balance += money;
    }

    public void withdraw(double money) {
        if (balance > 0) {
            balance -= money;
            if (balance < 0) {
                balance = 0;
            }
        }

    }

    public double getAPRValue() {
        return apr;
    }

    public String getId() {
        return id;

    }

    public int getAge() {
        return age;
    }

    public void addAge(int months) {
        age += months;
    }

    public String getAccountType() {
        return "account";
    }

    public abstract boolean canDeposit();

    public abstract boolean canDepositAmount(double amount);

    public abstract boolean withdrawRange(double amount);

    public void resetWithdrawalCount() {

    }


    public abstract double calculateInterest(double balance, double monthlyAPR, Account account);

    public abstract int getWithdrawalCount();
}
