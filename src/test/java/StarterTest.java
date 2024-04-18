import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;


public class StarterTest extends Tests {

    private final String selectorNout = "a[href=\"https://market.yandex.ru/catalog--noutbuki/26895412/list?hid=91013&local-offers-first=0\"]";
    private final String tablets = "Планшеты";
    private final String max = "30000";

    @Test
    @Description(value = "Полный цикл тестов")
    public void testYandexPage() {
        openYandexPage();
        goToYandexMarket();
        goToTechnics();
        goToElement(selectorNout);
        setPrice(max);
        setManufacturers();
        selectFirstElement();
        String el1 = selectFirstElement();
        assertFirstElement(el1);
    }
}