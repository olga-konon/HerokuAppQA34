package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContextMenuPage extends BasePage {

    private final By CONTEXT_MENU = By.id("hot-spot");

    public ContextMenuPage(WebDriver driver) {
        super(driver);
    }

    public void clickContextMenu() {

        WebElement contextMenu = driver.findElement(CONTEXT_MENU);
        Actions actions = new Actions(driver);
        actions.contextClick(contextMenu).perform();
    }

    private Alert getAlert() {
        return driver.switchTo().alert();
    }

    public String getContextMenuText() {
        return getAlert().getText();
    }

    public void clickAlertOk() {
        getAlert().accept();
    }

    public boolean isAlertClosed() {
        return ExpectedConditions.alertIsPresent().apply(driver) == null;
    }
}
