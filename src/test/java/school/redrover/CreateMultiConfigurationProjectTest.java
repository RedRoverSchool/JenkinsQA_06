package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class CreateMultiConfigurationProjectTest extends BaseTest {
    @Test
    public void testMultiConfigurationProject() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a"));
        newItem.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//input[@id='name']"))))
                .sendKeys("My Multi configuration project");

        WebElement MultiProject = getDriver().findElement(By.xpath("//span[text() = 'Multi-configuration project']"));
        MultiProject.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement buttonSave = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        buttonSave.click();

        WebElement nameProject = getDriver().findElement(By.xpath("//h1[text() = 'Project My Multi configuration project']"));

        Assert.assertEquals(nameProject.getText(), "Project My Multi configuration project");
    }
}
