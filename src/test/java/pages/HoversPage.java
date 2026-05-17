package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HoversPage extends BasePage {

    private final By FIGURE_ITEM = By.className("figure");
    private final By FIGURE_NAME = By.tagName("h5");
    private final By FIGURE_LINK = By.tagName("a");
    private final By MESSAGE_NOT_FOUND = By.xpath("//*[contains(text(),'404') or contains(text(),'Page not found') or contains(text(),'Not Found')]");

    public HoversPage(WebDriver driver) {
        super(driver);
    }

    public void openHoversPage() {
        driver.get("https://the-internet.herokuapp.com/hovers");
    }

    public List<WebElement> getFigureItems() {
        return driver.findElements(FIGURE_ITEM);
    }

    public int getFigureSize() {
        return getFigureItems().size();
    }

    public String getFigureName(WebElement item) {
        return item.findElement(FIGURE_NAME).getText();
    }

    public void hoverFigure(WebElement item) {
        Actions actions = new Actions(driver);
        actions.moveToElement(item).perform();
    }

    public void clickFigureLink(WebElement item) {
        item.findElement(FIGURE_LINK).click();
    }

    public boolean isMessageNotFoundDisplayed() {
        return driver.findElement(MESSAGE_NOT_FOUND).isDisplayed();
    }

    public void navigateBack() {
        driver.navigate().back();
    }
}
