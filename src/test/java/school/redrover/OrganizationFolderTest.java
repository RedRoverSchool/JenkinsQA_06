package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.model.OrganizationFolderPage;
import school.redrover.runner.BaseTest;

public class OrganizationFolderTest extends BaseTest {

    private static final String ORGANIZATION_FOLDER_NAME = "OrgFolder";
    private static final String ORGANIZATION_FOLDER_RENAMED = "OrgFolderNew";

    @Test
    public void testCreateOrganizationFolder() {

        String actualNewFolderName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(ORGANIZATION_FOLDER_NAME)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .getProjectNameMainPage(ORGANIZATION_FOLDER_NAME);

        Assert.assertEquals(actualNewFolderName, ORGANIZATION_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {

        String actualRenamedFolderName = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickRename()
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButton()
                .getProjectName();

        Assert.assertEquals(actualRenamedFolderName, ORGANIZATION_FOLDER_RENAMED);
    }

    @Test(dependsOnMethods = {"testCreateOrganizationFolder", "testRenameOrganizationFolder"})
    public void testMoveOrganizationFolderToFolderFromOrganizationFolderPage() {

        final String folderName = "TestFolder";

        boolean movedOrgFolderVisibleAndClickable = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(folderName)
                .selectFolderAndOk()
                .clickSaveButton()
                .getHeader()
                .clickLogo()
                .dropDownMenuClickMove(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .selectDestinationFolder(folderName)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickJobName(folderName, new FolderPage(getDriver()))
                .nestedFolderIsVisibleAndClickable(ORGANIZATION_FOLDER_RENAMED);

        Assert.assertTrue(movedOrgFolderVisibleAndClickable);
    }

    @Test
    public void testCreateDisableOrganizationFolder() {

        String disableFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(ORGANIZATION_FOLDER_NAME)
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
                .enterItemName(ORGANIZATION_FOLDER_NAME)
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
        String actualRenamedName = new MainPage(getDriver())
                .dropDownMenuClickRename(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButton()
                .getProjectName();

        Assert.assertEquals(actualRenamedName, ORGANIZATION_FOLDER_RENAMED);
    }

    @Test(dependsOnMethods = {"testCreateOrganizationFolderWithDescription", "testRenameFromDropDownMenu"} )
    public void testRenameNegative() {
        String errorMessage = new MainPage(getDriver())
                .dropDownMenuClickRename(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButtonAndGoError()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "The new name is the same as the current name.");
    }
}
