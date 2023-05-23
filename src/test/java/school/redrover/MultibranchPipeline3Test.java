package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class MultibranchPipeline3Test extends BaseTest {
    @Ignore
    @Test
    public void testMoveMultibranchPipelineToFolder() {
        TestUtils.createFolder(this,"Folder1",true);
        TestUtils.createMultibranchPipeline(this,"MultibranchPipeline1", false);
        getDriver().findElement(By.linkText("Move")).click();
        WebElement folderList = getDriver().findElement(By.xpath("//select[@name='destination']"));
        getWait2().until(ExpectedConditions.elementToBeClickable(folderList)).click();
        getDriver().findElement(By.xpath("//select/option[contains(text(),'Folder')]")).click();
        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();

        String expectedMultibranchPipeline = "MultibranchPipeline1";
        WebElement actualMultibranchPipeline = getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals(actualMultibranchPipeline.getText(), expectedMultibranchPipeline);

        new Actions(getDriver())
                .click(getDriver().findElement(By.linkText("Folder1")))
                .perform();
        WebElement actualMultibranchPipeline2 = getDriver().findElement(By.linkText("MultibranchPipeline1"));

        Assert.assertEquals(actualMultibranchPipeline2.getText(), expectedMultibranchPipeline);
    }

    @Test
    public void testMoveMultibranchPipelineToFolderByDrop() {
        TestUtils.createFolder(this,"Folder1",true);
        TestUtils.createMultibranchPipeline(this,"MultibranchPipeline1", true);

        new Actions(getDriver()).
                moveToElement(getDriver().findElement(By.xpath("//span[text() = 'MultibranchPipeline1']")))
                .perform();
        getDriver().findElement(By.xpath("//a[@href='job/MultibranchPipeline1/']/button[@class = 'jenkins-menu-dropdown-chevron']")).sendKeys(Keys.RETURN);
        getDriver().findElement(By.xpath("//a[@href='/job/MultibranchPipeline1/move']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div [@id = 'main-panel']")));
        WebElement folderList = getDriver().findElement(By.xpath("//select[@name='destination']"));
        getWait2().until(ExpectedConditions.elementToBeClickable(folderList)).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//select/option[contains(text(),'Folder')]"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']"))).click();
        String expectedMultibranchPipeline = "MultibranchPipeline1";
        WebElement actualMultibranchPipeline = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        Assert.assertEquals(actualMultibranchPipeline.getText(), expectedMultibranchPipeline);

        new Actions(getDriver())
                .click(getDriver().findElement(By.linkText("Folder1")))
                .perform();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        WebElement actualMultibranchPipeline2 = getDriver().findElement(By.linkText("MultibranchPipeline1"));

        Assert.assertEquals(actualMultibranchPipeline2.getText(), expectedMultibranchPipeline);
    }

    @Test
    public void testCreateMultibranchPipelineWithoutDescription() {
        TestUtils.createMultibranchPipeline(this,"MyMultibranchPipeline", false);

        getWait2().until(ExpectedConditions
                .textToBePresentInElement(getDriver().findElement(By.xpath("//h1")), "MyMultibranchPipeline"));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "MyMultibranchPipeline");
    }

    @Test
    public void testCreateNewItemWithNullName() {
        final String expectedErrorMessage = "» This field cannot be empty, please enter a valid name";

        WebElement buttonCreateItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonCreateItem));
        buttonCreateItem.click();

        WebElement fieldInputName = getDriver().findElement(By.xpath("//input[@id='name']"));
        getWait5().until(ExpectedConditions.elementToBeClickable(fieldInputName));
        fieldInputName.click();

        WebElement folderButton = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        new Actions(getDriver())
                .scrollToElement(folderButton)
                .perform();
        folderButton.click();

        WebElement multibranchPipelineButton = getDriver().findElement(By.xpath("//span[text() = 'Multibranch Pipeline']"));
        new Actions(getDriver())
                .scrollToElement(multibranchPipelineButton)
                .perform();
        getWait5().until(ExpectedConditions.elementToBeClickable(multibranchPipelineButton));
        multibranchPipelineButton.click();

        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@id = 'itemname-required']"));

        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage);
    }
}
