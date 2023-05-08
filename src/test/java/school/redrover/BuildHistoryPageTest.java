package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class BuildHistoryPageTest extends BaseTest {
    @Test
    public void testNavigateToBuildHistoryPage() throws InterruptedException {

        final String expectedBuildHistoryPageUrl = "http://localhost:8080/view/all/builds";
        final String expectedBuildHistoryPageTitle = "All [Jenkins]";

        WebElement buildHistorySideMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/builds']"));
        buildHistorySideMenu.click();

        String actualBuildHistoryPageTitle = getDriver().getTitle();
        String actualBuildHistoryPageUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualBuildHistoryPageTitle, expectedBuildHistoryPageTitle);
        Assert.assertEquals(actualBuildHistoryPageUrl, expectedBuildHistoryPageUrl);
    }
}
