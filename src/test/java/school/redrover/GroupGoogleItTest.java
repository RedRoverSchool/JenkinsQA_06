package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;
import java.util.Set;


public class GroupGoogleItTest extends BaseTest {

    private WebElement findFolderElementByTextLabel(String label) {
        List<WebElement> listWebElements = getDriver().findElements(By.xpath("//span[@class = 'label']"));
        WebElement result = null;
        for (WebElement element : listWebElements){
            if (element.getText().equals(label)){
                result = element;
            }
        }
        return result;
    }

    private WebElement findAboutJenkinsElementByText() {
        List<WebElement> listOfWebElements = getDriver().findElements(By.xpath("//div[@class = 'jenkins-section__item']//dl/dt"));
        WebElement result = null;
        for (WebElement element : listOfWebElements){
            if (element.getText().equals("About Jenkins")){
                result = element;
            }
        }
        return result;
    }

    private void switchToWindow(String windowDescriptor) {
        getDriver().switchTo().window(windowDescriptor);
    }

    @Test
    public void testSimple() {
       WebElement welcomeElement =  getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testIfErrorMessageIsDisplayedWhenAnItemNameContainsPoundAsSecondSymbol() throws InterruptedException {
        String expectedResult = "Error";
        String folderName = "a#";
        WebElement newItemIcon = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        Thread.sleep(2000);
        newItemIcon.click();
        Thread.sleep(2000);
        WebElement folder = findFolderElementByTextLabel("Folder");
        folder.click();
        WebElement enterAnItemNameBar = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        enterAnItemNameBar.sendKeys(folderName);
        WebElement okButton = getDriver().findElement(By.xpath("//button[@id = 'ok-button']"));
        okButton.click();
        WebElement errorMessage = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(errorMessage.getText(), expectedResult);
    }

    @Test
    public void TestTheSearchBarOnTheDashBoardWithNoMatchingResults() throws InterruptedException {
        String expectedResult = "Nothing seems to match.";
        Thread.sleep(2000);
        WebElement searchBarOnDashBoard = getDriver().findElement(By.xpath("//input[@role = 'searchbox']"));
        searchBarOnDashBoard.click();
        Thread.sleep(3000);
        searchBarOnDashBoard.sendKeys("Java");
        searchBarOnDashBoard.sendKeys(Keys.ENTER);
        WebElement errorMessage = getDriver().findElement(By.xpath("//div[@class = 'error']"));

        Assert.assertEquals(errorMessage.getText(), expectedResult);

    }

    @Test
    public void TestIfTheCorrectPageIsOpenedWhenClickOnJavaConcurrencyOnAboutJenkinsPage() throws InterruptedException {
        String expectedResult = "https://jcip.net/";
        String window1 = getDriver().getWindowHandle();

        Thread.sleep(3000);

        WebElement manageJenkinsLink = getDriver().findElement(By.xpath("//a[@href = '/manage']"));
        manageJenkinsLink.click();
        Thread.sleep(2000);
        WebElement aboutJenkinsLink = findAboutJenkinsElementByText();
        aboutJenkinsLink.click();
        Thread.sleep(2000);
        WebElement javaConcurrencyInPracticeLink = getDriver().findElement(By.xpath("//a[@href = 'http://jcip.net/']"));
        javaConcurrencyInPracticeLink.click();
        String window2 = "";
        Set<String> currentWindows = getDriver().getWindowHandles();

        for (String window : currentWindows){
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        switchToWindow(window2);

        Thread.sleep(2000);

        getDriver().switchTo();
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testIfOkButtonIsDisabledWhenAddNewItemWithEmptyName() throws InterruptedException {
        String expectedResult = "true";
        WebElement newItemIcon = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        Thread.sleep(2000);
        newItemIcon.click();
        Thread.sleep(2000);
        WebElement okButton = getDriver().findElement(By.xpath("//button[@id = 'ok-button']"));
        String actualResult = okButton.getAttribute("disabled");

        Assert.assertEquals(actualResult, expectedResult);
    }
}
