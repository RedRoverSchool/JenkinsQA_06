package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.FreestyleProjectPage;
import school.redrover.model.MainPage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By GO_TO_DASHBOARD_BUTTON = By.linkText("Dashboard");
    private static final String NEW_FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String TEST_NAME = "Test";
    private String name = "First Project";

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
    public void testAddDescriptionToFreestyleProject() {

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
    public void testDeleteFreestyleProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();

        getDriver().findElement(By.xpath("//a[@href='job/" + NEW_FREESTYLE_NAME + "/']")).click();
        getDriver().findElement(By.xpath("//span[contains(text(),'Delete Project')]")).click();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();

        Assert.assertFalse(getDriver().findElements(By
                        .xpath("//a[@class='jenkins-table__link model-link inside']"))
                .stream().map(WebElement::getText).collect(Collectors.toList()).contains(NEW_FREESTYLE_NAME));
    }

    @Test
    public void testNewFreestyleProjectCreated() {
        final String PROJECT_NAME = "Project1";

        WebElement createAJobArrow = getDriver().findElement(
                By.xpath("//a[@href='newJob']/span[@class = 'trailing-icon']")
        );
        createAJobArrow.click();

        WebElement inputItemName = getDriver().findElement(By.id("name"));
        getWait2().until(ExpectedConditions.elementToBeClickable(inputItemName))
                .sendKeys(PROJECT_NAME);

        WebElement freestyleProjectTab = getDriver().findElement(
                By.xpath("//ul[@class = 'j-item-options']/li[@tabindex='0']")
        );
        freestyleProjectTab.click();

        WebElement okButton = getDriver().findElement(By.className("btn-decorator"));
        okButton.click();

        WebElement dashboardLink = getDriver().findElement(
                By.xpath("//ol[@id='breadcrumbs']/li/a[text() = 'Dashboard']")
        );
        dashboardLink.click();

        Assert.assertTrue(getDriver().findElement(By.id("projectstatus")).isDisplayed());

        List<WebElement> newProjectsList = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody/tr"));

        Assert.assertEquals(newProjectsList.size(), 1);

        List<WebElement> projectDetailsList = getDriver().findElements(
                By.xpath("//table[@id='projectstatus']/tbody/tr/td")
        );

        Assert.assertEquals(projectDetailsList.get(2).getText(), PROJECT_NAME);
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
        getDriver().findElement(By.linkText("New Item")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(wrongCharacter);
        getDriver().findElement(By.xpath("//img[@class='icon-freestyle-project icon-xlg']")).click();

        String validationMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        Assert.assertEquals(validationMessage, "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
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
  
    public void testCreateFreestyleProject() {
        final String name = "Test";

        getDriver().findElement(By.linkText("New Item")).click();

        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys(name);
        getDriver().findElement(By.xpath("//*[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//*[@class='btn-decorator']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        String actualProjectName = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(actualProjectName, "Project " + name);

    }




    @Test
    public void testMakeProjectDisabled() {
        TestUtils.createFreestyleProject(this, name, false);

        WebElement actualProjectHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(actualProjectHeader.getText(), "Project First Project");

        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();
        WebElement receivedMessage = getDriver().findElement(By.xpath("//div/form[@id='enable-project']"));

        Assert.assertEquals(receivedMessage.getText().substring(0,34), "This project is currently disabled");
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

    @Ignore
    @Test(dependsOnMethods={"testCreateFreestyleProject"})
    public void testFreestyleProjectNameDisplayedWithoutDescription(){
        getDriver().findElement(By.xpath("//span[normalize-space()='"+ TEST_NAME +"']")).click();
        getDriver().findElement(By.xpath("//span[normalize-space()='Changes']")).click();
        getDriver().findElement(By.xpath("//span[normalize-space()='Status']")).click();

        String actualProjectName = getDriver().findElement(By.xpath("//h1")).getText();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link")));

        Assert.assertEquals(actualProjectName, "Project " + TEST_NAME);
        Assert.assertEquals(getDriver().findElement(By.id("description-link")).getText(),"Add description");
    }
    @Ignore

    @Test(dependsOnMethods = "testCreateFreestyleProjectValidName")
    public void testAddDescription() {
        WebElement projectName = getDriver().findElement(By.xpath("//a[@href='job/Astra/']"));
        new Actions(getDriver()).moveToElement(projectName).click(projectName).perform();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']/h1")));

        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        addDescriptionButton.click();

        WebElement textArea = getDriver().findElement(By.tagName("textarea"));
        String forTextArea = "123\nAAA\nSSS";
        textArea.sendKeys(forTextArea);

        WebElement previewButton = getDriver().findElement(By.xpath("//a[@previewendpoint='/markupFormatter/previewDescription']"));
        previewButton.click();

        WebElement previewTextArea = getDriver().findElement(By.xpath("//div[@class='textarea-preview']"));
        Assert.assertEquals(previewTextArea.getText(), forTextArea);

        WebElement saveButton = getDriver().findElement(By.xpath("//button[contains(text(), 'Save')]"));
        saveButton.click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='editDescription']")));

        WebElement description = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));

        Assert.assertEquals(description.getText(), forTextArea);
    }
}

