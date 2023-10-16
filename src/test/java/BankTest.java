import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {
    public static final double DEPOSIT = 30;
    public static final double WITHDRAW = 10;
    public static final int ID_2 = 12345678;
    public static final double APR = 3.5;
    public static final int ID = 12345679;


    Bank bank;
    Account account1;
    Account account2;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        account1 = new Checking(APR, ID);
        account2 = new Savings(APR, ID_2);
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


}
