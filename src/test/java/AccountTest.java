import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    Account cd;

    @BeforeEach
    public void setUp() {
        cd = new CertificateDeposit(20, 3.5, 12345678);

    }

    @Test
    public void deposit_20_dollars() {
        cd.addMoney(10);
        double balance = cd.getBalance();
        assertEquals(30, balance);
    }

    @Test
    public void withdraw_20_dollars() {
        cd.takeMoney(10);
        double balance = cd.getBalance();
        assertEquals(10, balance);
    }

    @Test
    public void make_it_zero_balance_if_there_is_no_money_left() {
        cd.takeMoney(20);
        double balance = cd.getBalance();
        assertEquals(0, balance);
    }

    @Test
    public void APR_value_for_cd() {
        double aprValue = cd.getAPRValue();
        assertEquals(3.5, aprValue);
    }

    @Test
    public void unique_Id_for_cd() {
        int idNum = cd.getId();
        assertEquals(12345678, idNum);
    }

}
