package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
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
    private static final String PRINT_MESSAGE_PIPELINE_SYNTAX = "TEXT";
    private static final String DESCRIPTION_TEXT = "DESCRIPTION_TEXT";

    @Test
    public void testCreateFromNewItem() {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, true);

        boolean actualNewFolderName = new MainPage(getDriver())
                .jobIsDisplayed(ORGANIZATION_FOLDER_NAME);

        Assert.assertTrue(actualNewFolderName, "error was not show name folder");
    }

    @Test(dependsOnMethods = "testCreateFromNewItem")
    public void testDisableFromConfigurationPage() {
        String disabledText = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .clickDisableEnable()
                .clickSaveButton()
                .getTextFromDisableMessage();

        Assert.assertTrue(disabledText.contains("This Organization Folder is currently disabled"));
    }

    @Test
    public void testDeleteItemFromDropDown() {
        TestUtils.createJob(this, "OrgFolder", TestUtils.JobType.OrganizationFolder, true);

        boolean welcomeToJenkinsIsDisplayed = new MainPage(getDriver())
                .openJobDropDownMenu("OrgFolder")
                .dropDownMenuClickDeleteFolders("OrgFolder")
                .clickYesButton()
                .WelcomeIsDisplayed();

        Assert.assertTrue(welcomeToJenkinsIsDisplayed, "error, Welcome to Jenkins! is not displayed");
    }

    @Test(dependsOnMethods = "testCreateFromManageJenkinsPage")
    public void testCreateWithExistingName() {
        CreateItemErrorPage errorPage =
                TestUtils.createJobWithExistingName(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "A job already exists with the name ‘" + ORGANIZATION_FOLDER_NAME + "’");
    }

    @Test(dependsOnMethods = "testDeleteDisplayName")
    public void testRenameFromSideMenu() {
        String actualRenamedFolderName = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickRename()
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(actualRenamedFolderName, ORGANIZATION_FOLDER_RENAMED);
    }

    @Test(dependsOnMethods = "testDisableFromConfigurationPage")
    public void testEnableFromConfigurationPage() {
        String enableOrgFolder = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .clickDisableEnable()
                .clickSaveButton()
                .getDisableButtonText();

        Assert.assertEquals(enableOrgFolder.trim(), "Disable Organization Folder");
    }

    @Test(dependsOnMethods = "testEnableFromConfigurationPage")
    public void testDisableFromProjectPage() {
        String disabledText = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickDisableEnableButton()
                .getTextFromDisableMessage();

        Assert.assertEquals(disabledText.substring(0, 46), "This Organization Folder is currently disabled");
    }

    @Test(dependsOnMethods = "testDisableFromProjectPage")
    public void testEnableFromProjectPage() {
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

    @Test(dependsOnMethods = "testEnableFromProjectPage")
    public void testAddDescriptionFromConfigurationPage() {
        String textFromDescription = new MainPage(getDriver())
                .clickConfigureDropDown(ORGANIZATION_FOLDER_NAME, new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
                .addDescription(DESCRIPTION_TEXT)
                .clickSaveButton()
                .getAddedDescriptionFromConfig();

        Assert.assertEquals(textFromDescription, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescriptionFromConfigurationPage")
    public void testRenameFromDropDownMenu() {
        String actualRenamedName = new MainPage(getDriver())
                .dropDownMenuClickRename(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButton()
                .getJobName();

        Assert.assertEquals(actualRenamedName, ORGANIZATION_FOLDER_RENAMED);
    }

    @Test(dependsOnMethods = "testRenameFromDropDownMenu")
    public void testRenameToTheCurrentNameAndGetError() {
        String errorMessage = new MainPage(getDriver())
                .dropDownMenuClickRename(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .enterNewName(ORGANIZATION_FOLDER_RENAMED)
                .clickRenameButtonAndGoError()
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "The new name is the same as the current name.");
    }

    @Test(dependsOnMethods = "testRenameToTheCurrentNameAndGetError")
    public void testDeleteItemFromSideMenu() {
        String welcomeText = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .clickDeleteJobLocatedOnMainPage()
                .clickYesButton()
                .getWelcomeText();

        Assert.assertEquals(welcomeText, "Welcome to Jenkins!");
    }

    @Test
    public void testCreateFromManageJenkinsPage() {
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
    public void testCreateWithSpaceInsteadName() {
        CreateItemErrorPage errorPage =
                TestUtils.createJobWithSpaceInsteadName(this, TestUtils.JobType.OrganizationFolder);

        Assert.assertEquals(errorPage.getHeaderText(), "Error");
        Assert.assertEquals(errorPage.getErrorMessage(), "No name is specified");
    }

    @Test(dependsOnMethods = "testPreviewDescriptionFromProjectPage")
    public void testPreviewDescriptionFromConfigurationPage() {
        String previewText = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .addDescription(DESCRIPTION_TEXT)
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testRenameFromSideMenu")
    public void testPreviewDescriptionFromProjectPage() {
        String previewText = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_RENAMED, new OrganizationFolderPage(getDriver()))
                .clickAddDescription()
                .enterDescription(DESCRIPTION_TEXT)
                .clickPreview()
                .getPreviewText();

        Assert.assertEquals(previewText, DESCRIPTION_TEXT);
    }

    @DataProvider(name = "wrong-character")
    public Object[][] provideWrongCharacters() {
        return new Object[][]{{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"?"}, {"|"}, {">"}, {"["}, {"]"}};
    }

    @Test(dataProvider = "wrong-character")
    public void testCreateUsingInvalidData(String invalidData) {
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(invalidData)
                .selectJobType(TestUtils.JobType.OrganizationFolder);

        Assert.assertFalse(newJobPage.isOkButtonEnabled(), "Save button is enabled");
        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + invalidData + "’ is an unsafe character");
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

    @Test(dependsOnMethods = "testCreateFromCreateAJob")
    public void testScanOrgFolderLog() {
        String titleScanOrgFolderLogPage = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickScanOrgFolderLog()
                .getTextFromTitle();

        Assert.assertEquals(titleScanOrgFolderLogPage, "Scan Organization Folder Log");
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
    @Ignore
    @Test
    public void testAddHealthMetricsFromSideMenu() {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, true);

        boolean isHealthMetricsAdded = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickConfigure()
                .addHealthMetrics()
                .clickSaveButton()
                .clickConfigure()
                .clickHealthMetrics()
                .healthMetricIsVisible();

        Assert.assertTrue(isHealthMetricsAdded, "Health Metric is not displayed");
    }

    @Test
    public void testCreateMultibranchProject() {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, false);

        String createMultibranchProject = new OrganizationFolderPage(getDriver())
                .clickMultibranchProject()
                .getBranchesAndPullRequestsTutorial();

        Assert.assertEquals(createMultibranchProject, "Branches and Pull Requests");
    }

    @Test
    public void testConfigureProject() {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, false);

        String configurationHeaderText = new OrganizationFolderPage(getDriver())
                .clickConfigureProject()
                .getConfigurationHeaderText();

        Assert.assertEquals(configurationHeaderText, "Configuration");
    }

    @Test(dependsOnMethods = "testCreateFromCreateAJob")
    public void testCredentials() {
        String titleCredentials = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickCredentials()
                .getTitleText();

        Assert.assertEquals(titleCredentials, "Credentials");
    }

    @Test(dependsOnMethods = "testCreateFromCreateAJob")
    public void testOrganizationFolderConfigPipelineSyntax() {
        final String expectedText = "echo '" + PRINT_MESSAGE_PIPELINE_SYNTAX + "'";

        String pipelineSyntax = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickPipelineSyntax()
                .clickPrintMessageOption()
                .enterMessage(PRINT_MESSAGE_PIPELINE_SYNTAX)
                .clickGeneratePipelineScriptButton()
                .getTextPipelineScript();

        Assert.assertEquals(pipelineSyntax, expectedText );
    }

    @Test
    public void testCreatingJenkinsPipeline() {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, false);
        String linkBookCreatingPipeline = new OrganizationFolderPage(getDriver())
                .getTextCreatingJenkinsPipeline();

        String pipelineOneTutorial = new OrganizationFolderPage(getDriver())
                .clickPipelineOneTutorial()
                .getTextPipelineTitle();

        Assert.assertEquals(linkBookCreatingPipeline, "Creating a Jenkins Pipeline");
        Assert.assertEquals(pipelineOneTutorial,"Pipeline");
    }

    @Test(dataProvider = "wrong-character")
    public void testRenameWithInvalidData(String invalidData) {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, true);

        String actualErrorMessage = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickRename()
                .enterNewName(invalidData)
                .clickRenameButtonAndGoError()
                .getErrorMessage();

        switch (invalidData) {
            case "&" -> Assert.assertEquals(actualErrorMessage, "‘&amp;’ is an unsafe character");
            case "<" -> Assert.assertEquals(actualErrorMessage, "‘&lt;’ is an unsafe character");
            case ">" -> Assert.assertEquals(actualErrorMessage, "‘&gt;’ is an unsafe character");
            default -> Assert.assertEquals(actualErrorMessage, "‘" + invalidData + "’ is an unsafe character");
        }
    }

    @Test
    public void testCreateWithLongName() {
        String longName = RandomStringUtils.randomAlphanumeric(256);
        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(longName)
                .selectJobAndOkAndGoToBugPage(TestUtils.JobType.OrganizationFolder)
                .getErrorMessage();

        Assert.assertEquals(errorMessage, "A problem occurred while processing the request.");
    }

    @Test
    public void testOrganizationFolderEvents() {
        TestUtils.createJob(this,ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, true);

        String eventTitle = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickOrgFolderEvents()
                .getTextFromTitle();

        Assert.assertEquals(eventTitle,"Organization Folder Events");
    }

    @Test(dependsOnMethods = "testCredentials")
    public void testReRunFolderComputation() {
        String titleScanOrganizationFolder = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickRerunTheFolderComputation()
                .getTextFromTitle();

        Assert.assertEquals(titleScanOrganizationFolder, "Scan Organization Folder");
    }

    @Test
    public void testCreateWithDotInsteadOfName() {
        String errorMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(".")
                .getItemInvalidMessage();

        Assert.assertEquals(errorMessage, "» “.” is not an allowed name");
    }

    @Test
    public void testCreateFromMyViewsNewItem() {
        String newOrganizationFolderName = new MainPage(getDriver())
                .clickMyViewsSideMenuLink()
                .clickNewItem()
                .enterItemName(ORGANIZATION_FOLDER_NAME)
                .selectJobType(TestUtils.JobType.OrganizationFolder)
                .clickOkButton(new OrganizationFolderConfigPage(new OrganizationFolderPage(getDriver())))
                .clickSaveButton()
                .getJobName();

        boolean newOrganizationFolderNameIsDisplayed = new OrganizationFolderPage(getDriver())
                .getBreadcrumb()
                .openMyViewsPageFromDashboardDropdownMenu()
                .jobIsDisplayed(newOrganizationFolderName);

        Assert.assertEquals(newOrganizationFolderName, ORGANIZATION_FOLDER_NAME);
        Assert.assertTrue(newOrganizationFolderNameIsDisplayed);
    }

    @Test
    public void testCancelDeletingFromDropDownMenu() {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, true);

        boolean isOrganisationFolderDisplayed = new MainPage(getDriver())
                .dropDownMenuClickDeleteFolders(ORGANIZATION_FOLDER_NAME)
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(ORGANIZATION_FOLDER_NAME);

        Assert.assertTrue(isOrganisationFolderDisplayed, "Organisation Folder`s name is not displayed");
    }

    @Test
    public void testCancelDeletingFromSideMenu() {
        TestUtils.createJob(this, ORGANIZATION_FOLDER_NAME, TestUtils.JobType.OrganizationFolder, true);

        boolean isOrganisationFolderDisplayed = new MainPage(getDriver())
                .clickJobName(ORGANIZATION_FOLDER_NAME, new OrganizationFolderPage(getDriver()))
                .clickDeleteJobLocatedOnFolderPage()
                .getHeader()
                .clickLogo()
                .jobIsDisplayed(ORGANIZATION_FOLDER_NAME);

        Assert.assertTrue(isOrganisationFolderDisplayed, "Organisation Folder`s name is not displayed");
    }
}
