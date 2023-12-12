package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {
    CommandValidator commandValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        Checking checking = new Checking("12345677", 2.0);
        bank.addAccount(checking);


        commandValidator = new CommandValidator(bank);
    }

    @Test
    void valid_checking_account() {
        boolean actual = commandValidator.validate("create checking 12345678 2.0");
        assertTrue(actual);
    }

    @Test
    void invalid_checking_account() {
        boolean actual = commandValidator.validate("checking 12345678 2.0");
        assertFalse(actual);
    }

    @Test
    void valid_deposit() {
        boolean actual = commandValidator.validate("deposit 12345677 1000");
        assertTrue(actual);
    }

    @Test
    void invalid_deposit() {
        boolean actual = commandValidator.validate("12345678 100");
        assertFalse(actual);
    }

    @Test
    void invalid_no_input() {
        boolean actual = commandValidator.validate("");
        assertFalse(actual);
    }

    @Test
    void invalid_account_number_pattern() {
        boolean actual = commandValidator.isValidAccountNumber("12345");
        assertFalse(actual);
    }

    @Test
    void invalid_non_numeric_account_number_pattern() {
        boolean actual = commandValidator.isValidAccountNumber("ajdnbopo");
        assertFalse(actual);
    }


}
