package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class FreestyleProjectAllTest extends BaseTest {
    private final String projectName = "My Freestyle Project";

    @Test
    public void testCreateFreestyleProjectWithValidData() {
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

    @Test
    public void testOpenViewProjectPageFromDashboard() {
        TestUtils.createFreestyleProject(this, projectName, true);

        getDriver().findElement(By.linkText(projectName)).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                "Project " + projectName);
        Assert.assertTrue(getDriver().findElement(By.linkText("Build Now")).isDisplayed(), "The 'Build Now' button is not displayed");
    }

    @Test
    public void testVisibleProjectNameAndDescriptionFromViewPage() {
        final String description = "This is a description for My Freestyle Project";

        TestUtils.createFreestyleProject(this, projectName, false);

        getDriver().findElement(By.linkText("Add description")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name = 'description']")))
                .sendKeys(description);
        getDriver().findElement(By.name("Submit")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.linkText("Dashboard"))).click();
        getDriver().findElement(By.linkText(projectName)).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                "Project " + projectName);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[contains(text(),'" + description + "')]")).getText(),
                description);
    }

    @Test
    public void testDisableFreestyleProject() {
        TestUtils.createFreestyleProject(this, projectName, false);

        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();

        String actualWarning = getDriver().findElement(By.xpath("//form[@id='enable-project']")).getText();
        actualWarning = actualWarning.substring(0, actualWarning.length() - "Enable".length() - 1);

        List<WebElement> projectActions = getDriver().findElements(By.xpath("//*[@class='task ']//a"));

        Assert.assertEquals(actualWarning, "This project is currently disabled");
        for (WebElement actualPossibilityBuildProject : projectActions) {
            Assert.assertNotEquals(actualPossibilityBuildProject.getText(), "Build Now",
                    "The 'Build Now' button is displayed");
        }
        Assert.assertTrue(getDriver().findElement(By.xpath("//button[contains(text(),'Enable')]")).isDisplayed(),
                "The 'Enable' button is not displayed");
    }

    @Test(dependsOnMethods = "testDisableFreestyleProject")
    public void testEnableFreestyleProject() {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(projectName))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        List<WebElement> projectActions = getDriver().findElements(By.xpath("//*[@class='task ']//a"));
        String actualPossibilityToBuildProject = "";
        for (WebElement button : projectActions) {
            if (button.getText().contains("Build Now")) {
                actualPossibilityToBuildProject = button.getText();
                break;
            }
        }
        Assert.assertEquals(actualPossibilityToBuildProject, "Build Now",
                "The 'Build Now' button is not displayed");
        Assert.assertEquals(getDriver().findElement(By.name("Submit")).getText(), "Disable Project");
    }
}
