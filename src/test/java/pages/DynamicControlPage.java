package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicControlPage extends BasePage {

    private final By REMOVE_BUTTON = By.xpath("//*[text()='Remove']");
    private final By MESSAGE_TEXT = By.id("message");
    private final By CHECKBOX_FIELD = By.cssSelector("[type='checkbox']");
    private final By INPUT_FIELD = By.cssSelector("input");
    private final By ENABLE_BUTTON = By.xpath("//*[text()='Enable']");

    public DynamicControlPage(WebDriver driver) {
        super(driver);
    }

    public void clickRemoveButton() {
        driver.findElement(REMOVE_BUTTON).click();
    }

    public void checkMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_TEXT));
    }

    public void checkCheckboxDisplayed() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CHECKBOX_FIELD));
    }

    public int checkCheckboxSize() {
        return driver.findElements(CHECKBOX_FIELD).size();
    }

    public boolean isInputEnabled() {
        return driver.findElement(INPUT_FIELD).isEnabled();
    }

    public void clickEnableButton() {
        driver.findElement(ENABLE_BUTTON).click();
    }
}


