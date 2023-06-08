package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.OrganizationFolderPage;
import school.redrover.runner.BaseTest;

public class OrganizationFolderTest extends BaseTest {

    private static final String NAME = "OrgFolder";
    private static final String NEW_NAME = "OrgFolderRenamed";

    @Test
    public void testCreateOrganizationFolder() {

        String actualNewFolderName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectNameMainPage(NAME);

        Assert.assertEquals(actualNewFolderName, NAME);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {

        String actualRenamedFolderName = new MainPage(getDriver())
                .clickMultiConfigurationProjectName(NAME)
                .clickRename()
                .enterNewName(NEW_NAME)
                .clickRenameButton()
                .getName();

        Assert.assertEquals(actualRenamedFolderName, NEW_NAME);
    }

    @Test(dependsOnMethods = "testRenameOrganizationFolder")
    public void testMoveOrganizationFolderToFolderFromOrganizationFolderPage() {

        final String folderName = "TestFolder";

        boolean movedOrgFolderVisibleAndClickable = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(folderName)
                .selectFolderAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickMove(NEW_NAME, new OrganizationFolderPage(getDriver()))
                .selectDestinationFolder(folderName)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(folderName)
                .nestedFolderIsVisibleAndClickable(NEW_NAME);

        Assert.assertTrue(movedOrgFolderVisibleAndClickable);
    }

    @Test
    public void testCreateDisableOrganizationFolder() {

        String disableFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectOrganizationFolderAndOk()
                .clickDisable()
                .clickSaveButton()
                .getTextFromDisableMessage();

        Assert.assertEquals(disableFolder.trim().substring(0, 46), "This Organization Folder is currently disabled");
    }

    @Test
    public void testCreateOrganizationFolderWithDescription() {

        String textFromDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(NAME)
                .selectOrganizationFolderAndOk()
                .addDescription("Description")
                .clickSaveButton()
                .getTextFromDescription();

        Assert.assertEquals(textFromDescription, "Description");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testDisabledOrganizationFolder() {

        String disabledText = new MainPage(getDriver())
                .clickJodOrganizationFolder()
                .clickDisableButton()
                .getTextFromDisableMessage();

        Assert.assertEquals(disabledText.substring(0,46),"This Organization Folder is currently disabled");
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolderWithDescription")
    public void testRenameFromDropDownMenu() {

        String actualResult = new MainPage(getDriver())
                .dropDownMenuClickRename(NAME, new OrganizationFolderPage(getDriver()))
                .enterNewName(NEW_NAME)
                .clickRenameButton()
                .getName();

        Assert.assertEquals(actualResult, NEW_NAME);
    }
}
