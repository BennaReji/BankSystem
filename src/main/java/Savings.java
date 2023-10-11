public class Savings extends Account {
    public Savings(double balance) {
        super(balance);
    }

    public Savings() {
        super(0);
    }

    @Override
    public double getAPRValue() {
        return 4;
    }

    @Override
    public int getId() {
        return 12345699;
    }
}