import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class TyposTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkParagraphsTypos() {
        SoftAssert soft = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/typos");

        // Create list of all paragraphs elements
        List<WebElement> texts = driver.findElements(By.tagName("p"));

        // Get actual text of paragraph 1 and paragraph 2
        String actualTextParagraph1 = texts.get(0).getAttribute("textContent");
        String actualTextParagraph2 = texts.get(1).getAttribute("textContent");

        // Expected text of paragraph 1
        String expectedTextParagraph1 = "This example demonstrates a typo being introduced. It does it randomly on each page load.";

        // Expected text of paragraph 1 with 2 options of won't or won,t
        String expectedTextParagraph2Correct =
                "Sometimes you'll see a typo, other times you won't.";
        String expectedTextParagraph2WithTypo =
                "Sometimes you'll see a typo, other times you won,t.";

        // Verify, that actual text of paragraph 1 is equal to expected text
        soft.assertEquals(actualTextParagraph1, expectedTextParagraph1, "First paragraph text is incorrect");

        // Verify, that actual text of paragraph 2 is equal to 1 of 2 expected text
        soft.assertTrue(
                actualTextParagraph2.equals(expectedTextParagraph2Correct)
                        || actualTextParagraph2.equals(expectedTextParagraph2WithTypo),
                "Second paragraph text is neither correct nor the known typo variant. Actual: " + actualTextParagraph2
        );
        soft.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
