package tests;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class SeleniumTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        /*Options - настройка нашего браузера перед запуском*/

        ChromeOptions options = new ChromeOptions();
        // max size
        options.addArguments("--start-maximized");
        //инкогнито
      //  options.addArguments("--incognito");
        //открытие браузера без pop-up
        options.addArguments("--disable-notifications");
        // без UI
        // options.addArguments("--headless");

        driver = new ChromeDriver(options);
        // время ожидания появления элемента
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        //свернуть браузер
        // driver.manage().window().minimize();
        //свернуть браузер
        // driver.manage().window().maximize();
        // driver.manage().window().fullscreen();

    }

    @Test
    public void cookiesTest() {
        driver.get("https://sharelane.com/cgi-bin/register.py");
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Cookies size = " + cookies.size());

        for(Cookie cookie: cookies) {
            System.out.println(cookie.getName() + cookie.getValue());

        }
    }

    @Test
    public void checkHandle() {
        driver.get("https://www.onliner.by/");
        String mainHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://catalog.onliner.by/");
        String catalogHandle = driver.getWindowHandle();
        driver.switchTo().window(mainHandle);
        driver.switchTo().window(catalogHandle);
    }

    @AfterMethod
    public void tearDown() {
        //Закрыть браузер
        driver.quit();
        //Закрыть вкладку браузера
        // driver.close();

    }
}
