package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProjectVDTest extends BaseTest {

    private static final String PROJECT_NAME = "Tricky_Project";

    private static final By OK_BUTTON = By.xpath("//*[@name='Submit']");

    private static final By DISABLE_BUTTON = By.xpath("//*[@id='disable-project']/button[@name = 'Submit']");

    private static final By ENABLE_BUTTON = By.xpath("//*[@id='enable-project']/button[@name='Submit']");

    @Test
    public void testCreateProject() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id='name']"))).sendKeys(PROJECT_NAME);

        getDriver().findElement(By.xpath("//*[@class='hudson_matrix_MatrixProject']")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(OK_BUTTON)).click();

        WebElement expectedResult = getDriver().findElement(By.xpath("//*[@class='matrix-project-headline page-headline']"));
        Assert.assertEquals(expectedResult.getText(), "Project " + PROJECT_NAME);
    }

    @Test(dependsOnMethods = {"testCreateProject"})
    public void testAddDescription() {

        getDriver().findElement(By.xpath("//*[@class ='jenkins-table__link model-link inside']")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'configure')]"))).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='description']"))).sendKeys(PROJECT_NAME);
        getDriver().findElement(OK_BUTTON).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id='description']/div[1]"))).getText(), PROJECT_NAME);
    }

    @Test
    public void testDisableAndEnableProject() {

        TestUtils.createMultiConfigurationProject(this, PROJECT_NAME, true);

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class ='jenkins-table__link model-link inside']"))).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(DISABLE_BUTTON)).click();
        Assert.assertTrue(getWait2().until(ExpectedConditions.visibilityOfElementLocated(ENABLE_BUTTON)).isDisplayed());

        getWait2().until(ExpectedConditions.elementToBeClickable(ENABLE_BUTTON)).click();
        Assert.assertTrue(getWait2().until(ExpectedConditions.visibilityOfElementLocated(DISABLE_BUTTON)).isDisplayed());
    }
}


