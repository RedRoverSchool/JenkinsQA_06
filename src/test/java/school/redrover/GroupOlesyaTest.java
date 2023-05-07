package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupOlesyaTest extends BaseTest {
    private WebDriverWait wait;

    protected WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait;
    }

    @Test
    public void testInputHelpMsg() {
        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();

        String expectedResultMsg = getDriver()
                .findElement(By.xpath("//div[@class = 'input-help']"))
                .getText();

        Assert.assertEquals(expectedResultMsg, "» Required field");
    }

    @Test
    public void testCreateProject() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.xpath("//span[text() ='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[text()='Project Freestyle']")).getText(), "Project Freestyle");
    }

    @Test
    public void testAdminPage() {
        List<String> expectedMenus = Arrays.asList(
                "People", "Status", "Builds", "Configure", "My Views", "Credentials");
        getDriver().findElement(By.xpath("//a[@class='model-link']")).click();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("jenkins")));
        List<WebElement> listMenu = getDriver().findElements(By.className("task-link"));

        for (int i = 0; i < listMenu.size(); i++) {
            Assert.assertEquals(listMenu.get(i).getText(), expectedMenus.get(i));
        }
    }

    @Test
    public void testDescriptionArea() {
        String descriptionXpath = "//a[@id='description-link']";
        String textAreaXPath = "//textarea[@name='description']";
        String saveButtonXPath = "//button[@name='Submit']";
        String descriptionHeader = "//*[@id=\"description\"]/div[1]";

        WebElement addDescription = getDriver().findElement(By.xpath(descriptionXpath));
        addDescription.click();
        WebElement textArea = getDriver().findElement(By.xpath(textAreaXPath));
        getWait().until(ExpectedConditions.visibilityOf(textArea));
        textArea.clear();
        textArea.sendKeys("Testing the description");
        WebElement saveButton = getDriver().findElement(By.xpath(saveButtonXPath));
        saveButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath(descriptionHeader))
                .getText(), "Testing the description");

        addDescription = getDriver().findElement(By.xpath(descriptionXpath));
        addDescription.click();
        textArea = getDriver().findElement(By.xpath(textAreaXPath));
        textArea.clear();
        saveButton = getDriver().findElement(By.xpath(saveButtonXPath));
        saveButton.click();

        Assert.assertEquals(getDriver().findElement(By.xpath(descriptionHeader))
                .getText(), "");
    }

    @Test
    public void createFolderTest() {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys("Folder1");
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-nested-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id=\"main-panel\"]/h1")).getText(), "Folder1");
    }
    @Test
    public void testSearchBarSpecialChars() {

        List<String> symbols = new ArrayList(Arrays.asList("@", "#", "$", "%", "^", "&", "*", "(", ")", "|", "<", ">", "~", "`"));
        for (String symbol : symbols) {
            WebElement searchBox = getDriver().findElement(By.id("search-box"));
            searchBox.click();
            searchBox.clear();
            searchBox.sendKeys(symbol);
            searchBox.sendKeys(Keys.ENTER);
            WebElement wrongSearch = getDriver().findElement(By.xpath("//div[contains(@class,'error')]"));
            Assert.assertEquals("Nothing seems to match.", wrongSearch.getText());
        }
    }


    @Test
    public void testNewProjectCreator() {
        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(),"Welcome to Jenkins!");

        WebElement creator = getDriver().findElement(By.xpath("//span[normalize-space()='Create a job']"));
        creator.click();
        WebElement textField = getDriver().findElement(By.id("name"));
        textField.click();
        textField.sendKeys("New Project");
        WebElement freestyleProject = getDriver().findElement(By.xpath("//span[normalize-space()='Freestyle project']"));
        freestyleProject.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        Assert.assertEquals(getDriver().findElement(By.id("general")).getText(),"General");
        WebElement descriptionField = getDriver().findElement(By.xpath("//textarea[contains(@name,'description')]"));
        descriptionField.sendKeys("New Project");
        WebElement checkBox = getDriver().findElement(By.xpath("//label[normalize-space()='Discard old builds']"));
        checkBox.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']"))
                .getText(),"Project New Project");


    }
}

