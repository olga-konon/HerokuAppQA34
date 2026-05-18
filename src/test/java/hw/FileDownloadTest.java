package hw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FileDownloadPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;

import static org.testng.Assert.assertTrue;

public class FileDownloadTest {

    FileDownloadPage fileDownloadPage;
    String downloadDir;
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();

        downloadDir = System.getProperty("user.dir") + "\\Downloads";
        Files.createDirectories(Path.of(downloadDir));

        chromePrefs.put("profile.default_content_settings.popups", 0); // disable download popups
        chromePrefs.put("download.default_directory", downloadDir); // tell Chrome where to save downloads
        chromePrefs.put("download.prompt_for_download", false); // do not ask user where to save file
        chromePrefs.put("download.directory_upgrade", true); // allow Chrome to use this folder

        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        fileDownloadPage = new FileDownloadPage(driver);
    }

    @Test
    public void checkFileDownload() throws Exception {

        driver.get("https://the-internet.herokuapp.com/download");
        fileDownloadPage.clickFileToDownload();

        Path filePath = Path.of(downloadDir, fileDownloadPage.getDownloadFileName()); // build full path to expected downloaded file
        // wait until file appears
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(d -> Files.exists(filePath));  // wait up to 15 seconds for download to finish
        assertTrue(
                Files.exists(filePath),
                "File was not downloaded");
        // remove file from folder
        Files.deleteIfExists(filePath);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
