import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {
    CommandValidator commandValidator;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
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
        boolean actual = commandValidator.validate("deposit 12345678 100");
        assertTrue(actual);
    }

    @Test
    void invalid_deposit() {
        boolean actual = commandValidator.validate("12345678 100");
        assertFalse(actual);
    }
}
