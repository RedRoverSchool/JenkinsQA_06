package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaramelSyrupForJavaTest extends BaseTest {

    @Test
    public void testAbramovaHotKeys() {

        WebElement body = getDriver().findElement(By.tagName("body"));
        body.sendKeys(Keys.chord(Keys.CONTROL, "k"));
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@role]"));
        WebElement currentElement = getDriver().switchTo().activeElement();

        Assert.assertEquals(currentElement, searchBox);
    }

    @Test
    public void testKhudovaEditDescriptionButtonChanges() {

        WebElement editDescriptionButton = getDriver().findElement(By.id("description-link"));
        editDescriptionButton.click();

        WebElement inputWindow = getDriver().findElement(By.xpath("//textarea"));
        inputWindow.sendKeys("New Description");

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name= 'Submit']"));
        saveButton.click();

        WebElement changedDescriptionButton = getDriver().findElement(By.id("description-link"));
        String newButtonText = changedDescriptionButton.getText();

        Assert.assertEquals(newButtonText, "Edit description");
    }

    @Test
    public void testRykovaEmptyRequiredField(){

        WebElement newItem = getDriver().findElement(By.cssSelector("#side-panel>div>div"));
        newItem.click();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement freestyleProject =
                getDriver().findElement(By.className("hudson_model_FreeStyleProject"));
        freestyleProject.click();

        WebElement error = getDriver().findElement(By.id("itemname-required"));

        WebElement notError =
                getDriver().findElement(By.xpath("//div[@class = 'add-item-name']/div[1]"));

        Assert.assertTrue(error.isDisplayed());
        Assert.assertFalse(notError.isDisplayed());
    }

    @Ignore
    @Test
    public void testDimaKFirst() {
        String expResFol = "Folder";
        String expResName = "First item";

        WebElement newItem = getDriver().findElement(By.cssSelector("#side-panel>div>div"));
        newItem.click();
        WebElement send = getDriver().findElement(By.className("jenkins-input"));
        send.sendKeys("First item");
        WebElement folder = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']//span"));
        folder.click();
        WebElement okey = getDriver().findElement(By.id("ok-button"));
        okey.click();
        WebElement board = getDriver().findElement(By.xpath("//ol[@id = 'breadcrumbs']//a"));
        board.click();
        Actions act = new Actions(getDriver());
        WebElement boardfold = getDriver().findElement(By.xpath("(//*[name()='svg'][@title='Folder'])[1]\n"));
        act.moveToElement(boardfold).perform();
        WebElement nav = getDriver().findElement(By.className("tippy-content"));
        String actResFol = nav.getText();
        WebElement nameF = getDriver().findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']/span"));
        String actResName = nameF.getText();

        Assert.assertEquals(actResFol, expResFol);
        Assert.assertEquals(actResName, expResName);
    }

    @Test
    public void testADCreateJobProject() {
        String expectedResultSummary = "Project Engineer";
        String expectedResultDescription = "New Project";

        WebElement createJob = getDriver().findElement(By.xpath("//span[text()='Create a job']"));
        createJob.click();
        WebElement inputLine = getDriver().findElement(By.xpath("//input[@id='name']"));
        inputLine.click();
        inputLine.sendKeys("Engineer");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//span[text()='Freestyle project']"));
        freestyleProject.click();
        WebElement buttonOk = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        buttonOk.click();

        WebElement description = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        description.click();
        description.sendKeys("New Project");
        WebElement buttonSubmit = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        buttonSubmit.click();

        WebElement newSummaryProject = getDriver().findElement(By.xpath("//h1[text()='Project Engineer']"));
        String actualResultSummary = newSummaryProject.getText();
        WebElement newDescription = getDriver().findElement(By.xpath("//div[@id='description']//div[text()='New Project']"));
        String actualResultDescription = newDescription.getText();

        Assert.assertEquals(actualResultSummary, expectedResultSummary);
        Assert.assertEquals(actualResultDescription, expectedResultDescription);
    }

    @Ignore
    @Test
    public void testADLearnMore() {
        String expectedResult = "static content of the Wiki";
        WebElement learnMoreHref = getDriver().findElement(By.xpath("//a[@href='https://www.jenkins.io/redirect/distributed-builds']"));
        learnMoreHref.click();
        ArrayList<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windows.get(1));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement atlassianHref = getDriver().findElement(By.xpath("//a[@href='/display/']"));
        atlassianHref.click();
        WebElement wikiJenkinsHref = getDriver().findElement(By.xpath("//a[text()='static content of the Wiki']"));
        String actualResult = wikiJenkinsHref.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }
    @Ignore
    @Test
    public void testAbramovaDropDownList() {
        List<String> expectedResult = Arrays.asList("Builds", "Configure", "My Views", "Credentials");
        WebElement dropDownButton = getDriver().findElement(
               By.xpath("(//button[@class = 'jenkins-menu-dropdown-chevron'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", dropDownButton);
        List<WebElement> folder = getDriver().findElements(By.xpath("//div[@class = 'bd']//a"));
        List<String> actualResult = new ArrayList<>();
        for (int i = 0; i < folder.size(); i++) {
            actualResult.add(folder.get(i).getText());
        }

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(folder.size(), 4);
    }

    @Test
    public void testSHFirstJenkins() {
        String expectedResult = "Project AQA";

        WebElement createAJob = getDriver().findElement(By.xpath("//span[text() = 'Create a job']"));
        createAJob.click();
        WebElement itemName = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        itemName.click();
        itemName.sendKeys("AQA");

        WebElement freestyleProject = getDriver().findElement(
                By.xpath("//span[text() = 'Freestyle project']"));
        freestyleProject.click();
        WebElement buttonOK = getDriver().findElement(By.xpath("//button[@id = 'ok-button']"));
        buttonOK.click();

        WebElement description = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));
        description.click();
        description.sendKeys("Description for AQA");
        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        saveButton.click();
        WebElement createdProjectName = getDriver().findElement(By.xpath("//h1[text() = 'Project AQA']"));

        Assert.assertEquals(createdProjectName.getText(), expectedResult);
    }
}


