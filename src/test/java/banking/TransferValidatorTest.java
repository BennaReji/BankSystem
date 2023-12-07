package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferValidatorTest {
    TransferValidator transferValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferValidator = new TransferValidator(bank);

        bank.addAccount(new Checking("12345678", 1.0));
        bank.addAccount(new Savings("12345679", 2.0));
    }

    @Test
    void valid_transfer_within_checking() {
        assertTrue(transferValidator.validate("transfer 12345678 12345679 500"));
    }

    @Test
    void valid_transfer_within_savings() {
        assertTrue(transferValidator.validate("transfer 12345679 12345678 300"));
    }

    @Test
    void valid_transfer_checking_to_savings() {
        assertTrue(transferValidator.validate("transfer 12345678 12345679 200"));
    }

    @Test
    void valid_transfer_savings_to_checking() {
        assertTrue(transferValidator.validate("transfer 12345679 12345678 100"));
    }

    @Test
    void invalid_transfer_invalid_accounts() {
        assertFalse(transferValidator.validate("transfer 1234567 98765432 100"));
    }

    @Test
    void invalid_transfer_negative_amount() {
        assertFalse(transferValidator.validate("transfer 12345678 12345679 -50"));
    }

    @Test
    void invalid_transfer_to_cd_account() {
        bank.addAccount(new CertificateDeposit("12345888", 2.0, 1500.0));
        assertFalse(transferValidator.validate("transfer 12345678 12345888 500"));
    }

}
