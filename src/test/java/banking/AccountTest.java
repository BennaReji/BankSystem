package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    public static final double DEPOSIT = 20;
    public static final double WITHDRAW = 10;
    public static final String ID = "12345678";
    public static final String ID_2 = "12345679";
    public static final double APR = 3.5;
    public static final double INITIAL_BALANCE = 1000;

    Account cd;
    Account checking;

    @BeforeEach
    public void setUp() {
        cd = new CertificateDeposit(ID, APR, INITIAL_BALANCE);
        checking = new Checking(ID_2, APR);


    }

    @Test
    public void deposit_dollars_to_cd_and_checking() {
        cd.deposit(DEPOSIT);
        double balance = cd.getBalance();
        assertEquals(1020, balance);
        checking.deposit(DEPOSIT);
        double balance1 = checking.getBalance();
        assertEquals(20, balance1);
    }

    @Test
    public void deposit_dollars_twice_to_cd() {
        cd.deposit(DEPOSIT);
        cd.deposit(DEPOSIT);
        double balance = cd.getBalance();
        assertEquals(1040, balance);
    }

    @Test
    public void withdraw_from_cd() {
        cd.withdraw(WITHDRAW);
        double balance = cd.getBalance();
        assertEquals(990, balance);
    }

    @Test
    public void withdraw_twice_from_cd() {
        cd.withdraw(WITHDRAW);
        cd.withdraw(WITHDRAW);
        double balance = cd.getBalance();
        assertEquals(980, balance);
    }

    @Test
    public void make_it_zero_balance_if_there_is_no_money_left() {
        cd.withdraw(1001);
        double balance = cd.getBalance();
        assertEquals(0, balance);
    }

    @Test
    public void apr_value() {
        double aprValue = cd.getAPRValue();
        assertEquals(APR, aprValue);
    }


    @Test
    public void unique_id() {
        String idNum = cd.getId();
        assertEquals(ID, idNum);
    }

    @Test
    public void verify_account_type_for_cd() {
        String accountType = cd.getAccountType();
        assertEquals("Cd", accountType);
    }

    @Test
    public void check_accountAge_increment_correctly_for_cd() {
        cd.addAge(12);
        int age = cd.getAge();
        assertEquals(12, age);
    }

    @Test
    public void deposit_zero_amount_should_not_change_balance() {
        cd.deposit(0);
        double balance = cd.getBalance();
        assertEquals(INITIAL_BALANCE, balance);
    }

    @Test
    public void withdraw_more_than_balance_balance_should_remain_unchanged() {
        cd.withdraw(INITIAL_BALANCE + 100);
        double balance = cd.getBalance();
        assertEquals(0, balance);
    }


}
