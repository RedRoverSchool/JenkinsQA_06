package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class UserPageTest extends BaseTest {

    private final By User_Name_Link = By.xpath("//a[@href='/user/admin']");

    @Test
    public void testVerifyUserPageMenu() {
        List<String> listMenuExpected = Arrays.asList("People", "Status", "Builds", "Configure", "My Views", "Credentials");

        getDriver().findElement(User_Name_Link).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
        List<WebElement> listMenu = getDriver().findElements(By.className("task"));

        for (int i = 0; i < listMenu.size(); i++) {
            Assert.assertEquals(listMenu.get(i).getText(), listMenuExpected.get(i));
        }
    }
}
