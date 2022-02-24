import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.commands.Hover;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.sleep;

public class BrandTest {

    @BeforeEach
    void precondition() {
        Selenide.open("https://www.strunki.ru/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"ERNIE BALL 2833", "FEDOSOV ГБ4-2 - (45-65-85-105)"})
    @ParameterizedTest(name = "Проверка наличия товара \"{0}\" в каталоге")
    void inStockTest(String testProduct){

        Selenide.$("[data-submenu-id=strings]").hover();
        Selenide.$(byText("4-струнные")).click();
        Selenide.$("#eFiltr_results").find(testProduct).shouldHave(Condition.text("В наличии"));

    }

}
