import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;


public class StarterTest extends Tests {

    private final String element = "Ноутбуки";
    private final String tablets = "Планшеты";
    private final String max = "30000";

    @Test
    @Description(value = "Полный цикл тестов")
    public void testYandexPage() {
        openYandexPage();
        goToYandexMarket();
        goToTechnics();
        goToElement(element);
        setUpMax(max);
    }
}