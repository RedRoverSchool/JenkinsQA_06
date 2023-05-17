package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class ProfileTest extends BaseTest {
    final Map<String, String> menuToUrlMap = new HashMap<>() {{
        put("Builds", "builds");
        put("Configure", "configure");
        put("My Views", "my-views");
        put("Credentials", "credentials");
    }};

    @Test
    public void testProfileItems() {
        for (Map.Entry<String, String> entry : menuToUrlMap.entrySet()) {
            ((JavascriptExecutor) getDriver()).executeScript("document.querySelector('.login .jenkins-menu-dropdown-chevron').click()");
            getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + entry.getKey() + "']"))).click();
            Assert.assertTrue(getDriver().getCurrentUrl().contains(entry.getValue()));
        }
    }
}
