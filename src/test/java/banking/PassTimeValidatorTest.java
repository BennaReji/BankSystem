package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeValidatorTest {
    PassTimeValidator passTimeValidator;


    @BeforeEach
    void setUp() {
        passTimeValidator = new PassTimeValidator();


    }

    @Test
    void valid_pass_time_command() {
        assertTrue(passTimeValidator.validate("pass 1"));
        assertTrue(passTimeValidator.validate("pass 12"));
        assertTrue(passTimeValidator.validate("pass 60"));
    }

    @Test
    void invalid_pass_time_command_out_of_range() {
        assertFalse(passTimeValidator.validate("pass 0"));
        assertFalse(passTimeValidator.validate("pass 61"));
        assertFalse(passTimeValidator.validate("pass 100"));
    }

    @Test
    void invalid_pass_time_command_with_wrong_format() {
        assertFalse(passTimeValidator.validate("pas 12"));
        assertFalse(passTimeValidator.validate("pass th"));
        assertFalse(passTimeValidator.validate("12"));
    }


}
