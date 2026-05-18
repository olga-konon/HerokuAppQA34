package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IFramePage extends BasePage {

    private final By CLOSE_POPUP_BUTTON = By.cssSelector("div[role='dialog'] button");
    private final By IFRAME = By.id("mce_0_ifr");
    private final By IFRAME_TEXT = By.cssSelector("p");

    public IFramePage(WebDriver driver) {
        super(driver);
    }

    public void switchToIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IFRAME));

    }

    public String getIframeText() {
        return driver.findElement(IFRAME_TEXT).getText();
    }
}
