package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class FreestyleProject14Test extends BaseTest {
    private final static String PROJECT_NAME = "First-Project-Name";

    @Test
    public void testBuildIsDisplayedOnTheBuildHistoryPageTable() {
        createFreestyleProjectBuild(2, PROJECT_NAME);

        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();

        Assert.assertTrue(getWait2().until(ExpectedConditions
                .visibilityOfElementLocated(By.id("main-panel"))).getText().contains("Build History of Jenkins"));

        Assert.assertTrue(isTableContainsBuild(PROJECT_NAME, "#2"));
    }

    public boolean isTableContainsBuild(String projectName, String ordinal_number) {
        WebElement table = getDriver().findElement(By.id("projectStatus"));
        List<WebElement> tableCells = table.findElements(By.xpath("./tbody/tr/td"));
        List<WebElement> specificBuilds = getDriver().findElements(By
                .xpath("//td/a[contains(@href,'" + PROJECT_NAME + "')]/span"));
        String projectOrdinalBuildNumber = "#" + specificBuilds.size();
        for(WebElement tableCell : tableCells) {
            if (tableCell.getText().contains(projectName) && tableCell.getText().contains(projectOrdinalBuildNumber)
                    && projectOrdinalBuildNumber.equals(ordinal_number)) {
                return true;
            }
        }
        return false;
    }

        public void createFreestyleProjectBuild(int numberOfBuilds, String name) {
           TestUtils.createFreestyleProject(this, name, true);
           for (int i = 0; i < numberOfBuilds; i++ ) {
               new Actions(getDriver()).moveToElement(getDriver().findElement(By
                   .xpath("//a[@href='job/" + name + "/']"))).perform();
               getDriver().findElement(By.xpath("//a[@href='job/" + name + "/']/button"))
                   .sendKeys(Keys.RETURN);

               getWait2().until(ExpectedConditions.elementToBeClickable(By.id("yui-gen3"))).click();
               getDriver().findElement(By.linkText("Dashboard")).click();
       }
    }
}