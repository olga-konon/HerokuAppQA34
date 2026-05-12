import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InputsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void inputShouldHandleNumbersAndIgnoreLetters() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        WebElement input = driver.findElement(By.tagName("input"));

        // Check, that input doesn't accept non-numeric values
        input.sendKeys("d");
        String actualString = input.getAttribute("value");
        Assert.assertEquals(actualString, "", "Input should be empty, when enters non-numeric value");

        // check, that input accepts numeric values
        input.clear();
        input.sendKeys("1");
        String actualNumber = input.getAttribute("value");
        Assert.assertEquals(actualNumber, "1", "Input field should be equal 1");

        // Click Arrow_UP button 3 times
        for (int i = 0; i < 3; i++) {
            input.sendKeys(Keys.ARROW_UP);
        }
        // Check, that number is equal 4
        String actualNumber2 = input.getAttribute("value");
        Assert.assertEquals(actualNumber2, "4", "Value should be 4 after pressing ARROW_UP 3 times");

        // Click Arrow_DOWN button 2 times
        for (int i = 0; i < 2; i++) {
            input.sendKeys(Keys.ARROW_DOWN);
        }
        // Check, that number is equal 2
        String actualNumber3 = input.getAttribute("value");
        Assert.assertEquals(actualNumber3, "2", "Value should be 2 after pressing ARROW_DOWN 2 times");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
