package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawValidatorTest {
    WithdrawValidator withdrawValidator;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        Checking checking = new Checking("12345678", 2.0);
        bank.addAccount(checking);
        Savings savings = new Savings("12345679", 2.0);
        bank.addAccount(savings);
        CertificateDeposit cd = new CertificateDeposit("12345687", 2.0, 1222);
        bank.addAccount(cd);
        withdrawValidator = new WithdrawValidator(bank);

    }

    @Test
    void valid_withdraw_from_checking_account() {
        boolean actual = withdrawValidator.validate("withdraw 12345678 100");
        assertTrue(actual);
    }

    @Test
    void check_the_max_withdraw_range_of_checking() {
        boolean actual = withdrawValidator.validate("withdraw 12345678 400");
        assertTrue(actual);
    }

    @Test
    void check_above_the_max_withdraw_range_of_checking() {
        boolean actual = withdrawValidator.validate("withdraw 12345678 401");
        assertFalse(actual);
    }

    @Test
    void check_the_min_withdraw_range_of_checking() {
        boolean actual = withdrawValidator.validate("withdraw 12345678 0");
        assertTrue(actual);

    }

    @Test
    void check_negative_amount_can_not_be_withdrawed() {
        boolean actual = withdrawValidator.validate("withdraw 12345678 -1");
        assertFalse(actual);
    }

    @Test
    void valid_withdraw_from_savings() {
        boolean actual = withdrawValidator.validate("withdraw 12345679 500");
        assertTrue(actual);
    }

    @Test
    void invalid_if_the_amount_is_above_the_balance_in_cd() {
        boolean actual = withdrawValidator.validate("withdraw 12345687 1300");
        assertFalse(actual);
    }

    @Test
    void valid_withdraw_from_cd() {
        boolean actual = withdrawValidator.validate("withdraw 12345687 1222");
        assertTrue(actual);
    }
}

