import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final String name;
    private final float price;
    private final IngredientType type;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;

    }

    @Parameterized.Parameters (name = "IngredientType: {0}, IngredientName: {1}, IngredientPrice: {2}")
    public static Object[][] testData() {
        return new Object[][]{
                {SAUCE, "hot sauce", 100},
                {FILLING, "cutlet", 200}
        };
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Неверная цена ингредиента", price, ingredient.getPrice(), 0);
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Неверное имя ингредиента", name, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Неверный тип ингредиента", type, ingredient.getType());
    }
}
