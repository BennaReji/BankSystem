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

        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals("12345678", bank.getAccounts().get("12345678").getId());
        assertEquals(1.0, bank.getAccounts().get("12345678").getAPRValue());
    }


    @Test
    void check_account_has_balance_of_100() {
        commandProcessor.processCommand("create checking 12345678 1.0");
        commandProcessor.processCommand("deposit 12345678 100");

        assertEquals(100, bank.getAccounts().get("12345678").getBalance());
    }
}