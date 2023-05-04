package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Set;


public class GroupGoogleItTest extends BaseTest {

    private void switchToWindow(String windowDescriptor) {
        getDriver().switchTo().window(windowDescriptor);
    }

    @Test
    public void testSimple() {
       WebElement welcomeElement =  getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(), "Welcome to Jenkins!");
    }

    @Test
    public void testIfErrorMessageIsDisplayedWhenAnItemNameContainsPoundAsSecondSymbol() {
        String expectedResult = "Error";
        String folderName = "a#";

        WebElement newItemIcon = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemIcon.click();

        WebElement folder = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Folder']")));
        folder.click();

        WebElement enterAnItemNameBar = getDriver().findElement(By.xpath("//input[@name = 'name']"));
        enterAnItemNameBar.sendKeys(folderName);

        WebElement okButton = getDriver().findElement(By.xpath("//button[@id = 'ok-button']"));
        okButton.click();

        WebElement errorMessage = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(errorMessage.getText(), expectedResult);
    }

    @Test
    public void testTheSearchBarOnTheDashBoardWithNoMatchingResults() {
        String expectedResult = "Nothing seems to match.";

        WebElement searchBarOnDashBoard = getDriver().findElement(By.xpath("//input[@role = 'searchbox']"));
        searchBarOnDashBoard.click();

        searchBarOnDashBoard.sendKeys("Java");
        searchBarOnDashBoard.sendKeys(Keys.ENTER);

        WebElement errorMessage = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'error']")));

        Assert.assertEquals(errorMessage.getText(), expectedResult);

    }

    @Test
    public void testIfTheCorrectPageIsOpenedWhenClickOnJavaConcurrencyOnAboutJenkinsPage() {
        String expectedResult = "https://jcip.net/";
        String window1 = getDriver().getWindowHandle();


        WebElement manageJenkinsLink = getDriver().findElement(By.xpath("//a[@href = '/manage']"));
        manageJenkinsLink.click();

        WebElement aboutJenkinsLink = getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//dt[ text() = 'About Jenkins']")));
        aboutJenkinsLink.click();

        WebElement javaConcurrencyInPracticeLink = getWait2().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[@href = 'http://jcip.net/']")));
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

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void testIfOkButtonIsDisabledWhenAddNewItemWithEmptyName() {
        String expectedResult = "true";
        WebElement newItemIcon = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemIcon.click();

        WebElement okButton = getWait2().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[@id = 'ok-button']")));

        String actualResult = okButton.getAttribute("disabled");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCreateNewFreestyleProject() {
        WebElement addNewItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        addNewItem.click();

        WebElement enterNewJob = getDriver().findElement(By.id("name"));
        enterNewJob.sendKeys("Job1");

        WebElement selectFreestyleProject = getDriver().findElement(By.xpath("//span[text() = 'Freestyle project']"));
        selectFreestyleProject.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();

        WebElement dashboard = getDriver().findElement(By.xpath("//a[@href='/']"));
        dashboard.click();

        WebElement jobTable = getDriver().findElement(By.id("projectstatus"));

        String job = jobTable.findElement(By.linkText("Job1")).getText();

        Assert.assertEquals(job, "Job1");
    }

    @Test
    public void testRenameFolder() {
        String folderName = "Folder1";

        WebElement addNewItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        addNewItem.click();

        WebElement enterNewJob = getDriver().findElement(By.id("name"));
        enterNewJob.sendKeys(folderName);

        WebElement selectFolder = getDriver().findElement(By.xpath("//span[text() = 'Folder']"));
        selectFolder.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.name("Submit"));
        saveButton.click();

        WebElement rename = getDriver().findElement(By.xpath("//a[@href='/job/" + folderName + "/confirm-rename']"));
        rename.click();

        WebElement enterNewName = getDriver().findElement(By.name("newName"));
        enterNewName.clear();
        enterNewName.sendKeys("Folder2");

        WebElement renameButton = getDriver().findElement(By.name("Submit"));
        renameButton.click();

        String folder = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(folder, "Folder2");
    }
}
