package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {
    MasterControl masterControl;
    private ArrayList<String> input;


    @BeforeEach
    void setUp() {
        input = new ArrayList<>();
        Bank bank = new Bank();
        masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank), new StoreCommands());

    }

    @Test
    void typo_in_create_command_is_invalid() {
        input.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(input);

        assertSingleCommand("creat checking 12345678 1.0", actual);
    }

    @Test
    void typo_in_deposit_command_is_invalid() {
        input.add("depositt 12345678 100");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("depositt 12345678 100", actual);

    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }

    @Test
    void two_typo_commands_both_invalid() {
        input.add("creat checking 12345678 1.0");

        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("creat checking 12345678 1.0", actual.get(0));
        assertEquals("depositt 12345678 100", actual.get(1));
    }

    @Test
    void invalid_to_create_accounts_with_same_ID() {
        input.add("create checking 13345578 2.0");
        input.add("create checking 13345578 2.0");

        List<String> actual = masterControl.start(input);

        assertEquals("create checking 13345578 2.0", actual.get(1));
    }

    @Test
    void check_if_checking_does_not_allow_initial_balance() {
        input.add("create checking 12345666 2.0 20");
        List<String> actual = masterControl.start(input);

        assertSingleCommand("create checking 12345666 2.0 20", actual);

    }

    @Test
    void check_if_savings_does_not_allow_initial_balance() {
        input.add("create savings 12345679 2.0 20");
        List<String> actual = masterControl.start(input);
        assertSingleCommand("create savings 12345679 2.0 20", actual);

    }

    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }

    @Test
    void test_run_1() {
        input.add("Create savings 12345678 0.6");
        input.add("deposit 12345678 700");
        input.add("deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("deposit 98765432 300");
        input.add("transfer 98765432 12345678 300");
        input.add("pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }


    @Test
    void check_if_all_command_output() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 1500");
        input.add("withdraw 12345678 200");
        input.add("withdraw 12345678 200");
        List<String> actual = masterControl.start(input);
        assertEquals(4, actual.size());
        assertEquals("Savings 12345678 1300.00 0.60", actual.get(0));
        assertEquals("Deposit 12345678 1500", actual.get(1));
        assertEquals("Withdraw 12345678 200", actual.get(2));
        assertEquals("Withdraw 12345678 200", actual.get(3));

    }

    @Test
    void check_that_it_fails_for_withdraw() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 1500");
        input.add("Withdraw 12345678 200");
        input.add("Withdraw 12345678 200");
        List<String> actual = masterControl.start(input);
        assertEquals(4, actual.size());
        assertEquals("Withdraw 12345678 200", actual.get(3));
    }

    @Test
    void test_run_2() {
        input.add("Create savings 12345678 0.6");
        input.add("deposit 12345678 700");
        input.add("deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("deposit 98765432 300");
        input.add("Withdraw 12345678 1111");
        input.add("transfer 98765432 12345678 300");
        input.add("pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(6, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
        assertEquals("Withdraw 12345678 1111", actual.get(5));

    }

    @Test
    void test_run_3() {
        input.add("Create savings 12345678 0.6");
        input.add("deposit 12345678 700");
        input.add("deposit 12345678 5000");
        input.add("deposit 12345678 200");
        input.add("withdraw 12345678 200");
        input.add("withdraw 12345678 200");
        input.add("Withdraw 12345678 1111");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("deposit 98765432 300");
        input.add("transfer 98765432 12345678 300");
        input.add("pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(9, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Deposit 12345678 200", actual.get(2));
        assertEquals("Withdraw 12345678 200", actual.get(3));
        assertEquals("Transfer 98765432 12345678 300", actual.get(4));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(5));
        assertEquals("Deposit 12345678 5000", actual.get(6));
        assertEquals("Withdraw 12345678 200", actual.get(7));
        assertEquals("Withdraw 12345678 1111", actual.get(8));

    }


}
