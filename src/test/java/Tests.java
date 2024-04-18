import java.util.ArrayList;
import java.util.List;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Tests extends  TestBase{


        private final By Tech = By.cssSelector("a[href=\"/catalog--elektronika/54440\"]");
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
                        //driver.findElement(By.cssSelector("button[class=\"_30-fz button-focus-ring Hkr1q _1pHod _2rdh3 _3rbM-\"]")).click();
                        driver.findElement(By.cssSelector("a[href=\"/catalog--elektronika/54440\"]")).click();
                        logger.info("goToTechnics success");
                } catch (Exception e){
                        logger.error("goToTechics failed");
                }
        }
        @Step("Выбираем технику Ноутбук(1 Тест кейс)/Планшет(2 Тест кейс)")
        public void goToElement(String element){
                try {
                        driver.findElement(By.cssSelector("a[href=\"https://market.yandex.ru/catalog--noutbuki/26895412/list?hid=91013&local-offers-first=0\"")).click();
                        logger.info(element +" success");
                } catch (Exception e){
                        logger.error(element+" failed");
                }
        }
        @Step("Выбираем технику Ноутбук(1 Тест кейс)/Планшет(2 Тест кейс)")
        public void setUpMax(String max){
                try {
                        WebElement MaxValue = driver.findElement(By.cssSelector("input[id=\"range-filter-field-glprice_jaq0tcv7bhh_max\"]"));
                        MaxValue.click();
                        MaxValue.sendKeys(max);
                        System.out.println(MaxValue);
                } catch (Exception e){
                        logger.error("Setting up max failed");
                }
        }
}
