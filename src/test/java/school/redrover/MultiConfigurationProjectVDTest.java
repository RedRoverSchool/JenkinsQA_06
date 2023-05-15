package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class MultiConfigurationProjectVDTest extends BaseTest {
    private static final String PROJECT_NAME = "Tricky Project";

    @Test
    public void testCreateProject() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[@id='name']")))
                .sendKeys(PROJECT_NAME);

        getDriver().findElement(By.xpath("//*[@class='hudson_matrix_MatrixProject']")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='Submit']"))).click();

        WebElement expectedResult = getDriver().findElement(By.xpath("//*[@class='matrix-project-headline page-headline']"));
        Assert.assertEquals(expectedResult.getText(), "Project " + PROJECT_NAME);
    }
}
