import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;


public class StarterTest extends Tests {

    private final String selectorNout = "a[href=\"https://market.yandex.ru/catalog--noutbuki/26895412/list?hid=91013&local-offers-first=0\"]";
    private final String selectorTablet = "a[href=\"https://market.yandex.ru/catalog--planshety/26908970/list?hid=6427100&local-offers-first=0\"]";
    private final String max = "30000";
    private final String max2 = "95000";
    private final String min = "20000";

    @Test
    @Description(value = "Полный цикл тестов")
    public void testYandexPage() {
        openYandexPage();
        goToYandexMarket();
        goToTechnics();
        goToElement(selectorNout);
        setPriceMax(max);
        setManufacturers();
        String el1 = selectFirstElement();
        assertFirstElement(el1);

    }

    @Test
    @Description(value = "Полный цикл тестов")
    public void testTask2(){
        openYandexPage();
        goToYandexMarket();
        goToTechnics();
        goToElement(selectorTablet);
        setPriceMax(max2);
        setPriceMin(min);

    }
}