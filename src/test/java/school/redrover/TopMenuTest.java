package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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


public class TopMenuTest extends BaseTest {

    @FindBy (xpath = "//span[text()='Freestyle project']")
    private WebElement freestyleProject;
    @FindBy (xpath = "//input[@id='name']")
    private  WebElement newName;
    @FindBy (xpath = "//button[@id='ok-button']")
    private WebElement okButton;
    @FindBy (xpath = "//textarea[@name='description']")
    private WebElement description;
    @FindBy (xpath = "//label[text()='This project is parameterized']")
    private WebElement projectParameter;
    @FindBy (xpath = "//button[text()='Add Parameter']")
    private WebElement addParameter;
    @FindBy (xpath = "//input[@class='jenkins-input']")
    private WebElement listParameter;
    @FindBy (xpath = "//a[text()='File Parameter']")
    private WebElement fileParameter;
    @FindBy (xpath = "//input[@name='parameter.name']")
    private WebElement fileLocation;
    @FindBy (xpath = "//button[@name='Submit']")
    private WebElement saveButton;

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

    public final void clickFreestyleProject() {
        verifyElementVisible(freestyleProject);
        verifyElementIsClickable(freestyleProject).click();
    }

    public final void typeNewName() {
        String text = "FirstFreestyleProject";
        verifyElementVisible(newName);
        verifyElementIsClickable(newName).sendKeys(text);
    }

    public final void clickOkButton() {
        verifyElementVisible(okButton);
        verifyElementIsClickable(okButton).click();
    }

    public final void typeDescription() {
        verifyElementVisible(description);
        verifyElementIsClickable(description).sendKeys("First project");
    }

    public final void clickProjectParameter() {
        verifyElementVisible(projectParameter);
        verifyElementIsClickable(projectParameter).click();
    }

    public final void clickAddParameter() {
        verifyElementVisible(addParameter);
        verifyElementIsClickable(addParameter).click();
    }
    public final void veiwListParameter() {
        verifyElementVisible(listParameter);
    }

    public final void clickFileParameter() {
        verifyElementVisible(fileParameter);
        verifyElementIsClickable(fileParameter).click();
    }

    public final void typeFileLocation() {
        verifyElementVisible(fileLocation);
        verifyElementIsClickable(fileLocation).sendKeys("Home");
    }

    public final void clickSaveButton() {
        verifyElementVisible(saveButton);
        verifyElementIsClickable(saveButton).click();
    }

    @Test
    public void testCheckPeopleButton() {
        WebElement buttonPeople = getDriver().findElement(By.linkText("People"));
        boolean actualResult = buttonPeople.isDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testUserButton() {
        WebElement buttonAdmin = getDriver().findElement(By.xpath("//header/div/a[@class = 'model-link']"));
        buttonAdmin.click();

        WebElement userIDDescription = getDriver().findElement(By.xpath("//div[@id='main-panel']//div[contains(text(), " +
                "'Jenkins User ID:')]"));
        String userID = userIDDescription.getText().split(": ")[1];

        Assert.assertEquals(userID, "admin");
    }

    @Test
    public void testSetOfElementsPeople() {
        WebElement buttonPeople = getDriver().findElement(By.linkText("People"));
        buttonPeople.click();

        WebElement fourElements = getDriver().findElement(By.xpath("//table[@id = \"people\"]"));
        boolean actualResult = fourElements.isDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Ignore
    @Test
    public void testTopMenuUser(){
        WebElement topMenuUser = getDriver().findElement(By.xpath("//span[@class='hidden-xs hidden-sm'][text()='admin']"));

        String actualResult1 = topMenuUser.getText();

        Assert.assertEquals(actualResult1, "admin");
    }

    @Test
    public void testCreateNewItem() {
        WebElement item = getDriver().findElement(By.linkText("New Item"));
        item.click();

        WebElement enterItemNameField = getDriver().findElement(By.xpath("//input[@id='name']"));
        enterItemNameField.sendKeys("TestFirst");

        WebElement folderType = getDriver().findElement(By.xpath("//span[text()='Folder']"));
        folderType.click();

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();

        WebElement displayName = getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"));
        displayName.sendKeys("FirstFolder");

        WebElement description = getDriver().findElement(By.xpath("//textarea[@name='_.description']"));
        description.sendKeys("TestOne");

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();

        WebElement folderArea = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(folderArea.getText(), "FirstFolder");
    }

    @Test
    public void testCreateNewFreestyleProject() {
        String expectedNewProjectName = "FirstFreestyleProject";

        PageFactory.initElements(getDriver(), this);

        WebElement item = getDriver().findElement(By.linkText("New Item"));
        item.click();

        typeNewName();
        clickFreestyleProject();
        clickOkButton();
        typeDescription();
        clickProjectParameter();
        clickAddParameter();
        veiwListParameter();
        clickFileParameter();
        typeFileLocation();
        clickSaveButton();

        String actualNewProjectName = expectedNewProjectName;

        Assert.assertEquals(actualNewProjectName,expectedNewProjectName);

    }

    @Ignore
    @Test
    public void testPreviewOfAddedDescriptionWhenClickUserIDButton() {
        String expectedPreviewOfAddedDescription = "QA Engineer";

        getDriver()
                .findElement(By.xpath("//div[@class=\'login page-header__hyperlinks\']/a[1]/span"))
                .click();
        getDriver()
                .findElement(By.xpath("//a[@id=\'description-link\']"))
                .click();
        getDriver()
                .findElement(By.xpath("//div[@class=\'setting-main help-sibling\']/textarea"))
                .sendKeys("QA Engineer");
        getDriver()
                .findElement(By.xpath("//div[@class=\'textarea-preview-container\']/a[@class=\'textarea-show-preview\']"))
                .click();

        WebElement previewDescription = getDriver().findElement(By.xpath("//div[@class=\'textarea-preview\']"));
        String actualPreviewOfAddedDescription = String.valueOf(previewDescription.getText());

        Assert.assertEquals(actualPreviewOfAddedDescription, expectedPreviewOfAddedDescription);
    }
    @Test
    public void testCheckMenuAfterPushButtonPeople () {
        getDriver().findElement(By.linkText("People")).click();

        WebElement one = getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals(one.getText(),"People");

        List<String> expectedMenu = Arrays.asList("User ID", "Name", "Last Commit Activity", "On");

        List<WebElement> titles =  getDriver().findElements(By.xpath("//a[@class = 'sortheader']"));
        List<String> actualMenu = new ArrayList<>();

        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).getText().contains("↑")) {
                actualMenu.add(titles.get(i).getText().replace("↑", "").trim());
            } else {
                actualMenu.add(titles.get(i).getText());
            }
        }
        Assert.assertEquals(actualMenu, expectedMenu);
    }

    @Test
    public void testVerificationLogOutButton(){
        getDriver().findElement(By.xpath("//a[@href='/logout']")).click();
        WebElement element = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(element.getText(), "Welcome to Jenkins!");
    }
}