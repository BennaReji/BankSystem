import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    public static final double DEPOSIT = 20;
    public static final double WITHDRAW = 10;
    public static final int ID = 12345678;
    public static final double APR = 3.5;
    Account cd;

    @BeforeEach
    public void setUp() {
        cd = new CertificateDeposit(APR, ID, 50);

    }

    @Test
    public void deposit_dollars_to_cd() {
        cd.deposit(DEPOSIT);
        double balance = cd.getBalance();
        assertEquals(70, balance);
    }

    @Test
    public void deposit_dollars_twice_to_cd() {
        cd.deposit(DEPOSIT);
        cd.deposit(DEPOSIT);
        double balance = cd.getBalance();
        assertEquals(90, balance);
    }

    @Test

    public void withdraw_from_cd() {
        cd.withdraw(WITHDRAW);
        double balance = cd.getBalance();
        assertEquals(40, balance);
    }

    @Test
    public void withdraw_twice_from_cd() {
        cd.withdraw(WITHDRAW);
        cd.withdraw(WITHDRAW);
        double balance = cd.getBalance();
        assertEquals(30, balance);
    }

    @Test
    public void make_it_zero_balance_if_there_is_no_money_left() {
        cd.withdraw(60);
        double balance = cd.getBalance();
        assertEquals(0, balance);
    }

    @Test
    public void apr_value() {
        double aprValue = cd.getAPRValue();
        assertEquals(APR, aprValue);
    }

    @Test
    public void unique_id() {
        int idNum = cd.getId();
        assertEquals(ID, idNum);
    }

}
