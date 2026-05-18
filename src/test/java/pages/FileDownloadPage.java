package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileDownloadPage extends BasePage {

    private final By DOWNLOAD_FILE_LINK = By.xpath("//a[contains(@href,'download')]");

    public FileDownloadPage(WebDriver driver) {
        super(driver);
    }

    public String getDownloadFileName() {
        return driver.findElement(DOWNLOAD_FILE_LINK).getText();
    }
    public void clickFileToDownload() {
        driver.findElement(By.linkText(getDownloadFileName())).click();
    }
}
