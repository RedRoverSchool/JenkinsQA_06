package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreestyleProject4Test extends BaseTest {

    private final String nameOfProject = "NewFreestyleProject";
    private final String expectedBuildStatus = "Success > Console Output";

    @Test
    public void testCreateFreestyleProject() {
        String nameOfProject = "NewFreestyleProject";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@id='items']"))));
        getDriver().findElement(By.cssSelector("#name")).sendKeys(nameOfProject);

        getDriver().findElement(By.xpath("//span[.='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.cssSelector("[name='Submit']")).click();
        getDriver().findElement(By.cssSelector("ol#breadcrumbs>li:first-child .model-link")).click();

        WebElement projectName = getDriver().findElement(By.cssSelector(".job-status-nobuilt td>a>span"));
        Assert.assertEquals(projectName.getText(), nameOfProject);
    }

    @Test(dependsOnMethods = "testCreateFreestyleProject")
    public void testDisableFreestyleProject() {

        getDriver().findElement(By.cssSelector(".job-status-nobuilt td>a>span")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement((By.cssSelector("#disable-project>.jenkins-button"))))).click();

        String disabledProject = getDriver().findElement(By.cssSelector("#enable-project")).getText();

        Assert.assertTrue(getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate']")).isDisplayed());
        Assert.assertEquals(disabledProject.substring(0, disabledProject.indexOf("\n")), "This project is currently disabled");
    }

    @Test
    public void testBuildNowProject() {
        TestUtils.createFreestyleProject(this, nameOfProject, true);

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//a[@href = 'job/"+ nameOfProject + "/']")))
                .click()
                .perform();

        getDriver().findElement(By.xpath("//span[text() = 'Build Now']/ancestor::a")).click();

        new Actions(getDriver())
                .moveToElement(getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='build-status-link']"))))
                .perform();

        String actualBuildStatus = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Success')]"))).getText();
        Assert.assertEquals(actualBuildStatus, expectedBuildStatus);
    }

    @Ignore
    @Test(dependsOnMethods = "testBuildNowProject")
    public void testBuildNowProjectWithBooleanParameter() {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//a[@href = 'job/"+ nameOfProject + "/']")))
                .click()
                .perform();

        getDriver().findElement(By.xpath("//span[text()='Configure']/ancestor::a")).click();

        new Actions(getDriver())
                .scrollToElement(getDriver().findElement(By.xpath("//div[@id='source-code-management']")))
                .click(getDriver().findElement(By.xpath("//div[@ref='cb6']//span")))
                .perform();

        getDriver().findElement(By.xpath("//button[contains(text(), 'Parameter')]")).click();

        new Actions(getDriver())
                .moveToElement(getDriver().findElement(By.xpath("//a[contains(text(), 'Boolean')]")))
                .click()
                .perform();

        getDriver().findElement(By.xpath("//input[@name='parameter.name']")).sendKeys("BooleanParameter");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getWait2().until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//span[contains(text(), 'with Parameters')]/ancestor::a"))).click();

        getDriver().findElement(By.xpath("//span[@class='jenkins-checkbox']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        new Actions(getDriver())
                .moveToElement(getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='build-status-link']"))))
                .perform();
        boolean actualBuildStatus = getWait5().until(ExpectedConditions.attributeToBe(By.xpath("//div[@class='tippy-content']"), "innerText", expectedBuildStatus));
        Assert.assertTrue(actualBuildStatus, "Build is not success");
    }
}
