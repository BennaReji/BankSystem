package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    public static final double DEPOSIT = 30;
    public static final double WITHDRAW = 10;
    public static final String ID_2 = "12345678";
    public static final double APR = 3.5;
    public static final String ID = "12345679";


    Bank bank;
    Account account1;
    Account account2;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        account1 = new Checking(ID, APR);
        account2 = new Savings(ID_2, APR);
    }

    @Test
    void bank_has_no_accounts_initially() {
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void add_account_to_bank() {
        bank.addAccount(account1);
        assertEquals(1, bank.getAccounts().size());

    }

    @Test
    void add_second_account_to_bank() {
        bank.addAccount(account1);
        bank.addAccount(account2);
        assertEquals(2, bank.getAccounts().size());
    }

    @Test
    void retrieve_account_by_id() {
        bank.addAccount(account1);
        assertEquals(ID, bank.getAccounts().get(ID).getId());
    }

    @Test
    void deposit_money_with_id() {
        bank.addAccount(account1);
        bank.getAccounts().get(ID).deposit(DEPOSIT);

        assertEquals(DEPOSIT, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_money_with_id() {
        bank.addAccount(account1);
        bank.getAccounts().get(ID).deposit(DEPOSIT);
        bank.getAccounts().get(ID).withdraw(WITHDRAW);
        assertEquals(20, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void deposit_money_twice_with_id() {
        bank.addAccount(account1);
        bank.getAccounts().get(ID).deposit(DEPOSIT);
        bank.getAccounts().get(ID).deposit(DEPOSIT);

        assertEquals(60, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void withdraw_money_twice_with_id() {
        bank.addAccount(account1);
        bank.getAccounts().get(ID).deposit(DEPOSIT);
        bank.getAccounts().get(ID).withdraw(WITHDRAW);
        bank.getAccounts().get(ID).withdraw(WITHDRAW);

        assertEquals(10, bank.getAccounts().get(ID).getBalance());
    }

    @Test
    void close_existing_account() {
        bank.addAccount(account1);
        boolean closed = bank.closeAccount(ID);
        assertTrue(closed);
        assertFalse(bank.checkIdExists(ID));
    }

    @Test
    void close_non_existing_account() {
        boolean closed = bank.closeAccount("NonExistingID");
        assertFalse(closed);
    }

    @Test
    void close_account_check_list_of_ids() {
        bank.addAccount(account1);
        bank.closeAccount(ID);
        assertFalse(bank.getListOfAccountId().contains(ID));
    }

    @Test
    void close_account_multiple_times() {
        bank.addAccount(account1);
        bank.closeAccount(ID);
        boolean closedSecondTime = bank.closeAccount(ID);
        assertFalse(closedSecondTime);
    }


}
