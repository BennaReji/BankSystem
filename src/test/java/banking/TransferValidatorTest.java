package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransferValidatorTest {
    TransferValidator transferValidator;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferValidator = new TransferValidator(bank);
        Checking checking = new Checking("12345678", 1.0);
        Checking checking1 = new Checking("12345777", 1.0);
        Savings savings = new Savings("12345679", 2.0);
        Savings savings2 = new Savings("12345698", 2.0);
        bank.addAccount(checking);
        bank.addAccount(checking1);
        bank.addAccount(savings);
        bank.addAccount(savings2);

        bank.getAccounts().get("12345678").deposit(1000.0);
        bank.getAccounts().get("12345679").deposit(1500.0);


    }

    @Test
    void valid_transfer_within_checking() {
        assertTrue(transferValidator.validate("transfer 12345678 12345777 400"));

    }

    @Test
    void check_if_the_right_amount_has_been_transferred() {
        assertTrue(transferValidator.validate("transfer 12345678 12345777 400"));
        assertEquals(600, bank.getAccounts().get("12345678").getBalance());
        assertEquals(400, bank.getAccounts().get("12345777").getBalance());
    }

    @Test
    void check_if_withdrawl_rules_apply_for_checking() {
        assertFalse(transferValidator.validate("transfer 12345678 12345777 401"));
    }


    @Test
    void valid_transfer_within_savings() {
        assertTrue(transferValidator.validate("transfer 12345679 12345698 300"));
    }

    @Test
    void check_if_withdrawal_rules_apply_for_savings() {
        assertFalse(transferValidator.validate("transfer 12345679 12345698 1001"));
    }

    @Test
    void valid_transfer_checking_to_savings() {
        assertTrue(transferValidator.validate("transfer 12345678 12345679 200"));
    }

    @Test
    void check_if_deposit_rules_apply_to_checking() {
        assertFalse(transferValidator.validate("transfer 12345679 12345678 1500"));

    }


    @Test
    void valid_transfer_savings_to_checking() {
        assertTrue(transferValidator.validate("transfer 12345679 12345678 100"));
    }

    @Test
    void check_if_the_right_amount_has_been_transferred_between_different_accounts() {
        assertTrue(transferValidator.validate("transfer 12345679 12345678 100"));
        assertEquals(1400, bank.getAccounts().get("12345679").getBalance());
        assertEquals(1100, bank.getAccounts().get("12345678").getBalance());
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
