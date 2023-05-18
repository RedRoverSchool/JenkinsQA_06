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
import school.redrover.runner.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By GO_TO_DASHBOARD_BUTTON = By.linkText("Dashboard");
    private static final String NEW_FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);

    @Ignore
    @Test
    public void testCreateNewFreestyleProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1")).getText(), "Project " + FREESTYLE_NAME);
    }

    @Ignore
    @Test
    public void testDisableProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'warning']")).getText().trim().substring(0, 34),
                "This project is currently disabled");
    }


    @Ignore
    @Test
    public void testEnableProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        getDriver().findElement(By.cssSelector("button.jenkins-button.jenkins-button--primary ")).click();
        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//span/span/*[name()='svg' and @class= 'svg-icon ']")).getAttribute("tooltip"), "Not built");
    }

    @Ignore
    @Test
    public void testFreestyleProjectPageIsOpenedFromDashboard() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();
        getDriver().findElement(By.xpath("//a[@href='job/" + FREESTYLE_NAME + "/']")).click();
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                String.format("Project %s", FREESTYLE_NAME));

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='main-panel']/h2 ")).getText(),
                "Permalinks");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("h1.job-index-headline.page-headline")).isEnabled());
    }

    @Ignore
    @Test
    public void testAddDescriptionToFreestyleProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();

        getDriver().findElement(By.xpath("//a[@href='job/" + FREESTYLE_NAME + "/']")).click();
        getDriver().findElement(By.id("description-link")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='setting-main help-sibling']/textarea"))).sendKeys("Job " + FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector("button.jenkins-button.jenkins-button--primary")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id = 'description'] /div[1]")).getText(), "Job " + FREESTYLE_NAME);
    }

    @Ignore
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

    @Ignore
    @Test()
    public void testCreateFreestyleProjectWithValidName(){
        getDriver().findElement(By.xpath("//*[text()='Create a job']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Project1");
        getDriver().findElement(By.xpath("//img[@class='icon-freestyle-project icon-xlg']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                "Project " + "Project1");
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

    @Test
    public void testRenameProjectFromTheProjectPage() {
        WebElement linkNewItem  = getDriver().findElement(By.xpath("//div/span/a[@href='/view/all/newJob']"));
            linkNewItem.click();
        WebElement fieldInput  = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
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
        WebElement btnRename= getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));
            btnRename.click();

        String  actualNewName = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(actualNewName,"Project ".concat(NEW_FREESTYLE_NAME));
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
}
