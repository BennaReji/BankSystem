import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateDepositTest {
    CertificateDeposit cd;

    @BeforeEach
    public void setUp() {
        cd = new CertificateDeposit(2, 12345678, 30);
    }

    @Test
    public void cd_created_with_specified_amount_of_balance() {
        double balance = cd.getBalance();
        assertEquals(30, balance);
    }
}

