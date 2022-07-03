package cloud.autotests.tests.ui;

import cloud.autotests.config.TestDataConfig;
import cloud.autotests.helpers.DriverUtils;
import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class UiTests extends TestBase {
    TestDataConfig config = ConfigFactory.create(TestDataConfig.class);
    String userName = "Татьяна";

    @Test
    @DisplayName("Позитивная проверка Авторизации")
    void generatedTest() {
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () ->
            open(""));

        step("Нажать кнопку 'Войти'", ()->
            $("[data-test-id='button__auth']").click());

        step("Заполнить форму авторизации",()->{
            $("[data-test-id='input__login']").val(config.userLogin());
            $("[data-test-id='input__password']").val(config.userPassword());
        });

        step("Нажать на кнопку 'Войти'",()->
            $("[data-test-id='button__sign-in']").submit());

        step("Проверка, успешной авторизации по имени юзера",()->{
            $("[data-test-id='button__user']").shouldHave(text(userName));
        });
    }

    @Test
    @DisplayName("Каждый элемент каталога имеет атрибур ссылки 'href'")
    void hrefLinksTest(){
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () ->
                open(""));
        step("Получить все ссылки из каталога",()->{
            ElementsCollection hrefs = $$("div.products a");
            List<String> links = new ArrayList<>();
            for(SelenideElement element: hrefs){
                links.add(element.getAttribute("href"));
            }
            step("Проверка, что каждый элемент каталога имеет атрибут ссылки 'href'",()->
                hrefs.stream().forEach(x->links.add(x.getAttribute("href"))));
        });
    }


    @Test
    @DisplayName("Проверка заголовка страницы")
    void titleTest() {
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () ->
            open(""));

        step("Проверка соответствия заголовка 'KazanExpress - шопинг с бесплатной доставкой за 1 день'", () -> {
            String expectedTitle = "KazanExpress - интернет-магазин с бесплатной доставкой за 1 день";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("В журнале консоли страницы нет ошибок")
    void consoleShouldNotHaveErrorsTest() {
        step("Открыть главную страницу 'https://kazanexpress.ru/'", () ->
            open(""));

        step("Проверка журнала консоли на отсутствия в нем пометки 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}