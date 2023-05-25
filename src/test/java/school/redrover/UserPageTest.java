package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;

public class UserPageTest extends BaseTest {

    private static final String USER_FULL_NAME = RandomStringUtils.randomAlphanumeric(13);

    private static final String DESCRIPTION = RandomStringUtils.randomAlphanumeric(130) + "\n\n" + RandomStringUtils.randomAlphanumeric(23);

    private final By User_Name_Link = By.xpath("//a[@href='/user/admin']");

    @Ignore
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

    @Ignore
    @Test
    public void testVerifyChangeNameUser() {
        getDriver().findElement(User_Name_Link).click();

        WebElement configure = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/user/admin/configure']")));
        configure.click();

        WebElement fullName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.fullName']")));
        fullName.clear();
        fullName.sendKeys(USER_FULL_NAME);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(User_Name_Link).getText(), USER_FULL_NAME);
    }

    @Ignore
    @Test
    public void testVerifyUserDescription() {
        getDriver().findElement(User_Name_Link).click();

        WebElement editDescription = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='description-link']")));
        editDescription.click();

        WebElement fullName = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));
        fullName.clear();
        fullName.sendKeys(DESCRIPTION);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div")).getText(), DESCRIPTION);
    }
}
