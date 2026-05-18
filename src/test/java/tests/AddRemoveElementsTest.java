package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkAddingTwoElementsAndRemovingOne() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        //Add locators two Add and Remove buttons
        By addButton = By.xpath("//button[text()='Add Element']");
        By deleteButton = By.xpath("//button[text()='Delete']");

        // Click 2 times add Button
        driver.findElement(addButton).click();
        driver.findElement(addButton).click();

        // Create list of delete Buttons and verify it equals to 2
        List<WebElement> deleteButtons = driver.findElements(deleteButton);
        Assert.assertEquals(deleteButtons.size(), 2, "Amount of Delete buttons should be equal 2");

        //Click delete button
        deleteButtons.get(0).click();

        //Update list of deleteButtons and verify it equals to 1
        deleteButtons = driver.findElements(deleteButton);
        Assert.assertEquals(deleteButtons.size(), 1, "Amount of Delete buttons should be equal 1");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


