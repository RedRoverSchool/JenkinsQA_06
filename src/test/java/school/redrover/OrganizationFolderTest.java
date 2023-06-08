package school.redrover;

import net.bytebuddy.implementation.bytecode.Throw;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.FolderPage;
import school.redrover.model.MainPage;
import school.redrover.model.MultiConfigurationProjectPage;
import school.redrover.model.OrganizationFolderPage;
import school.redrover.runner.BaseTest;

import java.util.Map;

public class OrganizationFolderTest extends BaseTest {

    private static final String ORGANIZATION_FOLDER_NAME = "OrgFolder";
    private static final String ORGANIZATION_FOLDER_RENAMED = "OrgFolderRenamed";

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
                .clickJobName(ORGANIZATION_FOLDER_NAME, new MultiConfigurationProjectPage(getDriver()))
                .clickRename()
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .submitNewName()
                .getName();

        Assert.assertEquals(actualRenamedFolderName, ORGANIZATION_FOLDER_RENAMED);
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

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testDeleteOrganizationFolder() {

        String projectDeleted = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(ORGANIZATION_FOLDER_NAME)
                .clickYes()
                .getWelcomeWebElement()
                .getText();

        Assert.assertEquals(projectDeleted,"Welcome to Jenkins!");
    }
}
