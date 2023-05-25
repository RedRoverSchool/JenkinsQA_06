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

import java.util.List;
import java.util.stream.Collectors;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By GO_TO_DASHBOARD_BUTTON = By.linkText("Dashboard");
    private static final String NEW_FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String TEST_NAME = "Test";

    @Test
    public void testCreateNewFreestyleProject() {

        WebElement projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickDashboard().getProjectName();

        Assert.assertEquals(projectName.getText(), FREESTYLE_NAME);
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
    public void testFreestyleProjectPageIsOpenedFromDashboard() {

        MainPage projectName = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .clickDashboard()
                .clickJobDropdownMenu(FREESTYLE_NAME);

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='main-panel']/h2 ")).getText(),
                "Permalinks");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("h1.job-index-headline.page-headline")).isEnabled());
    }

    @Test
    public void testNameAndDescAreDisplayed() {
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
    public void testBuildNowProject() {
        FreestyleProjectPage freestyleProjectPage = new MainPage(getDriver())
                .clickNewItem()
                .enterItemName(FREESTYLE_NAME)
                .selectFreestyleProjectAndOk()
                .clickSave()
                .selectBuildNow();

        Assert.assertEquals(freestyleProjectPage.getBuildsQuantity(), 1);

    }

    @Test
    public void testRenameFreestyleProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        getDriver().findElement(By.xpath("//a[@href='/job/" + FREESTYLE_NAME + "/confirm-rename']")).click();
        getDriver().findElement(By.cssSelector("input[name='newName']")).clear();
        getDriver().findElement(By.cssSelector("input[name='newName']")).sendKeys(NEW_FREESTYLE_NAME);
        getDriver().findElement(By.xpath("//button[@formnovalidate]")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("h1.job-index-headline.page-headline")).getText(),
                "Project " + NEW_FREESTYLE_NAME);
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

    @Test
    public void testRenameProjectFromTheProjectPage() {
        WebElement linkNewItem = getDriver().findElement(By.xpath("//div/span/a[@href='/view/all/newJob']"));
        linkNewItem.click();
        WebElement fieldInput = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
        fieldInput.click();
        fieldInput.sendKeys(FREESTYLE_NAME);
        WebElement labelFreestyleProject = getDriver().findElement(By.xpath("//ul/li[@class='hudson_model_FreeStyleProject']"));
        labelFreestyleProject.click();
        WebElement btnOk = getDriver().findElement(By.xpath("//button[@class and @id]"));
        btnOk.click();
        WebElement btnSave = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));
        btnSave.click();

        WebElement linkRename = getDriver().findElement(By.xpath("//div/span/a[contains(@href,'confirm-rename')]"));
        linkRename.click();
        WebElement inputNewName = getDriver().findElement(By.xpath("//div/input[@checkdependson='newName']"));
        inputNewName.click();
        inputNewName.clear();
        inputNewName.sendKeys(NEW_FREESTYLE_NAME);
        WebElement btnRename = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));
        btnRename.click();

        String actualNewName = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(actualNewName, "Project ".concat(NEW_FREESTYLE_NAME));
    }

    @DataProvider(name = "wrong-character")
    public Object[][] provideWrongCharacters() {
        return new Object[][]
                {{"!"}, {"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {":"}, {";"}, {"/"}, {"|"}, {"?"}, {"<"}, {">"}};
    }

    @Test(dataProvider = "wrong-character")
    public void testCreateFreestyleProjectWithInvalidName(String wrongCharacter) {
        getDriver().findElement(By.linkText("New Item")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(wrongCharacter);
        getDriver().findElement(By.xpath("//img[@class='icon-freestyle-project icon-xlg']")).click();

        String validationMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        Assert.assertEquals(validationMessage, "» ‘" + wrongCharacter + "’ is an unsafe character");
        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }

    @Test

    public void testNewFreestyleProjectFolder() {
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name = 'name']")).sendKeys("First");
        getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name ='Submit']"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[contains(text(),'Build Now')]/parent::a"
        ))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.linkText("Dashboard"))).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/builds']"))).click();
        WebElement projectStatusTable = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id = 'projectStatus']")));

        Assert.assertTrue(projectStatusTable.findElement(By.xpath("//a/span[contains(text(),'First')]")).isDisplayed());
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

    @Test(dependsOnMethods = {"testCreateFreestyleProject"})
    public void testFreestyleProjectNameDisplayedWithoutDescription() {
        getDriver().findElement(By.xpath("//span[normalize-space()='" + TEST_NAME + "']")).click();
        getDriver().findElement(By.xpath("//span[normalize-space()='Changes']")).click();
        getDriver().findElement(By.xpath("//span[normalize-space()='Status']")).click();

        String actualProjectName = getDriver().findElement(By.xpath("//h1")).getText();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link")));

        Assert.assertEquals(actualProjectName, "Project " + TEST_NAME);
        Assert.assertEquals(getDriver().findElement(By.id("description-link")).getText(), "Add description");
    }
}

