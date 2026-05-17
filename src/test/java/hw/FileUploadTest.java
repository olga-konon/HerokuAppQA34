package hw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class FileUploadTest extends BaseTest {

    @Test
    public void checkFileUpload() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/upload");

         fileUploadPage.clickChooseFileButton();

        // wait until selected file name appears in input / near input
        wait.until(ExpectedConditions.textToBePresentInElementValue(
                fileUploadPage.getChooseFileInput(),
                "test_file.txt"
        ));

        fileUploadPage.clickUploadFileButton();

        // wait until uploaded result is visible
        wait.until(ExpectedConditions.visibilityOf(fileUploadPage.getUploadedFileName()));

        assertEquals(fileUploadPage.getUploadFileText(),
                "test_file.txt",
                "File is not uploaded");

    }
}
