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

public class SortableDataTablesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkFirstTableData() {
        driver.get("https://the-internet.herokuapp.com/tables");

        // Create array of all Table1 elements
        String[][] expected = {
                {"Smith", "John", "jsmith@gmail.com", "$50.00", "http://www.jsmith.com", "edit delete"},
                {"Bach", "Frank", "fbach@yahoo.com", "$51.00", "http://www.frank.com", "edit delete"},
                {"Doe", "Jason", "jdoe@hotmail.com", "$100.00", "http://www.jdoe.com", "edit delete"},
                {"Conway", "Tim", "tconway@earthlink.net", "$50.00", "http://www.timconway.com", "edit delete"}
        };

        // Find table 1 by id
        WebElement table1 = driver.findElement(By.id("table1"));

        // Find rows size of table 1
        List<WebElement> rows = table1.findElements(By.xpath(".//tbody/tr"));
        int rowCount = rows.size();

        // Find column size of table 1
        List<WebElement> headerCells = table1.findElements(By.xpath(".//thead/tr[1]/th"));
        int colCount = headerCells.size();

        // Access all tables elements
        // actual[i+1][j+1], expected[i][j]
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                String xpath = String.format(
                        "//table[@id='table1']//tbody//tr[%d]//td[%d]",
                        i + 1, j + 1
                );
                String actual = driver.findElement(By.xpath(xpath)).getText();
                Assert.assertEquals(
                        actual,
                        expected[i][j],
                        String.format("Mismatch at row %d col %d", i + 1, j + 1)
                );
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
