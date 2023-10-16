import org.junit.jupiter.api.BeforeEach;

public class SavingsTest {
    Savings savings;

    @BeforeEach
    public void setUp() {
        savings = new Savings(3, 12345679);
    }

}
