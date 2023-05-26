package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.model.*;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String NEW_FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String DESCRIPTION_TEXT = RandomStringUtils.randomAlphanumeric(15);

    private void createFreestyleProject() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys(FREESTYLE_NAME);

        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
    }

    @Test
    public void testCreateNewFreestyleProject() {
        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickDashboard().getProjectName();

        Assert.assertEquals(projectName.getText(),  FREESTYLE_NAME);
    }

    @Test
    public void testNewFreestyleProjectCreated() {
        final String PROJECT_NAME = "Project1";

        WebElement createAJobArrow = getDriver().findElement(
                By.xpath("//a[@href='newJob']/span[@class = 'trailing-icon']"));
        createAJobArrow.click();

        WebElement inputItemName = getDriver().findElement(By.id("name"));
        getWait2().until(ExpectedConditions.elementToBeClickable(inputItemName))
                .sendKeys(PROJECT_NAME);

        WebElement freestyleProjectTab = getDriver().findElement(
                By.xpath("//ul[@class = 'j-item-options']/li[@tabindex='0']"));
        freestyleProjectTab.click();

        WebElement okButton = getDriver().findElement(By.className("btn-decorator"));
        okButton.click();

        WebElement dashboardLink = getDriver().findElement(
                By.xpath("//ol[@id='breadcrumbs']/li/a[text() = 'Dashboard']"));
        dashboardLink.click();

        Assert.assertTrue(getDriver().findElement(By.id("projectstatus")).isDisplayed());

        List<WebElement> newProjectsList = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr"));

        Assert.assertEquals(newProjectsList.size(), 1);

        List<WebElement> projectDetailsList = getDriver().findElements(
                By.xpath("//table[@id='projectstatus']/tbody/tr/td"));

        Assert.assertEquals(projectDetailsList.get(2).getText(), PROJECT_NAME);
    }

    @Test
    public void testCreateFreestyleProjectGoingFromPeoplePage() {
        String projectName = "FreestyleProject";

        getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']")).click();
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("name"))).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary jenkins-buttons-row--equal-width']")).click();
        getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']")).click();

        getDriver().findElement(By.xpath("//ol/li/a[@href='/'] ")).click();

        WebElement createdProject = getDriver().findElement(By.xpath("//a[@href='job/FreestyleProject/']"));

        Assert.assertEquals(createdProject.getText(), projectName);
    }

    @Test
    public void testCreatedProjectIsOnDashboard() {
        TestUtils.createFreestyleProject(this, FREESTYLE_NAME, true);

        assertEquals(new MainPage(getDriver()).getJobName(FREESTYLE_NAME), FREESTYLE_NAME);
    }

    @Test
    public void testCreateWithExistingName() {
        TestUtils.createFreestyleProject(this, FREESTYLE_NAME, true);

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
    public void testErrorWhenCreatingFreeStyleProjectWithEmptyName() {
        final String EXPECTED_ERROR = "» This field cannot be empty, please enter a valid name";

        getDriver().findElement(By.xpath("//a[@href='newJob']/span[@class = 'trailing-icon']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[@class = 'j-item-options']/li[@tabindex='0']"))).click();

        String actualError = getDriver().findElement(By.id("itemname-required")).getText();

        Assert.assertEquals(actualError, EXPECTED_ERROR);
    }

    @Test
    public void testOKButtonIsDisabledWhenCreatingFreestyleProjectWithEmptyName() {
        getDriver().findElement(By.xpath("//a[@href='newJob']/span[@class = 'trailing-icon']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[@class = 'j-item-options']/li[@tabindex='0']"))).click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));

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
        createFreestyleProject();

        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();

        Assert.assertEquals(FREESTYLE_NAME,
                getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span")).getText());
    }

    @Test
    public void testFindNewProjectOnDashboardAndOpen() {
        createFreestyleProject();

        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();
        WebElement projectIcon = getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span"));
        projectIcon.click();

        Assert.assertEquals("Project " + FREESTYLE_NAME,
                getDriver().findElement(By.cssSelector(".job-index-headline.page-headline")).getText());
    }

    @Test
    public void testNavigateToChangePage() {
        createFreestyleProject();

        getDriver().findElement(By.xpath("//a[@href='/job/" + FREESTYLE_NAME + "/changes']")).click();

        Assert.assertEquals("Changes",
                getDriver().findElement(By.xpath("//h1[normalize-space()='Changes']")).getText());
    }

    @Test
    public void testCreateFreestyleProjectWithDescription() {

        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .addDescription("Description")
                .clickSave();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(freestyleProjectPage.getDescription(), "Description");
    }

    @Test
    public void testEditDescription () {
        createFreestyleProject();

        WebElement descriptionButton = getDriver().findElement(By.xpath("//*[@id = 'description-link']"));
        descriptionButton.click();

        WebElement descInputField = getDriver().findElement(By.xpath("//*[@name = 'description']"));
        descInputField.sendKeys(DESCRIPTION_TEXT);

        WebElement saveButton = getDriver()
                .findElement(By.xpath("//*[@id='description']/form/div[2]/button"));
        saveButton.click();

        WebElement editButton = getDriver().findElement(By.xpath("//*[@href = 'editDescription']"));
        editButton.click();

        WebElement oldDescription = getDriver().findElement(By.xpath("//*[@id='description']/form/div[1]/div[1]/textarea"));
        oldDescription.clear();
        oldDescription.sendKeys("Edit description");

        WebElement saveButton2 = getDriver()
                .findElement(By.xpath("//*[@id='description']/form/div[2]/button"));
        saveButton2.click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id = 'description']/div[1]"))
                .getText(),"Edit description");
    }

    @Test
    public void testPreviewDescription () {
        FreestyleProjectConfigPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickAddDescription()
                .addDescription(DESCRIPTION_TEXT)
                .clickPreviewButton();

        Assert.assertEquals(freestyleProjectPage.getPreviewDescription(), DESCRIPTION_TEXT);

    }

    @Test
    public void testVisibleProjectNameAndDescriptionFromViewPage() {
        final String description = "This is a description for My Freestyle Project";

        TestUtils.createFreestyleProject(this, FREESTYLE_NAME, false);

        getDriver().findElement(By.linkText("Add description")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name = 'description']")))
                .sendKeys(description);
        getDriver().findElement(By.name("Submit")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.linkText("Dashboard"))).click();
        getDriver().findElement(By.linkText(FREESTYLE_NAME)).click();

        assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                "Project " + FREESTYLE_NAME);
        assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[contains(text(),'" + description + "')]")).getText(),
                description);
    }


    @Test
    public void testDisableProject() {
        FreestyleProjectPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickTheDisableProjectButton();

        Assert.assertEquals(projectName.getWarningMessage(), "This project is currently disabled");
    }

    @Test
    public void testEnableProject() {
        MainPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
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
                .clickSave()
                .clickRenameProject(FREESTYLE_NAME)
                .enterNewName(FREESTYLE_NAME + " New")
                .submitNewName();

        Assert.assertEquals(freestyleProjectPage.getProjectName(), "Project " + FREESTYLE_NAME + " New");
    }

    @Test
    public void testRenamingProjectFromTheDashboard() {
        String expectedResultProjectPage = "Project Engineer2";
        String expectedResultDashboardPage = "Engineer2";
        TestUtils.createFreestyleProject(this, "Engineer", true);

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
        createFreestyleProject();

        WebElement dashboardBreadCrumb = getDriver().findElement(By.xpath("//li/a[contains(text(),'Dashboard')]"));
        dashboardBreadCrumb.click();

        Actions act = new Actions(getDriver());
        WebElement projectName = getDriver().findElement(By.xpath("//span[contains(text(), '" + FREESTYLE_NAME + "')]"));
        act.moveToElement(projectName, 23, 7).perform();


        Actions act2 = new Actions(getDriver());
        WebElement dropDownButton = getDriver().findElement(By.xpath("//td/a/button[@class = 'jenkins-menu-dropdown-chevron']"));
        act2.moveToElement(dropDownButton).perform();
        dropDownButton.sendKeys(Keys.RETURN);

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("breadcrumb-menu")));
        getDriver().findElement(By.xpath("//div//li//span[contains(text(),'Delete Project')]")).click();
        getDriver().switchTo().alert().accept();

        getDriver().findElement(By.xpath("//a[@href = '/me/my-views']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h2")).getText(), "This folder is empty");
    }

    @Test
    public void testBuildFreestyleProject() {
        WebElement newItem = getDriver().findElement(By.xpath("//*[@href='/view/all/newJob']"));
        newItem.click();

        WebElement projectName = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id = 'name']")));
        projectName.sendKeys("MyFreestyleProject");

        WebElement typeFreeStyle = getDriver().findElement(By.xpath("//li[contains(@class, 'FreeStyleProject')]"));
        typeFreeStyle.click();

        WebElement createItem = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        createItem.click();

        WebElement buildStep = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Add build step')]")));
        Actions actions = new Actions(getDriver());
        actions.scrollToElement(getDriver().findElement(By.xpath("//button[contains(text(), 'Add post-build action')]"))).click().perform();
        getWait2().until(ExpectedConditions.elementToBeClickable(buildStep)).click();

        WebElement executeShell = getDriver().findElement(By.xpath("//a[contains(text(), 'Execute shell')]"));
        executeShell.click();

        WebElement codeMirror = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("CodeMirror")));
        actions.scrollToElement(getDriver().findElement(By.xpath("//button[contains(text(), 'Add post-build action')]"))).click().perform();
        WebElement codeLine = codeMirror.findElements(By.className("CodeMirror-lines")).get(0);
        codeLine.click();
        WebElement command = codeMirror.findElement(By.cssSelector("textarea"));
        command.sendKeys("echo Hello");

        WebElement saveConfiguration = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveConfiguration.click();

        WebElement toBuild = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'build?delay')]")));
        toBuild.click();

        WebElement firstBuild = getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='build-status-link']")));
        firstBuild.click();

        WebElement consoleOutput = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//pre[@class='console-output']")));

        Assert.assertTrue(consoleOutput.getText().contains("echo Hello"));
        Assert.assertTrue(consoleOutput.getText().contains("Finished: SUCCESS"));
    }

    @Test
    public void testCreatedNewBuild() {
        new MainPage(getDriver())
                .clickNewItem()
                .enterItemName("Engineer")
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickDashboard()
                .getProjectNameClick()
                .selectBuildNow()
                .selectBuildItemTheHistoryOnBuildPage();

        Assert.assertTrue(new BuildPage(getDriver()).getBuildHeader().isDisplayed(), "build not created");
    }

    @Test
    public void testBuildLinks() {
        createFreestyleProject();

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

    public void testCreateFreestyleProject() {
        String nameFreestyle = "FreestyleProject";
        String description = "First project";

        new MainPage(getDriver())
                .clickNewItemButton()
                .inputAnItemName(nameFreestyle)
                .clickFreestyleProject()
                .clickSaveButton()
                .sendAreDescriptionInputString(description)
                .clickSaveButton()
                .clickDashBoardButton();

        String actualFreestyleName = getDriver().findElement(By.xpath("//a[@href='job/FreestyleProject/']")).getText();

        Assert.assertEquals(actualFreestyleName,nameFreestyle);
    }
}

