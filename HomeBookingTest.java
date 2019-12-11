import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeBookingTest {

    @Test
    public void getHotelDateTest() {
        System.setProperty("webdriver.chrome.driver", "D:/PVTAUto/HWork_9/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.booking.com");

        WebElement searchMoscow = driver.findElement(By.xpath(".//input[@id='ss']"));
        searchMoscow.sendKeys("Москва");
        searchMoscow.click();

        WebElement searchDate = driver.findElement(By.xpath(".//span [@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb']"));
        searchDate.click();
        WebElement chooseArrivalDate = driver.findElement(By.xpath(".//td [@data-date='2019-12-08']"));
        chooseArrivalDate.click();
        WebElement chooseDepartureDate = driver.findElement(By.xpath(".//td [@data-date='2019-12-12']"));
        chooseDepartureDate.click();

        WebElement chooseCountPerson = driver.findElement(By.xpath(".//label [@class='xp__input']"));
        chooseCountPerson.click();

        WebElement searchHotel = driver.findElement(By.xpath(".//span[text()='Проверить цены']"));
        searchHotel.submit();

        List<WebElement> getListHotels = driver.findElements(By.xpath(".//div [@data-hotelid]"));
        Assert.assertNotNull(getListHotels);

        driver.quit();
    }

    @Test
    public void getBestRatingTest() throws Exception{
        System.setProperty("webdriver.chrome.driver", "D:/PVTAUto/HWork_9/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.booking.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement searchMoscow = driver.findElement(By.xpath(".//input[@id='ss']"));
        searchMoscow.sendKeys("Москва");
        searchMoscow.click();

        WebElement searchDate = driver.findElement(By.xpath(".//span [@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb']"));
        searchDate.click();
        WebElement chooseArrivalDate = driver.findElement(By.xpath(".//td [@data-date='2019-12-08']"));
        chooseArrivalDate.click();
        WebElement chooseDepartureDate = driver.findElement(By.xpath(".//td [@data-date='2019-12-12']"));
        chooseDepartureDate.click();

        WebElement chooseCountPerson = driver.findElement(By.xpath(".//label [@class='xp__input']"));
        chooseCountPerson.click();

        WebElement searchHotel = driver.findElement(By.xpath(".//span[text()='Проверить цены']"));
        searchHotel.submit();

        WebElement filterHotel = driver.findElement(By.xpath("(.//span [contains(.,\"Превосходно: 9+\")])[1]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", filterHotel);
        filterHotel.click();
        Thread.sleep(1000);

        WebElement firstHotel = driver.findElement(By.xpath("//*[@id=\"b2searchresultsPage\"]"));
        firstHotel.click();
        WebElement bestRating = driver.findElement(By.xpath("(.//div [@class='bui-review-score__badge'])[1]"));
        String ratingStr = bestRating.getText();
        Double ratingD = Double.parseDouble(ratingStr);
        Double boolRating = 9.0;
        Assert.assertTrue(ratingD >= boolRating);

        driver.quit();

    }
}
