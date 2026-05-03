import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkThatDropdownOptionsExistAndSelectable() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        // Create dropdown and
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);

        // Get all dropdown Options
        List<WebElement> options = select.getOptions();

        // Check 3 dropdown options: "Please select an option", "Option 1", "Option 2"
        Assert.assertEquals(options.size(), 3, "Expects 3 options");
        Assert.assertEquals(options.get(0).getText(), "Please select an option");
        Assert.assertEquals(options.get(1).getText(), "Option 1");
        Assert.assertEquals(options.get(2).getText(), "Option 2");

        // Select "Option 1" and check text
        select.selectByValue("1");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1", "Selected option 1");

        // Select "Option 2" and check text
        select.selectByValue("2");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 2", "Selected option 2");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
