package school.redrover;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import java.util.*;

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
    public void testEmptyRequiredFieldRM() {

        WebElement newItem = getDriver().findElement(By.cssSelector("#side-panel>div>div"));
        newItem.click();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement freestyleProject =
                getDriver().findElement(By.className("hudson_model_FreeStyleProject"));
        freestyleProject.click();

        WebElement error = getDriver().findElement(By.id("itemname-required"));

        WebElement notError =
                getDriver().findElement(By.xpath("//div[@class = 'add-item-name']/div[1]"));

        Assert.assertTrue(error.isDisplayed(), "error was not shown");
        Assert.assertFalse(notError.isDisplayed(), "error was shown");
    }

    @Test
    public void testDimaKFirst() {
        String expResFol = "Folder";
        String expResName = "First item";

        WebElement newItem = getDriver().findElement(By.cssSelector("#side-panel>div>div"));
        newItem.click();
        WebElement send = getDriver().findElement(By.className("jenkins-input"));
        send.sendKeys("First item");
        WebElement folder = getDriver().findElement(By.cssSelector(".category:nth-child(2)>ul>li"));
        folder.click();
        WebElement ok = getDriver().findElement(By.id("ok-button"));
        ok.click();
        WebElement board = getDriver().findElement(By.cssSelector("#breadcrumbBar>ol>li>a"));
        board.click();
        Actions act = new Actions(getDriver());
        String actResFol = "";
        WebElement boardFold = getDriver().findElement(By.cssSelector(".jenkins-table__cell__button-wrapper>[tooltip='Folder']"));
        act.moveToElement(boardFold).perform();
        while (actResFol.equals("")) {
            WebElement nav = getDriver().findElement(By.cssSelector("div:nth-child(9)>div>div"));
            actResFol = nav.getText();
        }
        WebElement nameF = getDriver().findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']/span"));
        String actResName = nameF.getText();

        Assert.assertEquals(actResFol, expResFol);
        Assert.assertEquals(actResName, expResName);
    }

    @Ignore
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

    @Test
    public void testSmallSizeIconDimensions() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        Dimension smallDimension = new Dimension(13, 13);

        WebElement buttonPeople = getDriver().findElement(By.xpath("//*[local-name()='svg' and @class='icon-user icon-md']"));
        buttonPeople.click();
        WebElement iconSizeSmall = getDriver().findElement(By.xpath("//a[@class='jenkins-button jenkins-button--tertiary' and @title='Small']"));
        iconSizeSmall.click();
        WebElement iconPerson = getDriver().findElement(By.xpath("//div[@class='jenkins-table__cell__button-wrapper']/*[name()='svg']/*[local-name()='path']"));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(iconPerson)));

        Assert.assertEquals(iconPerson.getRect().getDimension(), smallDimension);
    }

    @Test
    public void testMediumSizeIconDimensions() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        Dimension mediumDimension = new Dimension(16, 16);

        WebElement buttonPeople = getDriver().findElement(By.xpath("//*[local-name()='svg' and @class='icon-user icon-md']"));
        buttonPeople.click();
        WebElement iconSizeMeduim = getDriver().findElement(By.xpath("//a[@class='jenkins-button jenkins-button--tertiary' and @title='Medium']"));
        iconSizeMeduim.click();
        WebElement iconPerson = getDriver().findElement(By.xpath("//div[@class='jenkins-table__cell__button-wrapper']/*[name()='svg']/*[local-name()='path']"));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(iconPerson)));

        Assert.assertEquals(iconPerson.getRect().getDimension(), mediumDimension);
    }

    @Test
    public void testLargeSizeIconDimensions() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        Dimension largeDimension = new Dimension(19, 19);

        WebElement buttonPeople = getDriver().findElement(By.xpath("//*[local-name()='svg' and @class='icon-user icon-md']"));
        buttonPeople.click();
        WebElement iconSizeLarge = getDriver().findElement(By.xpath("//li[@class='jenkins-icon-size__items-item' and @title='Large']"));
        iconSizeLarge.click();
        WebElement iconPerson = getDriver().findElement(By.xpath("//div[@class='jenkins-table__cell__button-wrapper']/*[name()='svg']/*[local-name()='path']"));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(iconPerson)));

        Assert.assertEquals(iconPerson.getRect().getDimension(), largeDimension);
    }

    @Test
    public void testSortByNameItemRM() throws InterruptedException {
        List<String> expectedResult = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            String name = "test " + i;
            expectedResult.add("test " + (4-i));

            getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
            WebElement itemName = getDriver().findElement(By.xpath("//input[@name = 'name']"));
            itemName.click();
            itemName.sendKeys(name);

            getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
            getDriver().findElement(By.id("ok-button")).click();
            getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();
            getDriver().findElement(By.id("jenkins-name-icon")).click();
        }
        getDriver().findElement(By.xpath("//th[@initialsortdir='down']/a")).click();
        List<WebElement> names = getDriver().findElements(By.xpath("//a[@class='jenkins-table__link model-link inside']"));
        List<String> actualResult = Arrays.asList(names.get(0).getText(),names.get(1).getText(), names.get(2).getText());

        Assert.assertEquals(names.size(),expectedResult.size());
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testIconSizeChanges() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));

        WebElement buttonPeople = getDriver().findElement(By.xpath("//*[local-name()='svg' and @class='icon-user icon-md']"));
        buttonPeople.click();
        WebElement iconPerson = getDriver().findElement(By.xpath("//div[@class='jenkins-table__cell__button-wrapper']/*[name()='svg']/*[local-name()='path']"));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(iconPerson)));
        Dimension initialDimension = iconPerson.getRect().getDimension();

        WebElement iconSizeSmall = getDriver().findElement(By.xpath("//a[@class='jenkins-button jenkins-button--tertiary' and @title='Small']"));
        iconSizeSmall.click();
        WebElement smallIconPerson = getDriver().findElement(By.xpath("//*[@class='jenkins-table__cell__button-wrapper']/*[name()='svg']/*[local-name()='path']"));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(smallIconPerson)));
        Dimension changedDimension = smallIconPerson.getRect().getDimension();

        Assert.assertNotEquals(changedDimension, initialDimension);
    }
    @Ignore
    @Test
    public void testAddDel() throws InterruptedException {
        String expRes = "Welcome to Jenkins!";

        getDriver().findElement(By.xpath("//span[text()='New Item']/../..")).click();
        WebElement itemName = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        itemName.click();
        itemName.sendKeys("Project");

        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formNoValidate='formNoValidate']")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();
        getDriver().findElement(By.xpath("//th[@initialsortdir='down']/a")).click();
        getDriver().findElement(By.cssSelector("a[class='jenkins-table__link model-link inside']")).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Project']/..")).click();
        Alert alertOK = getDriver().switchTo().alert();
        alertOK.accept();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(),expRes);
    }
}


