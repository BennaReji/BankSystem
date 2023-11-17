package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountValidatorTest {
    AccountValidator accountValidator;
    Bank bank;

    @BeforeEach
    void setUp() {

        bank = new Bank();
        accountValidator = new AccountValidator(bank);
    }

    @Test
    void valid_checking_account() {
        boolean actual = accountValidator.validate("create checking 12345699 2.0");
        assertTrue(actual);
    }

    @Test
    void valid_savings_account() {
        boolean actual = accountValidator.validate("create savings 23456789 3.0");
        assertTrue(actual);
    }

    @Test
    void valid_cd_account() {
        boolean actual = accountValidator.validate("create cd 12345679 2.5 1222");
        assertTrue(actual);
    }

    @Test
    void cd_account_initial_balance_is_below_minimum() {
        boolean actual = accountValidator.validate("create cd 12345698 2.5 999");
        assertFalse(actual);
    }

    @Test
    void cd_account_initial_balance_is_above_maximum() {
        boolean actual = accountValidator.validate("create cd 12345876 2.5 10001");
        assertFalse(actual);
    }

    @Test
    void apr_is_below_the_range() {
        boolean actual = accountValidator.validate("create checking 12345769 0");
        assertFalse(actual);
    }

    @Test
    void apr_is_above_the_range() {
        boolean actual = accountValidator.validate("create checking 12345769 10.1");
        assertFalse(actual);
    }

    @Test
    void invalid_checking_account_missing_id_number() {
        boolean actual = accountValidator.validate("create checking 2.0");
        assertFalse(actual);
    }

    @Test
    void checking_account_missing_apr() {
        boolean actual = accountValidator.validate("create checking 12345678");
        assertFalse(actual);
    }

    @Test
    void missing_account_type() {
        boolean actual = accountValidator.validate("12345678 2.0");
        assertFalse(actual);
    }

    @Test
    void missing_account_type_id_and_apr() {
        boolean actual = accountValidator.validate("");
        assertFalse(actual);
    }

    @Test
    void typo_in_account_type() {
        boolean actual = accountValidator.validate("create chExKing 12345678 2.0");
        assertFalse(actual);
    }

    @Test
    void typo_in_create() {
        boolean actual = accountValidator.validate("creatq checking 12345678 2.0");
        assertFalse(actual);
    }

    @Test
    void case_insensitivity() {
        boolean actual = accountValidator.validate("Create Checking 12345699 2.0");
        assertTrue(actual);
    }
}
