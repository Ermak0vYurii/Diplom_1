import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String nameBun;
    private final float priceBun;

    public BunTest(String nameBun, Float priceBun) {
        this.nameBun = nameBun;
        this.priceBun = priceBun;
    }

    @Parameterized.Parameters(name = "nameBun: {0}, priceBun: {1}")
    public static Object[][] testData() {
        return new Object[][]{
                {"black bun", 100F},
                {"black bun", 0F},
                {"black bun", -100F},
                {"black bun", 158.99F},
                {"Черная булка", 100F},
                {"666 bun", 100F},
                {"@#&[]", 100F},
                {"", 100F},
                {null, 100F},
        };
    }

    @Test
    public void getNameTest() {
        Bun bun = new Bun(nameBun, priceBun);
        assertEquals("Неверное название булки", nameBun, bun.getName());
    }

    @Test
    public void getPriceTest() {
        Bun bun = new Bun(nameBun, priceBun);
        assertEquals("Неверная стоимость булки", priceBun, bun.getPrice(), 0);
    }
}