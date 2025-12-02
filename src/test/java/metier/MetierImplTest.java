package metier;

import dao.DaoImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetierImplTest {
    @Test
    public void testCalcul() {
        MetierImpl m = new MetierImpl();
        m.setDao(new DaoImpl());
        double result = m.calcul();
        assertEquals(200.0, result, 1e-9);
    }
}
