import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tests extends  TestBase{

        private List<WebElement> list = new ArrayList<>();
        private final By Tech = By.cssSelector("a[href=\"/catalog--elektronika/54440\"]");
        private final By PRODUCTNAME = By.xpath("//h3/a[contains(@title, '')]");
        //private final By YANDEXMARKETPAGE = By.xpath("//iframe[aria-label='Поиск Яндекс']");

    private WebElement waitFor(By selector) {
            return wait.until(ExpectedConditions.elementToBeClickable(driver
                    .findElement(selector)));
        }

        @Step("Открываем главную страницу Яндекса")
        public void openYandexPage() {
            driver.get("https://yandex.ru/");
            logger.info(driver.getTitle());
        }

        @Step("Открываем ЯндексМаркет")
        public void goToYandexMarket() {
                try {
                        WebElement iframelem = driver.findElement(By.tagName("iframe"));
                        driver.switchTo().frame(iframelem);
                        WebElement inputSearch = driver.findElement(By.className("mini-suggest__input"));
                        inputSearch.click();
                        driver.findElement(By.className("desktop-services__item_market")).click();
                        wait.until(ExpectedConditions
                                .numberOfWindowsToBe(2));
                        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
                        driver.switchTo().window(tabs.get(1));
                        logger.info("goToYandexMarket success");
                } catch (Exception e) {
                        logger.error("goToYandexMarket failed");
                }

        }

        @Step("Выбираем проходи в католог/Элеткроника")
        public void goToTechnics(){
                try {
                        driver.findElement(By.cssSelector("a[href=\"/catalog--elektronika/54440\"]")).click();
                        logger.info("goToTechnics success");
                } catch (Exception e){
                        logger.error("goToTechics failed");
                }
        }
       @Step("Выбираем технику Ноутбук(1 Тест кейс)/Планшет(2 Тест кейс)")
        public void goToElement(String selectorNout){
                try {
                        waitFor(By.cssSelector(selectorNout)).click();
                        logger.info("Noutebooks success");
                } catch (Exception e){
                        logger.error("Noutebooks failed");
                }
        }
        @Step("Устанавливаем цену от  {from} до {to}")
        public void setPrice(String max) {
                        WebElement priceTo = waitFor(By.cssSelector("input[id$=\"max\"]"));
                        priceTo.sendKeys(max + "\n");
                        /*Здесь идет передача максимального значения сумму для покупки в этот момент обновляется основная страница эллемента
                        * после чего чисто идейно должен быть установлен элемент производителя функция ниже. */
        }
    @Step("Выбираем производителей")
    public void setManufacturers() {
    try {


        // ждем появления на странице чекбоксов с переданными параметрами и чекаем
        WebElement Lenovo = driver.findElement(By.xpath("//span[text()='Показать всё']"));
        Lenovo.click();
        WebElement inputManufacture = driver.findElement(By.cssSelector("input[placeholder='Найти']"));

        inputManufacture.sendKeys("Lenovo");
        driver.findElement(By.xpath("//span[text()='Lenovo']")).click();
        /*В этом месте идет загрузка обработки поиска по фильтру и нужно выбрать первый элемент после обработки
        * Проблема в том что обработчик не ждет обновления основной страницы продуктов и выбирает их в промежутке между
        * двумя функциями установкой цены и установкой производителей, то есть результат получается элемент в котором
        * укзаан потолок цены но не указан производитель, а нужно чтобы и то и другое учитывалось для запоминания элемента*/
        logger.info("Manufacture set");
    } catch (Exception e){
        logger.error("Manufacture faild to find");
    }
    }
    @Step("Выбираем первый элемент из списка")
    public String selectFirstElement() {
        String firstElementOnPage = "";
        try {
            WebElement firstElement = driver.findElement(By.cssSelector("h3[role=\"link\"]"));
            firstElementOnPage = firstElement.getText();
            logger.info("firstElementOnPage = " + firstElementOnPage);
            logger.info("selectFirstElement success");

        } catch (Exception e) {
            logger.error("selectFirstElement failed");
        }
    }

    @Step("Проверка на совпадение первого элемента списка")
    public void assertFirstElement(String firstElementOnPage) {
        String newFirstElementOnPage = "";
        try {
            WebElement inputHeader = driver.findElement(By.cssSelector("input[id = \"header-search\"]"));
            inputHeader.sendKeys(firstElementOnPage);
            inputHeader.submit();
            WebElement newFirstElement = driver.findElement(By.cssSelector("h3[role=\"link\"]"));
            newFirstElementOnPage = newFirstElement.getText();
            logger.info("newFirstElementOnPage = " + newFirstElementOnPage);
            Assertions.assertEquals(firstElementOnPage, newFirstElementOnPage, "Elements are equal");

        } catch (Exception e) {
            logger.error("assertFirstElement failed");
        }
    }

}
