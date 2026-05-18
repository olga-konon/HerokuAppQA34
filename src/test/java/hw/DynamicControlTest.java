package hw;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DynamicControlTest extends BaseTest {

    @Test
    public void checkDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        dynamicControlPage.clickRemoveButton();
        dynamicControlPage.checkMessageDisplayed();
        dynamicControlPage.checkCheckboxDisplayed();

        assertEquals(dynamicControlPage.checkCheckboxSize(), 0, "Checkbox is not disabled");

        assertFalse(
                dynamicControlPage.isInputEnabled(),
                "Input is not disabled");

        dynamicControlPage.clickEnableButton();

        // wait until “It's enabled!”
        dynamicControlPage.checkMessageDisplayed();

        //проверить, что инпут enabled
        assertTrue(
                dynamicControlPage.isInputEnabled(),
                "Input is not Enabled");
    }
}



