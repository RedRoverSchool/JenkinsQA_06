package school.redrover;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
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
        final String nameFolder = "nameFolder";
        final String nameOrganizationFolder = nameFolder + "Organization";

        WebElement createdOrganizationFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameFolder)
                .selectFolderAndOk()
                .getConfig()
                .clickSaveButton(new FolderPage(getDriver()))
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

        boolean movedOrgFolderVisibleAndClickable = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(folderName)
                .selectFolderAndOk()
                .getConfig()
                .clickSaveButton(new FolderPage(getDriver()))
                .clickNewItem()
                .enterItemName(organizationFolderName)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .clickMoveOnLeftMenu()
                .selectDestinationFolder()
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName(folderName)
                .nestedFolderIsVisibleAndClickable(organizationFolderName);

        Assert.assertTrue(movedOrgFolderVisibleAndClickable);
    }
}
