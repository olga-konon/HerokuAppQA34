package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationMessagesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkNotificationMessageAfterClick() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        // Find and click link, which opens Notification message
        WebElement link = driver.findElement(By.cssSelector("a[href='/notification_message']"));
        link.click();

        //Find notificationMessage
        WebElement notificationMessage = driver.findElement(By.id("flash"));

        // Get actual result and remove X which appears sometimes
        String actualNotificationText = notificationMessage.getText();
        actualNotificationText = actualNotificationText.replace("×", "").trim();

        // Expected text might have 2 versions
        String expectedNotificationText1 = "Action unsuccesful, please try again";
        String expectedNotificationText2 = "Action successful";

        // Click Close button on notification, which blocked by github link
        WebElement closeButton = driver.findElement(By.className("close"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);

        Assert.assertTrue(
                actualNotificationText.equals(expectedNotificationText1) || actualNotificationText.equals(expectedNotificationText2),
                "Unexpected notification text: " + actualNotificationText
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

