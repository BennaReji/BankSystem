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
    void cd_account_initial_balance_is_minimum() {
        boolean actual = accountValidator.validate("create cd 12345698 2.5 1000");
        assertTrue(actual);
    }

    @Test
    void cd_account_initial_balance_is_maximum() {
        boolean actual = accountValidator.validate("create cd 12345876 2.5 10000");
        assertTrue(actual);
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

    @Test
    void check_if_cd_accounts_can_not_be_created_without_initial_balance() {
        boolean actual = accountValidator.validate("create cd 12345876 2.5");
        assertFalse(actual);
    }

    @Test
    void check_if_checking_does_not_allow_initial_balance() {
        boolean actual = accountValidator.validate("create checking 12345666 2.0 20");
        assertFalse(actual);
    }

    @Test
    void check_if_savings_does_not_allow_initial_balance() {
        boolean actual = accountValidator.validate("create savings 12345676 2.0 20");
        assertFalse(actual);
    }

    @Test
    void negative_apr_checking_account() {
        boolean actual = accountValidator.validate("create checking 12345678 -1.0");
        assertFalse(actual);
    }

    @Test
    void negative_apr_savings_account() {
        boolean actual = accountValidator.validate("create savings 23456789 -3.0");
        assertFalse(actual);
    }

    @Test
    void check_it_is_apr_bound_at_zero() {
        boolean actual = accountValidator.validate("create savings 23456789 0.0");
        assertFalse(actual);
    }

    @Test
    void check_the_lowest_bound() {
        boolean actual = accountValidator.validate("create savings 23456789 0.0001");
        assertTrue(actual);
    }

    @Test
    void valid_if_apr_is_10() {
        boolean actual = accountValidator.validate("create savings 23456789 10");
        assertTrue(actual);
    }


    @Test
    void non_numeric_balance_cd_account() {
        boolean actual = accountValidator.validate("create cd 12345678 5.0 abc");
        assertFalse(actual);
    }

    @Test
    void non_numeric_apr_checking_account() {
        boolean actual = accountValidator.validate("create checking 12345678 abc");
        assertFalse(actual);
    }

}
