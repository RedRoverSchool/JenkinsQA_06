package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultibranchPipeline4Test extends BaseTest {

    @Test
    public void testRenameMultibranchPipeline(){
        String newName = "TestAfterRenameMultibranchPipeline";

        TestUtils.createMultibranchPipeline(this, "TestRenameMultibranchPipeline", true);

        WebElement clickOnProjectName = getDriver().findElement(By.cssSelector(".jenkins-table__link.model-link.inside"));
        clickOnProjectName.click();

        WebElement renameButton = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='tasks']/div[8]")));
        renameButton.click();

        WebElement nameInputField = getDriver().findElement(By.name("newName"));
        nameInputField.clear();
        nameInputField.sendKeys(newName);
        nameInputField.sendKeys(Keys.ENTER);

        WebElement getTitle = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(getTitle.getText(), newName);
    }
}
