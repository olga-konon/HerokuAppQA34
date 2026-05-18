package hw;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContextMenuTest extends BaseTest {

    @Test
    public void checkContextMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/context_menu");

        contextMenuPage.clickContextMenu();
        wait.until(ExpectedConditions.alertIsPresent());
        assertEquals(
                contextMenuPage.getContextMenuText(),
                "You selected a context menu",
                "Text on alert is not right");

        contextMenuPage.clickAlertOk();
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        assertTrue(
                contextMenuPage.isAlertClosed(),
                "Alert was not closed");

    }
}
