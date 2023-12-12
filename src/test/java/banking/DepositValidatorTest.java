package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidatorTest {

    DepositValidator depositValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        Checking checking = new Checking("12345678", 2.0);
        bank.addAccount(checking);
        depositValidator = new DepositValidator(bank);
    }

    @Test
    void valid_deposit() {
        boolean actual = depositValidator.validate("deposit 12345678 100");
        assertTrue(actual);
    }

    @Test
    void deposit_above_maximum() {
        boolean actual = depositValidator.validate("deposit 12345678 1001");
        assertFalse(actual);
    }

    @Test
    void deposit_below_the_range() {
        boolean actual = depositValidator.validate("deposit 12345678 -100");
        assertFalse(actual);
    }

    @Test
    void typo_in_deposit() {
        boolean actual = depositValidator.validate("depo2it 12345678 100");
        assertFalse(actual);
    }

    @Test
    void deposit_money_missing() {
        boolean actual = depositValidator.validate("deposit 12345678");
        assertFalse(actual);
    }

    @Test
    void missing_deposit_id_and_money() {
        boolean actual = depositValidator.validate("");
        assertFalse(actual);
    }

    @Test
    void case_insensitivity() {
        boolean actual = depositValidator.validate("Deposit 12345678 100");
        assertTrue(actual);
    }

    @Test
    void invalid_account_number() {
        boolean actual = depositValidator.validate("deposit 1234abc 100");
        assertFalse(actual);
    }

    @Test
    void non_existent_id() {
        boolean actual = depositValidator.validate("deposit 23456789 100");
        assertFalse(actual);
    }

    @Test
    void non_numeric_deposit_amount() {
        boolean actual = depositValidator.validate("deposit 12345678 ajsjsj");
        assertFalse(actual);
    }


}
