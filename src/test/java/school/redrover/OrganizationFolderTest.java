package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
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
        final String originalNewFolderName = "Project1";
        final String expectedRenamedFolderName = "Project";

        String actualRenamedFolderName = new MainPage(getDriver())
                .clickMultiConfigurationProjectName(originalNewFolderName)
                .clickRename()
                .enterNewName(expectedRenamedFolderName)
                .submitNewName()
                .getMultiProjectName()
                .getText();

        Assert.assertEquals(actualRenamedFolderName, expectedRenamedFolderName);
    }

    @Test
    public void testMoveOrganizationFolderToFolderFromOrganizationFolderPage() {

        final String folderName = "TestFolder";
        final String organizationFolderName = "TestOrgFolder";

        boolean movedOrgFolderVisibleAndClickable = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(folderName)
                .selectFolderAndOk()
                .clickSaveButton()
                .clickNewItem()
                .enterItemName(organizationFolderName)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .clickMoveOnLeftMenu()
                .selectDestinationFolder(folderName)
                .clickMoveButton()
                .clickDashboard()
                .clickFolderName(folderName)
                .nestedFolderIsVisibleAndClickable(organizationFolderName);

        Assert.assertTrue(movedOrgFolderVisibleAndClickable);
    }

    @Test
    public void testCreateDisableOrganizationFolder() {
        final String RandomName = RandomStringUtils.randomAlphanumeric(5);

        String disableFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(RandomName)
                .selectOrganizationFolderAndOk()
                .clickDisable()
                .clickSaveButton()
                .getTextFromDisableMessage();

        Assert.assertEquals(disableFolder.trim().substring(0,46),"This Organization Folder is currently disabled");
    }

    @Test
    public  void testAddDescription(){
        final String RandomName = RandomStringUtils.randomAlphanumeric(5);

        String textFromDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(RandomName)
                .selectOrganizationFolderAndOk()
                .selectDescription("Description")
                .clickSaveButton()
                .getTextFromDescription();

        Assert.assertEquals(textFromDescription,"Description");
    }
}
