package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Paths;

public class FileUploadPage extends BasePage {

    private final By CHOOSE_FILE_BUTTON = By.id("file-upload");
    private final By UPLOAD_FILE_BUTTON = By.id("file-submit");
    private final By UPLOADED_FILE_TEXT = By.id("uploaded-files");
    private final String RELATIVE_PATH = "test_recourses/test_file.txt";

    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    public void clickChooseFileButton() {
        String absolutePath = Paths.get(RELATIVE_PATH).toAbsolutePath().toString();
        driver.findElement(CHOOSE_FILE_BUTTON).sendKeys(absolutePath);
    }

    public WebElement getChooseFileInput() {
        return driver.findElement(CHOOSE_FILE_BUTTON);
    }

    public WebElement getUploadedFileName() {
        return driver.findElement(UPLOADED_FILE_TEXT);
    }

    public void clickUploadFileButton() {
        driver.findElement(UPLOAD_FILE_BUTTON).click();
    }

    public String getUploadFileText() {
        return driver.findElement(UPLOADED_FILE_TEXT).getText();
    }
}
