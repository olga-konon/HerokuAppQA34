package hw;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IFrameTest extends BaseTest {

    @Test
    public void checkIframe() {

        driver.get("https://the-internet.herokuapp.com/iframe");

        iFramePage.switchToIframe();

        assertEquals(iFramePage.getIframeText(),
                "Your content goes here.",
                "Iframe text is wrong");
    }
}
