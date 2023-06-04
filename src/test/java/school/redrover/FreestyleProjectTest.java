package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static school.redrover.runner.TestUtils.createFreestyleProject;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = "FREESTYLE_NAME";
    private static final String NEW_FREESTYLE_NAME = "NEW_FREESTYLE_NAME";
    private static final String DESCRIPTION_TEXT = "DESCRIPTION_TEXT";
    private static final String NEW_DESCRIPTION_TEXT = "NEW_DESCRIPTION_TEXT";

    @Test
    public void testCreateFreestyleProject() {
        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickDashboard()
                .getProjectName();

        Assert.assertEquals(projectName.getText(),FREESTYLE_NAME);
    }

    @Test
    public void testCreateFSProjectWithDefaultConfigurations() {
        final String PROJECT_NAME = UUID.randomUUID().toString();

        MainPage mainPage = new MainPage(getDriver())
                .clickCreateAJobArrow()
                .enterItemName(PROJECT_NAME)
                .selectFreestyleProjectAndOk()
                .getHeader()
                .clickLogo();

        Assert.assertTrue(mainPage.getProjectStatusTable().isDisplayed());
        Assert.assertEquals(mainPage.getProjectsList().size(), 1);
        Assert.assertEquals(mainPage.getOnlyProjectName(), PROJECT_NAME);
    }

    @Test
    public void testCreateFreestyleProjectGoingFromPeoplePage() {
        final String projectName = "FreestyleProject";

        MainPage mainPage = new MainPage(getDriver())
                .clickPeopleOnLeftSideMenu()
                .clickNewItem()
                .enterItemName(projectName)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickDashboard();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//a[@href='job/FreestyleProject/']")).getText(), projectName);
    }

    @Ignore
    @Test
    public void testCreatedProjectIsOnDashboard() {
        createFreestyleProject(this, FREESTYLE_NAME, true);

        assertEquals(new MainPage(getDriver()).getJobName(FREESTYLE_NAME), FREESTYLE_NAME);
    }

    @Test
    public void testCreateWithExistingName() {
        createFreestyleProject(this, FREESTYLE_NAME, true);

        String itemAlreadyExistsMessage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProject()
                .clickOkToCreateWithExistingName()
                .getErrorMessage();

        assertEquals(itemAlreadyExistsMessage,
                String.format("A job already exists with the name ‘%s’", FREESTYLE_NAME));
    }

    @Test
    public void testEmptyNameError() {
        final String expectedError = "» This field cannot be empty, please enter a valid name";

        String actualError = new MainPage(getDriver())
                .clickCreateAJobArrow()
                .selectFreestyleProject()
                .getItemNameRequiredErrorText();

        Assert.assertEquals(actualError, expectedError);
    }

    @Test
    public void testOKButtonIsDisabledWhenEmptyName() {
       WebElement okButton = new MainPage(getDriver())
               .clickCreateAJobArrow()
               .selectFreestyleProject()
               .getOkButton();

        Assert.assertFalse(okButton.getAttribute("disabled").isEmpty());
    }


    @DataProvider(name = "wrong-character")
    public Object[][] provideWrongCharacters() {
        return new Object[][]
                {{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {":"}, {";"}, {"/"}, {"|"}, {"?"}, {"<"}, {">"}};
    }

    @Test(dataProvider = "wrong-character")
    public void testCreateFreestyleProjectWithInvalidName(String wrongCharacter){
        NewJobPage newJobPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(wrongCharacter);

        Assert.assertEquals(newJobPage.getItemInvalidMessage(), "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(newJobPage.isOkButtonEnabled());
    }

    @Test
    public void testFindNewProjectOnDashboard() {
        createFreestyleProject(this, "FREESTYLE_NAME", true);

        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();

        Assert.assertEquals(FREESTYLE_NAME,
                getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span")).getText());
    }

    @Test
    public void testFindNewProjectOnDashboardAndOpen() {
        createFreestyleProject(this, "FREESTYLE_NAME", true);

        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();
        WebElement projectIcon = getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span"));
        projectIcon.click();

        Assert.assertEquals("Project " + FREESTYLE_NAME,
                getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText());
    }

    @Ignore
    @Test
    public void testNavigateToChangePage() {
        createFreestyleProject(this, "Engineer", true);

        getDriver().findElement(By.xpath("//a[@href='/job/" + FREESTYLE_NAME + "/changes']")).click();

        Assert.assertEquals("Changes",
                getDriver().findElement(By.xpath("//h1[normalize-space()='Changes']")).getText());
    }

    @Test
    public void testDisableProject() {
        FreestyleProjectPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickTheDisableProjectButton();

        Assert.assertEquals(projectName.getWarningMessage(), "This project is currently disabled");
    }

    @Test
    public void testEnableProject() {
        MainPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickTheDisableProjectButton()
                .clickTheEnableProjectButton()
                .clickDashboard();

        Assert.assertEquals(projectName.getJobBuildStatusIcon(FREESTYLE_NAME), "Not built");
    }

    @Test
    public void testRenameFreestyleProject() {
        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickRenameProject(FREESTYLE_NAME)
                .enterNewName(FREESTYLE_NAME + " New")
                .submitNewName();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME + " New");
    }

    @Test
    public void testRenamingProjectFromTheDashboard() {
        String expectedResultProjectPage = "Project Engineer2";
        String expectedResultDashboardPage = "Engineer2";
        createFreestyleProject(this, "Engineer", true);

        Actions actions = new Actions(getDriver());
        WebElement nameProject = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a"));
        actions.moveToElement(nameProject).perform();

        WebElement dropdown = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a/button"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", dropdown);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='first-of-type']/li[6]"))).click();
        WebElement inputName = getDriver().findElement(By.xpath("//input[@name='newName']"));
        inputName.clear();
        inputName.click();
        inputName.sendKeys("Engineer2");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), expectedResultProjectPage);

        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']/td[3]"))
                .getText(), expectedResultDashboardPage);
    }

    @Ignore
    @Test
    public void testCreateFreestyleProjectWithDescription() {

        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .addDescription("Description")
                .clickSaveButton();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(freestyleProjectPage.getDescription(), "Description");
    }

    @Test
    public void testDeleteFreestyleProjectDouble() {
        FreestyleProjectPage deletedProject = new MyViewsPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickDeleteProject();

        Assert.assertFalse(deletedProject.checkProjectWasDeleted(FREESTYLE_NAME));
    }

    public void testEditDescription () {
        String editDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickAddDescription()
                .addDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .clickEditDescription()
                .removeOldDescriptionAndAddNew(NEW_DESCRIPTION_TEXT)
                .clickSaveDescription()
                .getDescription();

        Assert.assertEquals(editDescription, NEW_DESCRIPTION_TEXT);
    }


    @Test
    public void testPreviewDescription () {
        String previewDescription = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickAddDescription()
                .addDescription(DESCRIPTION_TEXT)
                .clickPreviewButton()
                .getPreviewDescription();

        Assert.assertEquals(previewDescription, "DESCRIPTION_TEXT");
    }

    @Test
    public void testVisibleProjectNameAndDescriptionFromViewPage() {
        createFreestyleProject(this, FREESTYLE_NAME, false);

        FreestyleProjectPage projectPage = new FreestyleProjectPage(getDriver())
                .clickAddDescription()
                .addDescription(DESCRIPTION_TEXT)
                .clickSaveDescription()
                .clickDashboard()
                .clickFreestyleProjectName(FREESTYLE_NAME);

        String projectNameFromViewPage = projectPage.getProjectName();
        String projectDescriptionFromViewPage = projectPage.getDescription();

        Assert.assertEquals(projectNameFromViewPage, "Project " + FREESTYLE_NAME);
        Assert.assertEquals(projectDescriptionFromViewPage, DESCRIPTION_TEXT);
    }

    @Ignore
    @Test
    public void testBuildFreestyleProject() {
        String consoleOutput = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("MyFreestyleProject")
                .selectFreestyleProjectAndOk()
                .addExecuteShellBuildStep("echo Hello")
                .clickSaveButton()
                .selectBuildNow()
                .openConsoleOutputForBuild()
                .getConsoleOutputText();

        Assert.assertTrue(consoleOutput.contains("echo Hello"), "Command wasn't run");
        Assert.assertTrue(consoleOutput.contains("Finished: SUCCESS"), "Build wasn't finished successfully");
    }

    @Test
    public void testCreatedNewBuild() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("Engineer")
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickFreestyleProjectName("Engineer")
                .selectBuildNow()
                .selectBuildItemTheHistoryOnBuildPage();

        Assert.assertTrue(new BuildPage(getDriver()).getBuildHeader().isDisplayed(), "build not created");
    }

    @Ignore
    @Test
    public void testBuildLinks() {
        createFreestyleProject(this, "Engineer", true);

        WebElement buildNowBtn = getDriver().findElement(By.xpath("//*[@class='task '][4]/span/a"));
        buildNowBtn.click();

        WebElement dashBoardBtn = getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a"));
        dashBoardBtn.click();

        WebElement greenCheckmark = getDriver().findElement(By.xpath("//*[@class='svg-icon ']"));

        Assert.assertTrue(greenCheckmark.isDisplayed());

        WebElement projectNameBtn = getDriver()
                .findElement(By.xpath("//*[@class='jenkins-table__link model-link inside']"));
        projectNameBtn.click();

        WebElement permaLinks = getDriver()
                .findElement(By.xpath("//*[@class='permalink-link model-link inside tl-tr']"));
        Assert.assertTrue(permaLinks.isDisplayed());
    }

    @Test
    public void testFreestyleProjectJob() {
        String nameProject = "Hello world";
        String steps = "javac ".concat(nameProject.concat(".java\njava ".concat(nameProject)));

        String consoleOutput = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(nameProject)
                .selectFreestyleProjectAndOk()
                .addBuildStepsExecuteShell(steps)
                .clickSaveButton()
                .selectBuildNow()
                .openConsoleOutputForBuild()
                .getConsoleOutputText();

        Assert.assertTrue(consoleOutput.contains("Finished: SUCCESS"), "Build Finished: FAILURE");
    }

    @Test
    public void testAddDescriptionFromConfigureDropDownAndPreview() {
        final String descriptionText = "In publishing and graphic design, Lorem ipsum is a placeholder " +
                "text commonly used to demonstrate the visual form of a document or a typeface without relying .";

        String previewText = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickDashboard()
                .clickConfigureDropDown(FREESTYLE_NAME)
                .addDescription(descriptionText)
                .clickPreviewButton()
                .getPreviewDescription();

        Assert.assertEquals(previewText, descriptionText);

        String actualDescriptionText = new FreestyleProjectPage(getDriver())
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescriptionText, descriptionText);
    }

    @Test
    public void testDeleteFreestyleProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        getDriver().findElement(By.linkText("Dashboard")).click();

        getDriver().findElement(By.xpath("//a[@href='job/" + NEW_FREESTYLE_NAME + "/']")).click();
        getDriver().findElement(By.xpath("//span[contains(text(),'Delete Project')]")).click();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();

        Assert.assertFalse(getDriver().findElements(By
                        .xpath("//a[@class='jenkins-table__link model-link inside']"))
                .stream().map(WebElement::getText).collect(Collectors.toList()).contains(NEW_FREESTYLE_NAME));
    }

    @Test
    public void testDeleteProjectFromDropdown() {
        final String projectName = "Name";

        MyViewsPage h2text = new MyViewsPage(getDriver())
                .clickNewItem()
                .enterItemName(projectName)
                .selectFreestyleProjectAndOk()
                .clickSaveButton()
                .clickDashboard()
                .dropDownMenuClickDelete(projectName)
                .acceptAlert()
                .clickMyViewsSideMenuLink();

        Assert.assertEquals(h2text.getStatusMessageText(), "This folder is empty");
    }

}

