import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.commands.Hover;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.sleep;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

public class BrandTest {

    private String dataName;

    @BeforeEach
    void precondition() {
        Selenide.open("https://www.strunki.ru/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"ERNIE BALL 2833", "FEDOSOV ГБ4"})
    @ParameterizedTest(name = "Проверка наличия товара \"{0}\" в каталоге")
    void inStockTest(String testProduct){

        Selenide.$("[data-submenu-id=strings]").hover();
        Selenide.$(byText("4-струнные")).click();
        Selenide.$("#eFiltr_results_wrapper").shouldHave(Condition.text(testProduct));
    }

    static Stream<Arguments> nameNumber() {
        return Stream.of(
                Arguments.of("Иван","+79501236534"),
                Arguments.of("Петр","+79501236535")
        );
    }

    @MethodSource(value = "nameNumber")
    @ParameterizedTest(name= "Проверка формы заказа звонка")
    void callingTest (String firstArg, String secondArg) {
        Selenide.$(byText("Заказать звонок")).click();
        Selenide.$("#clbk_form").shouldBe(Condition.visible);
        Selenide.$("[name=m_user]").setValue(firstArg);
        Selenide.$("[name=m_num]").setValue(secondArg);
        element("[name=m_type]").click();
        Selenide.$(byText("Консультация по товару")).click();

        //писать код на клик кнопки "отправить" не стал, жалко работнков магазина))

    }


}
