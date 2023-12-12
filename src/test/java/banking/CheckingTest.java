package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingTest {
    Checking checking;

    @BeforeEach
    public void setUp() {
        checking = new Checking("12345679", 2);
    }

    @Test
    void get_correct_account_type() {
        assertEquals("Checking", checking.getAccountType());
    }

    @Test
    void check_if_get_withdrawal_account_is_2() {
        int getWithdrawal = checking.getWithdrawalCount();
        assertEquals(2, getWithdrawal);
    }


}
