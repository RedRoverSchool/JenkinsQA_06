package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;

public class OrganizationFolderTest extends BaseTest {

    private static final By DASHBOARD = By.xpath("//a[contains(text(),'Dashboard')]");

    @Test
    public void testCreateOrganizationFolder() {
        final String expectedNewFolderName = "Project1";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='name']"))).sendKeys(expectedNewFolderName);
        getDriver().findElement(By.xpath("//li[@class='jenkins_branch_OrganizationFolder']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        getDriver().findElement(DASHBOARD).click();

        String actualNewFolderName = getDriver().findElement(By.xpath("//a[@href='job/Project1/']")).getText();

        Assert.assertEquals(actualNewFolderName, expectedNewFolderName);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {
        final String expectedRenamedFolderName = "Project";

        WebElement folderName = getDriver().findElement(By.xpath("//a[@href='job/Project1/']/button"));
        new Actions(getDriver()).moveToElement(folderName).perform();
        folderName.sendKeys(Keys.RETURN);
        getWait2().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//a[@href='/job/Project1/confirm-rename']"))).click();

        WebElement folderNameField = getDriver().findElement(By.xpath("//input[@name='newName']"));
        folderNameField.clear();
        folderNameField.sendKeys(expectedRenamedFolderName);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(DASHBOARD).click();

        String actualRenamedFolderName = getDriver().findElement(By.xpath("//a[@href='job/Project/']")).getText();

        Assert.assertEquals(actualRenamedFolderName, expectedRenamedFolderName);
    }

    @Test
    public void testCreateOrganizationFolderInFolder() {
        final String nameFolder = RandomStringUtils.randomAlphanumeric(8);
        final String nameOrganizationFolder = nameFolder + "Organization";

        WebElement createdOrganizationFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameFolder)
                .selectFolderAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickFolderName(nameFolder)
                .clickNewItem()
                .enterItemName(nameOrganizationFolder)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickFolderName(nameFolder)
                .getNestedFolder(nameOrganizationFolder);

        Assert.assertTrue(createdOrganizationFolder.isDisplayed());
    }

    @Test
    public void testMoveOrganizationFolderToFolderFromOrganizationFolderPage()  {
        final String folderName = "TestFolder";
        final String organizationFolderName = "TestOrgFolder";
        WebElement movedOrgFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(folderName)
                .selectFolderAndOk()
                .clickSaveButton()
                .clickNewItem()
                .enterItemName(organizationFolderName)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .clickMoveOnLeftMenu()
                .selectDestinationFolder()
                .clickMoveButtonOnOrgPage()
                .clickDashboard()
                .clickFolderName(folderName)
                .getNestedFolder(organizationFolderName);

        Assert.assertTrue(movedOrgFolder.isDisplayed());
        Assert.assertTrue(movedOrgFolder.isEnabled());
    }
}
