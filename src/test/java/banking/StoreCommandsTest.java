package banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreCommandsTest {

    @Test
    void store_invalid_command() {
        StoreCommands storeCommands = new StoreCommands();

        storeCommands.addInvalidCommand("create 12345678 2.0");
        storeCommands.addInvalidCommand("12345789 100");

        assertEquals(2, storeCommands.getAllInvalidCommands().size());
        assertEquals("create 12345678 2.0", storeCommands.getAllInvalidCommands().get(0));
        assertEquals("12345789 100", storeCommands.getAllInvalidCommands().get(1));
    }
}
