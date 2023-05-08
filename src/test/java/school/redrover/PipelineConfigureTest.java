package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineConfigureTest extends BaseTest {

    public void createPipeline() {
        WebElement createJobButton = getDriver().findElement(By.xpath("//a[@href = 'newJob']"));
        createJobButton.click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys("test-pipeline");

        WebElement newPipeline = getDriver()
                .findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']//li[2]"));
        newPipeline.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
    }

    @Test
    public void testSetDescription() {
        String descriptionText = "This is a test description";

        createPipeline();

        WebElement descriptionField = getDriver().findElement(By.name("description"));
        descriptionField.sendKeys(descriptionText);

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();

        WebElement actualDescription = getDriver().findElement(By.xpath("//*[@id='description']/div"));

        Assert.assertEquals(actualDescription.getText(), descriptionText);
    }

    @Test
    public void testDiscardOldBuildsIsCheckedEmptyDaysAndBuildsField() {
        createPipeline();

        WebElement discardOldBuildsLabel = getDriver()
                .findElement(By.xpath("//label[contains(text(),'Discard old builds')]"));
        discardOldBuildsLabel.click();

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();

        WebElement configureMenu = getDriver()
                .findElement(By.xpath("//*[@href='/job/test-pipeline/configure']"));
        configureMenu.click();

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
    }

    @Test
    public void testDiscardOldBuildsIsChecked7DaysAnd5Builds() {
        final String days = "7";
        final String builds = "5";

        createPipeline();

        WebElement discardOldBuildsLabel = getDriver()
                .findElement(By.xpath("//label[contains(text(),'Discard old builds')]"));
        discardOldBuildsLabel.click();

        WebElement daysToKeepField = getDriver().findElement(By.name("_.daysToKeepStr"));
        daysToKeepField.sendKeys(days);

        WebElement buildsToKeepField = getDriver().findElement(By.name("_.numToKeepStr"));
        buildsToKeepField.sendKeys(builds);

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();

        WebElement configureMenu = getDriver()
                .findElement(By.xpath("//*[@href='/job/test-pipeline/configure']"));
        configureMenu.click();

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));
        WebElement daysToKeep = getDriver().findElement(By.name("_.daysToKeepStr"));
        WebElement buildsToKeep = getDriver().findElement(By.name("_.numToKeepStr"));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
        Assert.assertEquals(daysToKeep.getAttribute("value"), days);
        Assert.assertEquals(buildsToKeep.getAttribute("value"), builds);
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Days() {
        final String days = "0";
        final String errorMessage = "Not a positive integer";

        createPipeline();

        WebElement discardOldBuildsLabel = getDriver()
                .findElement(By.xpath("//label[contains(text(),'Discard old builds')]"));
        discardOldBuildsLabel.click();

        WebElement daysToKeepField = getDriver().findElement(By.name("_.daysToKeepStr"));
        daysToKeepField.sendKeys(days);

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        WebElement clickOutsideOfInputField = getDriver()
                .findElement(By.xpath("//*[@name='strategy']/div/div"));
        clickOutsideOfInputField.click();

        WebElement actualErrorMessage = getDriver()
                .findElement(By.xpath("//*[@name='strategy']//div[@class='error']"));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
        Assert.assertEquals(actualErrorMessage.getText(), errorMessage);
    }

    @Test
    public void testDiscardOldBuildsIsChecked0Builds() {
        final String builds = "0";
        final String errorMessage = "Not a positive integer";

        createPipeline();

        WebElement discardOldBuildsLabel = getDriver()
                .findElement(By.xpath("//label[contains(text(),'Discard old builds')]"));
        discardOldBuildsLabel.click();

        WebElement buildsToKeepField = getDriver().findElement(By.name("_.numToKeepStr"));
        buildsToKeepField.sendKeys(builds);

        WebElement discardOldBuildsCheckbox = getDriver().findElement(By.id("cb2"));

        WebElement clickOutsideOfInputField = getDriver()
                .findElement(By.xpath("//*[@name='strategy']/div/div"));
        clickOutsideOfInputField.click();

        WebElement actualErrorMessage = getDriver()
                .findElement(By.xpath("//*[@name='strategy']//div[@class='error']"));

        Assert.assertTrue(discardOldBuildsCheckbox.isSelected());
        Assert.assertEquals(actualErrorMessage.getText(), errorMessage);
    }
}
