import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    private final By SEARCHFIELD = By.id("header-search");
    private final By SUBMITBUTTON = By.xpath("//button[@type='submit']");
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
        public void setPriceMax(String max) {
                        WebElement priceTo = waitFor(By.cssSelector("input[id$=\"max\"]"));
                        priceTo.sendKeys(max + "\n");
        }
    public void setPriceMin(String min) {
        WebElement priceTo = waitFor(By.cssSelector("input[id$=\"max\"]"));
        priceTo.sendKeys(min + "\n");
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


            logger.info("Manufacture set");
        } catch (Exception e) {
            logger.error("Manufacture faild to find");
        }
    }
    @Step("Выбираем первый элемент из списка")
    public String selectFirstElement() {
        String firstElementOnPage = "";
        try {
            /*Тут должен выбираться первый элемент по сортировке Производитель и макс цена, но он ене успевает переключится по производителю и выбирает первый элемент по цене*/
            WebElement firstElement = driver.findElement(By.cssSelector("h3[role=\"link\"]"));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-zone-name=\"searchResults\"]"))));
            firstElementOnPage = firstElement.getText();
            logger.info("firstElementOnPage = " + firstElementOnPage);
            logger.info("selectFirstElement success");
        } catch (Exception e) {
            logger.error("selectFirstElement failed");
        }
        return (firstElementOnPage);
    }

    @Step("Проверка на совпадение первого элемента списка")
    public void assertFirstElement(String firstElementOnPage) {
        String newFirstElementOnPage = "";
        try {

            waitFor(SEARCHFIELD).sendKeys(firstElementOnPage);
            waitFor(SUBMITBUTTON).click();
            WebElement newFirstElement = driver.findElement(By.cssSelector("h3[role=\"link\"]"));
            newFirstElementOnPage = newFirstElement.getText();
            logger.info("newFirstElementOnPage = " + newFirstElementOnPage);
            Assertions.assertEquals(firstElementOnPage, newFirstElementOnPage, "Elements are equal");
            logger.info("Elements are equal");
        } catch (Exception e) {
            logger.error("assertFirstElement failed");
        }
    }

}
