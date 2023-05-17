package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject8Test extends BaseTest {

    private final String descriptionText = "This is new description for the project";

    public void createNewProject () {
        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemButton.click();

        WebElement inputField = getDriver().findElement(By.id("name"));
        inputField.sendKeys("NewProject");

        WebElement jobType = getDriver()
                .findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]"));
        jobType.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//*[@name = 'Submit']"));
        saveButton.click();
    }

    @Test
    public void testVerifyProjectIsCreated () {
        createNewProject();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1"))
                .getText(), "Project NewProject");
    }

    @Test
    public void testAddDescription () {
        createNewProject();

        WebElement descriptionButton = getDriver().findElement(By.xpath("//*[@id = 'description-link']"));
        descriptionButton.click();

        WebElement descInputField = getDriver().findElement(By.xpath("//*[@name = 'description']"));
        descInputField.sendKeys(descriptionText);

        WebElement saveButton = getDriver()
                .findElement(By.xpath("//*[@id='description']/form/div[2]/button"));
        saveButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id = 'description']/div[1]"))
                .getText(),descriptionText );
    }

    @Test
    public void testPreviewDescription () {
        createNewProject();

        WebElement descriptionButton = getDriver().findElement(By.xpath("//*[@id = 'description-link']"));
        descriptionButton.click();

        WebElement descInputField = getDriver().findElement(By.xpath("//*[@name = 'description']"));
        descInputField.sendKeys(descriptionText);

        WebElement previewButton = getDriver()
                .findElement(By.xpath("//a[@class = 'textarea-show-preview']"));
        previewButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class = 'textarea-preview']"))
                .getText(), descriptionText);
    }

    @Test
    public void testEditDescription () {
        createNewProject();

        WebElement descriptionButton = getDriver().findElement(By.xpath("//*[@id = 'description-link']"));
        descriptionButton.click();

        WebElement descInputField = getDriver().findElement(By.xpath("//*[@name = 'description']"));
        descInputField.sendKeys(descriptionText);

        WebElement saveButton = getDriver()
                .findElement(By.xpath("//*[@id='description']/form/div[2]/button"));
        saveButton.click();

        WebElement editButton = getDriver().findElement(By.xpath("//*[@href = 'editDescription']"));
        editButton.click();

        WebElement oldDescription = getDriver().findElement(By.xpath("//*[@id='description']/form/div[1]/div[1]/textarea"));
        oldDescription.clear();
        oldDescription.sendKeys("Edit description");

        WebElement saveButton2 = getDriver()
                .findElement(By.xpath("//*[@id='description']/form/div[2]/button"));
        saveButton2.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id = 'description']/div[1]"))
                .getText(),"Edit description");
    }
}
