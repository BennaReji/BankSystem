package banking;

import org.junit.jupiter.api.BeforeEach;

public class CheckingTest {
    Checking checking;

    @BeforeEach
    public void setUp() {
        checking = new Checking("12345679", 2);
    }

}
