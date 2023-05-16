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

    @Test
    public void testCreateProject() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[@id='name']")))
                .sendKeys(PROJECT_NAME);

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

        getWait2().until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[@name='description']")))
                .sendKeys(PROJECT_NAME);

        getDriver().findElement(OK_BUTTON).click();
        Assert.assertEquals(getWait2()
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[@id='description']/div[1]"))).getText(), PROJECT_NAME);
    }
}
