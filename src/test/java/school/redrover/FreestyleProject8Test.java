package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @Test
    public void testRenameProjectFromProjectPage () {
        createNewProject();

        WebElement renameProjectButton = getDriver()
                .findElement(By.xpath("//*[@href = '/job/NewProject/confirm-rename']"));
        renameProjectButton.click();

        WebElement newNameInputField = getDriver().findElement(By.xpath("//*[@name = 'newName']"));
        newNameInputField.clear();
        newNameInputField.sendKeys("OldProject");

        WebElement renameButton = getDriver().findElement(By.xpath("//*[@name = 'Submit']"));
        renameButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Project OldProject");
    }

    @Test
    public void testRenameProjectFromDashboardList () {
        createNewProject();

        WebElement dashboardButton = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        dashboardButton.click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//*[@id='job_NewProject']/td[3]/a")))
                .moveToElement(getDriver().findElement(By.xpath("//*[@id='job_NewProject']/td[3]/a/button")))
                .click()
                .perform();

        getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[@href = '/job/NewProject/confirm-rename']"))).click();

        WebElement newNameInputField = getDriver().findElement(By.xpath("//*[@name = 'newName']"));
        newNameInputField.clear();
        newNameInputField.sendKeys("OldProject");

        WebElement renameButton = getDriver().findElement(By.xpath("//*[@name = 'Submit']"));
        renameButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1"))
                .getText(), "Project OldProject");
    }

    @Test (dependsOnMethods = "testRenameProjectFromProjectPage")
    public void testVerifyNameOnDashboardPageAfterRenameFromProjectPage () {
        WebElement dashboardButton = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        dashboardButton.click();

        WebElement projectName = getDriver().findElement(By.xpath("//*[@href = 'job/OldProject/']"));

        Assert.assertEquals(projectName.getText(), "OldProject");
    }

    @Test(dependsOnMethods = "testRenameProjectFromDashboardList")
    public void testVerifyNameOnDashboardPageAfterRenameFromDashboardList () {
        WebElement dashboardButton = getDriver().findElement(By.xpath("//a[@href='/'][@class='model-link']"));
        dashboardButton.click();

        WebElement projectName = getDriver().findElement(By.xpath("//*[@href = 'job/OldProject/']"));

        Assert.assertEquals(projectName.getText(), "OldProject");
    }
}
