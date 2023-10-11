import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsTest {
    Savings savings;

    @BeforeEach
    public void setUp() {
        savings = new Savings();
    }

    @Test
    public void APR_value_for_checking() {
        double aprValue = savings.getAPRValue();
        assertEquals(4, aprValue);
    }

    @Test
    public void unique_Id_for_checking() {
        int idNum = savings.getId();
        assertEquals(12345699, idNum);
    }

}
