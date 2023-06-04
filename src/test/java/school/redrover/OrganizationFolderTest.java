package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.MainPage;
import school.redrover.model.OrganizationFolderPage;
import school.redrover.runner.BaseTest;

public class OrganizationFolderTest extends BaseTest {

    private static final String organizationFolderName = "OrgFolder";
    private static final String organizationFolderRenamed = "OrgFolderRenamed";

    @Test
    public void testCreateOrganizationFolder() {

        String actualNewFolderName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(organizationFolderName)
                .selectOrganizationFolderAndOk()
                .clickSaveButton()
                .clickDashboard()
                .getProjectNameMainPage(organizationFolderName);

        Assert.assertEquals(actualNewFolderName, organizationFolderName);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {

        String actualRenamedFolderName = new MainPage(getDriver())
                .clickMultiConfigurationProjectName(organizationFolderName)
                .clickRename()
                .enterNewName(organizationFolderRenamed)
                .submitNewName()
                .getMultiProjectName()
                .getText();

        Assert.assertEquals(actualRenamedFolderName, organizationFolderRenamed);
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
                .dropDownMenuClickMove(organizationFolderRenamed, new OrganizationFolderPage(getDriver()))
                .selectDestinationFolder(folderName)
                .clickMoveButton()
                .getHeader()
                .clickLogo()
                .clickFolderName(folderName)
                .nestedFolderIsVisibleAndClickable(organizationFolderRenamed);

        Assert.assertTrue(movedOrgFolderVisibleAndClickable);
    }

    @Test
    public void testCreateDisableOrganizationFolder() {

        String disableFolder = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(organizationFolderName)
                .selectOrganizationFolderAndOk()
                .clickDisable()
                .clickSaveButton()
                .getTextFromDisableMessage();

        Assert.assertEquals(disableFolder.trim().substring(0,46),"This Organization Folder is currently disabled");
    }

    @Test
    public  void testCreateOrganizationFolderWithDescription(){

        String textFromDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(organizationFolderName)
                .selectOrganizationFolderAndOk()
                .selectDescription("Description")
                .clickSaveButton()
                .getTextFromDescription();

        Assert.assertEquals(textFromDescription,"Description");
    }
}
