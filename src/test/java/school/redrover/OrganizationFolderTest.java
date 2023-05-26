package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class OrganizationFolderTest extends BaseTest {

    @Test
    public void testCreateOrganizationFolder() {
        final String expectedNewFolderName = "Project1";

        String actualNewFolderName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(expectedNewFolderName)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .clickDashboard()
                .getProjectNameMainPage(expectedNewFolderName);

        Assert.assertEquals(actualNewFolderName, expectedNewFolderName);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {
        final String expectedRenamedFolderName = "Project";

        String actualRenamedFolderName = new MainPage(getDriver())
                .navigateToProjectPage()
                .clickRename()
                .enterNewName(expectedRenamedFolderName)
                .submitNewName()
                .getNameProject()
                .getText();

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
    public void testMoveOrganizationFolderToFolderFromOrganizationFolderPage() {
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
