package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.FolderPage;
import school.redrover.model.jobs.OrganizationFolderPage;
import school.redrover.model.jobsconfig.FolderConfigPage;
import school.redrover.model.jobsconfig.OrganizationFolderConfigPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;

public class OrganizationFolderTest extends BaseTest {

    private static final String ORGANIZATION_FOLDER_NAME = "OrgFolder";
    private static final String ORGANIZATION_FOLDER_RENAMED = "OrgFolderNew";

    @Test
    public void testCreateOrganizationFolder() {

        String actualNewFolderName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(ORGANIZATION_FOLDER_NAME)
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .clickOkButton(new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
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
                .getJobName();

        Assert.assertEquals(actualRenamedFolderName, ORGANIZATION_FOLDER_RENAMED);
    }

    @Test(dependsOnMethods = {"testCreateOrganizationFolder", "testRenameOrganizationFolder"})
    public void testMoveOrganizationFolderToFolderFromOrganizationFolderPage() {

        final String folderName = "TestFolder";

        boolean movedOrgFolderVisibleAndClickable = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(folderName)
                .selectJobType(TestUtils.JobType.Folder)
                .clickOkButton(new FolderConfigPage(new FolderPage(getDriver())))
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
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .clickOkButton(new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
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
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .clickOkButton(new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
                .addDescription("Description")
                .clickSaveButton()
                .getAddedDescriptionFromConfig();

        Assert.assertEquals(textFromDescription, "Description");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testDisabledOrganizationFolder() {

        String disabledText = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
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
                .getJobName();

        Assert.assertEquals(actualRenamedName, ORGANIZATION_FOLDER_RENAMED);
    }

    @Test(dependsOnMethods = {"testRenameFromDropDownMenu"} )
    public void testRenameNegative() {
        String errorMessage = new MainPage(getDriver())
                .dropDownMenuClickRename(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButtonAndGoError()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = {"testRenameNegative"} )
    public void testDeleteOrganizationFolder() {
        String welcomeText = new CreateItemErrorPage(getDriver())
                .getHeader()
                .clickLogo()
                .clickJobName(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .clickDeleteJobLocatedOnMainPage()
                .clickYesButton()
                .getWelcomeText();

        Assert.assertEquals(welcomeText, "Welcome to Jenkins!");
    }

    @Test
    public void testCreateOrganizationFolderGoingFromManageJenkinsPage() {
        List<String> organizationFolderName = new MainPage(getDriver())
                .clickManageJenkinsPage()
                .clickNewItem()
                .enterItemName(ORGANIZATION_FOLDER_NAME)
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .clickOkButton(new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
                .clickSaveButton()
                .getBreadcrumb()
                .clickDashboardButton()
                .getJobList();

        Assert.assertTrue(organizationFolderName.contains(ORGANIZATION_FOLDER_NAME));
    }
}
