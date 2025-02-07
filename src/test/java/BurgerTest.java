import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static Constants.Constants.*;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    Burger burger = new Burger();

    @Mock
    Bun bunMock;
    @Mock
    Ingredient ingredientMock, ingredientMock2;

    @Test
    public void setBunTest() {
        burger.setBuns(bunMock);
        assertEquals("Булка не соответствует выбранной", bunMock, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientMock);
        assertEquals("Ингредиент не добавлен", ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertEquals("Ингредиент не удалён", burger.ingredients.size(), 0);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(1,0);
        assertEquals("Ингредиент не заменён", ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    public void getPriceBurgerTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        Mockito.when(bunMock.getPrice()).thenReturn(PRICE_BUN);
        Mockito.when(ingredientMock.getPrice()).thenReturn(PRICE_INGREDIENT);
        float burgerPrice = burger.getPrice();
        float expectedPrice = PRICE_BUN * 2 + PRICE_INGREDIENT;
        assertEquals("Стоимость бургера расчитана неверно", expectedPrice, burgerPrice, 0);
    }

    @Test
    public void getReceiptTest() {
        Mockito.when(bunMock.getName()).thenReturn(NAME_BUN);
        Mockito.when(bunMock.getPrice()).thenReturn(PRICE_BUN);
        Mockito.when(ingredientMock.getType()).thenReturn(FILLING);
        Mockito.when(ingredientMock.getName()).thenReturn(NAME_INGREDIENT);
        Mockito.when(ingredientMock.getPrice()).thenReturn(PRICE_INGREDIENT);
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        String priceBurger = String.format("%f",burger.getPrice());
        String actualReceipt = burger.getReceipt();
        String expectedReceipt = "(==== " + NAME_BUN + " ====)\r\n" +
                "= " + FILLING.toString().toLowerCase() + " " + NAME_INGREDIENT + " =\r\n" +
                "(==== " + NAME_BUN + " ====)\r\n" +
                "\r\nPrice: " + priceBurger + "\r\n";
        assertEquals("Рецепт бургера не верный", expectedReceipt, actualReceipt);
    }
}