public abstract class Account {
    private double balance;
    private double apr;
    private int id;

    public Account(double balance, double apr, int id) {
        this.balance = balance;
        this.apr = apr;
        this.id = id;
    }


    public double getBalance() {
        return balance;

    }

    public void addMoney(double money) {
        balance += money;
    }

    public void takeMoney(double money) {
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
