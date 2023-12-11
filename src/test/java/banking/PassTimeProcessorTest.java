package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassTimeProcessorTest {

    private Bank bank;
    private PassTimeProcessor passTimeProcessor;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        passTimeProcessor = new PassTimeProcessor(bank);

        Checking checking = new Checking("12345678", 1.0);
        Savings savings = new Savings("12345679", 2.0);
        Checking checking1 = new Checking("12345777", 3.0);
        Savings savings1 = new Savings("12345888", 3.0);
        CertificateDeposit cd = new CertificateDeposit("12345699", 2.1, 2000);

        savings1.deposit(1000);
        checking1.deposit(1000);
        checking.deposit(99);
        savings.setBalance(0);
        bank.addAccount(cd);
        bank.addAccount(savings1);
        bank.addAccount(checking1);
        bank.addAccount(checking);
        bank.addAccount(savings);

    }

    @Test
    void check_if_balance_reduces_for_accounts_below_100_when_a_month_goes_by() {
        passTimeProcessor.passTime(1);
        assertEquals(74.06166666666667, bank.getAccounts().get("12345678").getBalance());
    }

    @Test
    void check_if_balance_reduces_by_twice_when_two_months_go_by() {
        passTimeProcessor.passTime(2);
        assertEquals(49.10255138888889, bank.getAccounts().get("12345678").getBalance());
    }


    @Test
    void test_if_account_close_if_it_has_no_money() {
        passTimeProcessor.passTime(1);
        assertEquals(null, bank.getAccounts().get("12345679"));
    }

    @Test
    void test_if_APR_calculation_is_done_correctly_for_checking() {
        passTimeProcessor.passTime(1);
        assertEquals(1002.5, bank.getAccounts().get("12345777").getBalance(), 0.01);

    }

    @Test
    void test_if_Apr_calculation_is_done_correctly_for_savings() {
        passTimeProcessor.passTime(1);
        assertEquals(1002.5, bank.getAccounts().get("12345888").getBalance(), 0.01);
    }

    @Test
    void test_if_apr_calculation_is_done_correctly_for_cd() {
        passTimeProcessor.passTime(1);
        assertEquals(2014.0, bank.getAccounts().get("12345699").getBalance(), 0.01);
    }

    @Test
    void test_if_apr_calculation_is_done_correctly_for_cd_twice() {
        passTimeProcessor.passTime(2);
        assertEquals(2028.098, bank.getAccounts().get("12345699").getBalance(), 0.01);
    }


}
