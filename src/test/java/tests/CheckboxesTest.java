package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class CheckboxesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkCheckboxesStates() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // Create list of checkboxes elements
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // Get every element of list
        WebElement checkbox1 = checkboxes.get(0);
        WebElement checkbox2 = checkboxes.get(1);

        // Check, if checkbox 1 is unchecked, click, check if checked
        assertFalse( checkbox1.isSelected(), "Checkbox 1 should be unchecked");
        checkbox1.click();
        assertTrue( checkbox1.isSelected(), "Checkbox 1 should be checked");

        // Check, if checkbox 2 is checked, click, check if unchecked
        assertTrue( checkbox2.isSelected(), "Checkbox 2 should be checked");
        checkbox2.click();
        assertFalse(checkbox2.isSelected(), "Checkbox 2 should be unchecked");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
