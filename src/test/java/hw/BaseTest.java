package hw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    WebDriver driver;
    ContextMenuPage contextMenuPage;
    FileUploadPage fileUploadPage;
    IFramePage iFramePage;
    DynamicControlPage dynamicControlPage;
    FileDownloadPage fileDownloadPage;
    HoversPage hoversPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        options.addArguments("--disable-infobars");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        contextMenuPage = new ContextMenuPage(driver);
        fileUploadPage = new FileUploadPage(driver);
        iFramePage = new IFramePage(driver);
        dynamicControlPage = new DynamicControlPage(driver);
        fileDownloadPage = new FileDownloadPage(driver);
        hoversPage = new HoversPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        if (driver != null) {
            driver.quit();
        }
    }
}
