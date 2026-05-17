package hw;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HoversTest extends BaseTest {

    @Test
    public void checkHover() {
        SoftAssert soft = new SoftAssert();

        List<String> expectedNames = List.of(
                "name: user1",
                "name: user2",
                "name: user3"
        );
        //open Hover Page
        hoversPage.openHoversPage();

        // Check every Array element
        for (int i = 0; i < hoversPage.getFigureSize(); i++) {
            WebElement item = hoversPage.getFigureItems().get(i);

            // Hover Figure icon
            hoversPage.hoverFigure(item);

            // Get name
            String actualName = hoversPage.getFigureName(item);

            // Check name corresponds to expected name
            soft.assertEquals(actualName, expectedNames.get(i), "Name is wrong for index " + i);

            // Click link
            hoversPage.clickFigureLink(item);

            // check there is no 404 error
            soft.assertFalse(hoversPage.isMessageNotFoundDisplayed(), "404 error displayed for: " + actualName);

            // Navigate back
            hoversPage.navigateBack();
        }
        soft.assertAll();
    }
}
