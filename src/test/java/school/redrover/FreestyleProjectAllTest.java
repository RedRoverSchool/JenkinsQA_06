package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProjectAllTest extends BaseTest {

    @Test
    public void testCreateFreestyleProjectWithValidData() {
        final String projectName = "My Freestyle Project";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(projectName);
        getDriver().findElement(By.cssSelector(".icon-freestyle-project")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        getDriver().findElement(By.linkText("Dashboard")).click();

        String actualProjectNameOnDashboard = getWait10().until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector(".jenkins-table__link.model-link.inside>span")))
                .getText();

        Assert.assertEquals(actualProjectNameOnDashboard, projectName);
    }

    @DataProvider(name = "wildcards")
    public Object[][] provideWildcardInName() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"["},
                {"]"}, {"?"}, {"<"}, {">"}, {"/"}, {"|"}, {":"}, {";"}};
    }

    @Test(dataProvider = "wildcards")
    public void testCreateFreestyleProjectWithInvalidData(String wildcard) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys(wildcard);

        String actualWarningUnsafeMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        getDriver().findElement(By.cssSelector(".icon-freestyle-project")).click();

        Assert.assertEquals(actualWarningUnsafeMessage, "» ‘" + wildcard + "’ is an unsafe character");
        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled(), "Button is disabled, but if you see this message, button is enabled");
    }
}
