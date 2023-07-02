package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.model.jobs.OrganizationFolderPage;
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
                .getJobName(ORGANIZATION_FOLDER_NAME);

        Assert.assertEquals(actualNewFolderName, ORGANIZATION_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testDisabledOrganizationFolder() {
        String disabledText = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .clickDisableEnable()
                .clickSaveButton()
                .getTextFromDisableMessage();

        Assert.assertTrue(disabledText.contains("This Organization Folder is currently disabled"));
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolderGoingFromManageJenkinsPage")
    public void testCreateWithExistingName() {
        CreateItemErrorPage errorPage =
                TestUtils.createJobWithExistingName(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "A job already exists with the name ‘" + ORGANIZATION_FOLDER_NAME + "’");
    }

    @Test(dependsOnMethods = "testDeleteDisplayName")
    public void testRenameOrganizationFolderFromSideMenu() {
        String actualRenamedFolderName = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickRename()
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(actualRenamedFolderName, ORGANIZATION_FOLDER_RENAMED);
    }

    @Test(dependsOnMethods = "testDisabledOrganizationFolder")
    public void testEnableOrgFolderFromConfig() {
        String enableOrgFolder = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .clickDisableEnable()
                .clickSaveButton()
                .getDisableButtonText();

        Assert.assertEquals(enableOrgFolder.trim(), "Disable Organization Folder");
    }

    @Test(dependsOnMethods = "testEnableOrgFolderFromConfig")
    public void testDisableOrgFolderFromProjectPage() {
        String disabledText = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickDisableEnableButton()
                .getTextFromDisableMessage();

        Assert.assertEquals(disabledText.substring(0, 46), "This Organization Folder is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableOrgFolderFromProjectPage")
    public void testEnableOrgFolderFromProjectPage() {
        String disableButton = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickDisableEnableButton()
                .getDisableButtonText();

        boolean iconOrgFolder = new OrganizationFolderPage(getDriver())
                .isMetadataFolderIconDisplayed();

        Assert.assertEquals(disableButton, "Disable Organization Folder");
        Assert.assertTrue(iconOrgFolder, "the dispayеd icon OrganizationFolder exists");
    }

    @Test(dependsOnMethods = "testCreateWithExistingName")
    public void testAddDisplayName() {
        final String displayName = "This is Display Name of Folder";

        OrganizationFolderPage orgFolderPage = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .enterDisplayName(displayName)
                .clickSaveButton();

        Assert.assertEquals(orgFolderPage.getJobName(), displayName);
        Assert.assertEquals(orgFolderPage.getHeader().clickLogo().getJobName(ORGANIZATION_FOLDER_NAME), displayName);
    }

    @Test(dependsOnMethods = "testAddDisplayName")
    public void testDeleteDisplayName() {
        String orgFolderName = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .clearDisplayName()
                .clickSaveButton()
                .getJobName();

        Assert.assertEquals(orgFolderName, ORGANIZATION_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testEnableOrgFolderFromProjectPage")
    public void testAddDescriptionToProject() {
        String textFromDescription = new MainPage(getDriver())
                .clickConfigureDropDown(ORGANIZATION_FOLDER_NAME, new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
                .addDescription("Description")
                .clickSaveButton()
                .getAddedDescriptionFromConfig();

        Assert.assertEquals(textFromDescription, "Description");
    }

    @Test(dependsOnMethods = "testAddDescriptionToProject")
    public void testRenameFromDropDownMenu() {
        String actualRenamedName = new MainPage(getDriver())
                .dropDownMenuClickRename(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(actualRenamedName, ORGANIZATION_FOLDER_RENAMED);
    }

    @Test(dependsOnMethods = {"testRenameFromDropDownMenu"})
    public void testRenameToTheCurrentNameAndGetError() {
        String errorMessage = new MainPage(getDriver())
                .dropDownMenuClickRename(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButtonAndGoError()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = {"testRenameToTheCurrentNameAndGetError"})
    public void testDeleteOrgFolderFromSideMenu() {
        String welcomeText = new MainPage(getDriver())
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

    @Test
    public void testCreateWithEmptyName() {
        final String expectedError = "» This field cannot be empty, please enter a valid name";

        String actualError = new MainPage(getDriver())
                .clickCreateAJobArrow()
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .getItemNameRequiredErrorText();

        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void testCreateOrganizationFolderWithSpaceName() {
        CreateItemErrorPage errorPage =
                TestUtils.createJobWithSpaceInsteadName(this, TestUtils.JobType.OrganizationFolder);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "No name is specified");
    }

    @Test
    public void testOrganizationFolderConfigPreviewDescription() {
        String previewText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(ORGANIZATION_FOLDER_NAME)
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .clickOkButton(new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
                .addDescription("Description")
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, "Description");
    }

    @DataProvider(name = "wrong-character")
    public Object[][] provideWrongCharacters() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}, {"|"}, {">"}, {"["}, {"]"}};
    }

    @Test(dataProvider = "wrong-character")
    public void testCreateUsingInvalidData(String wrongCharacter) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(wrongCharacter)
                .selectJobType(TestUtils.JobType.OrganizationFolder);

        Assert.assertTrue(newJobPage.isOkButtonDisabled(), "Save button is enabled");
        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacter + "’ is an unsafe character");
    }

    @Test
    public void testCreateFromCreateAJob() {
        MainPage mainPage = new MainPage(getDriver())
                .clickCreateAJob()
                .enterItemName(ORGANIZATION_FOLDER_NAME)
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .clickOkButton(new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
                .getHeader()
                .clickLogo();

        Assert.assertTrue(mainPage.projectStatusTableIsDisplayed());
        Assert.assertEquals(mainPage.getJobName(ORGANIZATION_FOLDER_NAME), ORGANIZATION_FOLDER_NAME);
    }

    @Test
    public void testAppearanceIconHasChanged() {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, true);

        boolean defaultIconDisplayed = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .clickAppearance()
                .selectDefaultIcon()
                .clickSaveButton()
                .isDefaultIconDisplayed();

        Assert.assertTrue(defaultIconDisplayed, "The appearance icon was not changed to the default icon");
    }
}

