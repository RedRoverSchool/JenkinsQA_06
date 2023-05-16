package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ProfileTest extends BaseTest {
    final String[] menuItems = {"Builds", "Configure", "My Views", "Credentials"};
    final String[] urlText = {"builds", "configure", "my-views", "credentials"};

    @Test
    public void testManageProfile() {
        for (int i = 0; i < menuItems.length; i++) {
            ((JavascriptExecutor) getDriver()).executeScript("document.querySelector('.login .jenkins-menu-dropdown-chevron').click()");
            getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + menuItems[i] + "']"))).click();
            Assert.assertTrue(getDriver().getCurrentUrl().contains(urlText[i]));
        }
    }
}
