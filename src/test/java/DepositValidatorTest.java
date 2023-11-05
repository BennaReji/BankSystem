import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidatorTest {
    DepositValidator depositValidator;

    @BeforeEach
    void setUp() {
        depositValidator = new DepositValidator();
    }

    @Test
    void valid_deposit() {
        boolean actual = depositValidator.validate("deposit 12345678 100");
        assertTrue(actual);
    }

    @Test
    void deposit_above_maximum() {
        boolean actual = depositValidator.validate("deposit 12345678 30001");
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

}
