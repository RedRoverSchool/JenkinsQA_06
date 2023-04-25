package school.redrover;

import org.openqa.selenium.By;
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


    @FindBy(xpath = "//section[@class='empty-state-section']//span[text()='Create a job']")
    private WebElement createAJobButton;

    @FindBy(xpath = "//div[@id='items']//span[@class='label']")
    private List<WebElement> itemsNameOfLabels;

    @FindBy(xpath = "//button[@id='ok-button']")
    private WebElement okButton;

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
    public void testBuildHistoryText() {
        WebElement buttonBuildHistory = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']"));
        buttonBuildHistory.click();

        WebElement buildHistoryText = getDriver().findElement(By.xpath("//div[@class='jenkins-app-bar__content']/h1"));
        String actualResult = buildHistoryText.getText();
        String expectedResult = "Build History of Jenkins";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testBuildHistoryButton() {
        WebElement buttonBuildHistory = getDriver().findElement(By.linkText("Build History"));
        boolean actualResult = buttonBuildHistory.isDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testUserAdd() {
        WebElement manageJenkinsLink = getDriver().findElement(By.xpath("//a[@href = '/manage']"));
        manageJenkinsLink.click();

        WebElement manageUsersLink = getDriver().findElement(By.xpath("//a[@href = 'securityRealm/']"));
        manageUsersLink.click();

        WebElement createUserLink = getDriver().findElement(By.xpath("//a[@href = 'addUser']"));
        createUserLink.click();

        WebElement userNameField = getDriver().findElement(By.xpath("//input[@name = 'username']"));
        userNameField.sendKeys("UserNameTest");
        WebElement passwordFieild = getDriver().findElement(By.xpath("//input[@name = 'password1']"));
        passwordFieild.sendKeys("qwerty");
        WebElement passwordConfirmField = getDriver().findElement(By.xpath("//input[@name = 'password2']"));
        passwordConfirmField.sendKeys("qwerty");
        WebElement fullNameField = getDriver().findElement(By.xpath("//input[@name = 'fullname']"));
        fullNameField.sendKeys("Jack Black");
        WebElement emailField = getDriver().findElement(By.xpath("//input[@name = 'email']"));
        emailField.sendKeys("JB@jb.tre");
        WebElement createUserButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        createUserButton.click();

        List<WebElement> listOfUsers = getDriver().findElements(By.xpath("//table[@id = 'people']/tbody/tr/td[2]"));
        String actualResult = null;
        for (WebElement listOfUser : listOfUsers) {
            if (listOfUser.getText().equals("UserNameTest")) {
                actualResult = listOfUser.getText();
            }
        }

        Assert.assertEquals(actualResult, "UserNameTest");
    }
}