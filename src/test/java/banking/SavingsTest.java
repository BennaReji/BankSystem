package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsTest {
    Savings savings;

    @BeforeEach
    public void setUp() {
        savings = new Savings("12345679", 2);
    }


    @Test
    public void test_can_deposit_amount_within_limit() {
        assertTrue(savings.canDepositAmount(2000));
    }

    @Test
    public void test_can_deposit_amount_over_limit() {
        assertFalse(savings.canDepositAmount(3000));
    }

    @Test
    public void test_deposit_within_the_max_limit() {
        assertTrue(savings.canDepositAmount(2500));
    }

    @Test
    public void test_withdraw_over_limit_amount() {
        assertFalse(savings.withdrawRange(1500));
    }

    @Test
    public void test_multiple_withdrawals() {
        assertTrue(savings.withdrawRange(500));
        assertFalse(savings.withdrawRange(500));
    }

    @Test
    public void test_withdraw_limit_amount() {
        savings.withdrawRange(1000);
        assertEquals(1, savings.getWithdrawalCount());
    }

    @Test
    public void test_withdraw_max_limit_amount() {
        assertTrue(savings.withdrawRange(1000));


    }


}


