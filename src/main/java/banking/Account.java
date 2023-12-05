package banking;

public abstract class Account {
    private double balance;
    private double apr;
    private String id;

    public Account(double apr, String id) {
        this.balance = 0;
        this.apr = apr;
        this.id = id;
    }


    public double getBalance() {
        return balance;

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

    public abstract boolean canDeposit();

    public abstract boolean canDepositAmount(double amount);

    public abstract boolean withdrawRange(double amount);


}
