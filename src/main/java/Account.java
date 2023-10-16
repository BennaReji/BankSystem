public abstract class Account {
    private double balance;
    private double apr;
    private int id;

    public Account(double apr, int id) {
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

    public int getId() {
        return id;

    }
}
