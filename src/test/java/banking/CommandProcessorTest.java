package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandProcessorTest {
    private Bank bank;
    private CommandProcessor commandProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void check_bank_has_checking() {
        commandProcessor.processCommand("create checking 12345678 1.0");

        assertTrue(bank.checkIdExists("12345678"));
        assertEquals("12345678", bank.getAccounts().get("12345678").getId());
        assertEquals(1.0, bank.getAccounts().get("12345678").getAPRValue());
    }


    @Test
    void check_account_has_balance_of_100() {
        commandProcessor.processCommand("create checking 12345678 1.0");
        commandProcessor.processCommand("deposit 12345678 100");

        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void check_account_has_savings() {
        commandProcessor.processCommand("create savings 12345679 1.0");

        assertTrue(bank.checkIdExists("12345679"));
        assertEquals("12345679", bank.getAccounts().get("12345679").getId());
        assertEquals(1.0, bank.getAccounts().get("12345679").getAPRValue());
    }

    @Test
    void check_account_has_cd() {
        commandProcessor.processCommand("create cd 12345677 1.0 1222");

        assertTrue(bank.checkIdExists("12345677"));
        assertEquals("12345677", bank.getAccounts().get("12345677").getId());
        assertEquals(1.0, bank.getAccounts().get("12345677").getAPRValue());
        assertEquals(1222, bank.getAccounts().get("12345677").getBalance());
    }

    @Test
    void check_account_can_be_deposited_twice() {
        commandProcessor.processCommand("create checking 12345678 1.0");
        commandProcessor.processCommand("deposit 12345678 1000");
        commandProcessor.processCommand("deposit 12345678 1000");


        assertEquals(2000, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void check_account_can_be_withdrawn() {
        commandProcessor.processCommand("create checking 12345678 1.0");
        commandProcessor.processCommand("deposit 12345678 1000");
        commandProcessor.processCommand("withdraw 12345678 500");


        assertEquals(500, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void check_account_can_be_withdrawn_twice() {
        commandProcessor.processCommand("create checking 12345678 1.0");
        commandProcessor.processCommand("deposit 12345678 1000");
        commandProcessor.processCommand("withdraw 12345678 500");
        commandProcessor.processCommand("withdraw 12345678 400");


        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void check_if_transfer_can_happen_between_accounts() {
        commandProcessor.processCommand("create checking 12345678 1.0");
        commandProcessor.processCommand("create savings 12345679 1.0");
        commandProcessor.processCommand("deposit 12345678 1000");
        commandProcessor.processCommand("transfer 12345678 12345679 400");

        assertEquals(600, bank.getAccounts().get("12345678").getBalance());
        assertEquals(400, bank.getAccounts().get("12345679").getBalance());
    }

}