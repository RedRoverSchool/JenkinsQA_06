package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class ManageJenkins2Test extends BaseTest {
    public static final int QNT_EXECUTORS = 3;

    @Test
    public void testConfigureNumOfExecutorsInMasterNode() {
        WebElement manageJenkinsTab = getDriver().findElement(By.xpath("//a[@href = '/manage']"));
        manageJenkinsTab.click();

        WebElement configureSystemSection = getDriver().findElement(By.xpath("//a[@href = 'configure']"));
        configureSystemSection.click();

        WebElement numOfExecutorsInput = getDriver().findElement(By.xpath("//input[@name = '_.numExecutors']"));
        numOfExecutorsInput.clear();
        numOfExecutorsInput.sendKeys(String.valueOf(QNT_EXECUTORS));

        WebElement saveBtn = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        saveBtn.click();

        WebElement dashBoardBreadcrumb = getDriver().findElement(By.xpath("//a[@href = '/'][1]"));
        getWait5().until(ExpectedConditions.elementToBeClickable(dashBoardBreadcrumb)).click();

        List<WebElement> executors = getDriver().findElements(By.xpath("//td[@class = 'pane'][2]"));
        Assert.assertEquals(executors.size(), QNT_EXECUTORS);
    }
}
