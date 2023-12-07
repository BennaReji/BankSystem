package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateDepositTest {
    CertificateDeposit cd;

    @BeforeEach
    public void setUp() {
        cd = new CertificateDeposit("12345678", 2.0, 1000);
    }

    @Test
    public void cd_created_with_specified_amount_of_balance() {
        double balance = cd.getBalance();
        assertEquals(1000, balance);
    }
}

