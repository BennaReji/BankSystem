package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CertificateDepositTest {
    CertificateDeposit cd;

    @BeforeEach
    public void setUp() {
        cd = new CertificateDeposit("12345678", 2.0, 1000);
    }

    @Test
    public void cd_created_with_specified_amount_of_balance() {
        double balance = cd.getBalance();
        assertEquals(1000, balance);
    }

    @Test
    public void withdraw_within_balance() {
        boolean canWithdraw = cd.withdrawRange(500);
        assertTrue(canWithdraw);
    }

    @Test
    public void withdraw_beyond_balance() {
        boolean canWithdraw = cd.withdrawRange(1500);
        assertFalse(canWithdraw);
    }

    @Test
    public void calculate_interest() {
        double interest = cd.calculateInterest(cd.getBalance(), 0.02, cd);
        assertEquals(80, interest);
    }

    @Test
    public void verify_account_type() {
        String accountType = cd.getAccountType();
        assertEquals("Cd", accountType);
    }

    @Test
    void cannot_deposit() {
        assertFalse(cd.canDeposit());
    }

    @Test
    void cannot_deposit_amount() {
        assertFalse(cd.canDepositAmount(500));
    }

    @Test
    void check_if_get_withdrawal_account_is_2() {
        int getWithdrawal = cd.getWithdrawalCount();
        assertEquals(2, getWithdrawal);
    }

    @Test
    void withdraw_amount_equal_to_balance() {
        CertificateDeposit cd = new CertificateDeposit("12345678", 2.0, 1000);
        assertTrue(cd.withdrawRange(1000));
    }

    @Test
    void withdraw_amount_greater_than_balance() {
        CertificateDeposit cd = new CertificateDeposit("12345678", 2.0, 1500);
        assertFalse(cd.withdrawRange(2000));
    }


}

