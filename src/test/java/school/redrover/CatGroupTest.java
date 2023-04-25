package school.redrover;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatGroupTest extends BaseTest {

    @FindBy(xpath = "//a[@href='newJob']")
    private WebElement createAJobButton;

    @FindBy(xpath = "//div[@id='items']//span[@class='label']")
    private List<WebElement> itemsNameOfLabels;

    @FindBy(xpath = "//button[@id='ok-button']")
    private WebElement okButton;
    @FindBy(xpath = "//div[@class='add-item-name']/input[@id='name']")
    private WebElement inputFieldToCreateJob;
    @FindBy(xpath = "//span[text()='Freestyle project']")
    private WebElement sectionFreestyleProject;
    @FindBy(xpath = "//button[@id='ok-button']")
    private WebElement submitButton;
    @FindBy(xpath = "//button[@class='jenkins-button jenkins-button--primary ']")
    private WebElement saveButton;
    @FindBy(xpath = "//h1[@class='job-index-headline page-headline']")
    private WebElement h1CreatedProject;
    @FindBy(xpath = "//a[@href='https://www.jenkins.io/']")
    private WebElement versionOfJenkins;
    @FindBy(xpath = "//div[@id='tasks']//div[4]/span")
    private WebElement manageJenkinsButton;

    public WebDriverWait webDriverWait10;


    public final WebDriverWait getWait10() {
        if (webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return webDriverWait10;
    }

    public final void verifyElementVisible(WebElement element) {

        getWait10().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement verifyElementIsClickable(WebElement element) {
        return getWait10().until(ExpectedConditions.elementToBeClickable(element));
    }

    public final void clickCreateAJobButton() {
        verifyElementVisible(createAJobButton);
        verifyElementIsClickable(createAJobButton).click();
    }

    public final void clickManageJenkinsButton() {
        verifyElementVisible(manageJenkinsButton);
        verifyElementIsClickable(manageJenkinsButton).click();
    }

    public void scrollByElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public int getListSize(List<WebElement> elements) {
        return elements.size();
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public List<String> getNamesOfLists(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(getText(element));
        }

        return texts;
    }
    public void printNameOfProject(){
        String inputText = "Project";
        verifyElementVisible(inputFieldToCreateJob);
        verifyElementIsClickable(inputFieldToCreateJob).sendKeys(inputText);
    }
    public void clickFreestyleProject(){
        verifyElementIsClickable(sectionFreestyleProject).click();
    }
    public void clickSubmitButton(){
        verifyElementVisible(submitButton);
        verifyElementIsClickable(submitButton).click();
    }
    public void clickSaveButton(){
        verifyElementVisible(saveButton);
        verifyElementIsClickable(saveButton).click();
    }
    public String getH1CreatedProject(){
        verifyElementVisible(h1CreatedProject);
        return getText(h1CreatedProject);
    }

    @Test
    public void testNameOfItemsOfLabels() {

        getDriver().manage().window().maximize();

        final List<String> expectedNamesOfItems = Arrays.asList("Freestyle project", "Pipeline",
                "Multi-configuration project", "Folder", "Multibranch Pipeline", "Organization Folder");

        PageFactory.initElements(getDriver(), this);
        clickCreateAJobButton();

        verifyElementVisible(okButton);
        List<String> actualNameOfItems = getNamesOfLists(itemsNameOfLabels);

        Assert.assertEquals(actualNameOfItems, expectedNamesOfItems);
    }

    @Test
    public void testH1ContainsNameOfNewPriject(){
        String expectedH1NameOfProject = "Project Project";

        PageFactory.initElements(getDriver(), this);
        clickCreateAJobButton();
        printNameOfProject();
        clickFreestyleProject();
        clickSubmitButton();
        clickSaveButton();
        String actualH1NameOfProject = getH1CreatedProject();

        Assert.assertEquals(actualH1NameOfProject,expectedH1NameOfProject);
    }

    @Test
    public void testVersionOfJenkins() {

        final String expectedVersionOfJenkins = "Jenkins 2.387.2";
        PageFactory.initElements(getDriver(), this);
        clickManageJenkinsButton();
        getWait10();
        scrollByElement(versionOfJenkins);
        getWait10();
        String actualVersionOfJenkins = versionOfJenkins.getText();

        Assert.assertEquals(actualVersionOfJenkins, expectedVersionOfJenkins);
    }
}