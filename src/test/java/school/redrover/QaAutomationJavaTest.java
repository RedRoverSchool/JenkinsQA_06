package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QaAutomationJavaTest extends BaseTest {
    @Test
    public void testVerifyWordIconJenkins() {
        WebElement logo = getDriver().findElement(By.id("jenkins-name-icon"));

        Assert.assertTrue(logo.isDisplayed());
    }
    @Test
    public void testvalidateJenkinsLogin() {
        WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(), "Welcome to Jenkins!");
    }
    @Test
    public void testCreatJob() throws InterruptedException {
        Thread.sleep(1000);
        WebElement createJob = getDriver().findElement(By.xpath("//div[@id='tasks']//a[contains(@href, 'newJob')]"));
        createJob.click();

        WebElement newProjectName = getDriver().findElement(By.xpath("//input[@id='name']"));
        newProjectName.sendKeys("First");

        WebElement selectFolder = getDriver().findElement(By.xpath("//span[text()='Organization Folder']/../.."));
        selectFolder.click();

        WebElement buttonOK = getDriver().findElement(By.cssSelector("button.jenkins-button"));
        buttonOK.click();

        WebElement buttonHome = getDriver().findElement(By.id("jenkins-home-link"));
        buttonHome.click();

        WebElement fieldNameInTable = getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span"));

        Assert.assertEquals(fieldNameInTable.getText(), "First");
    }
    @Test
    public void testJobCreation() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        By createJob = By.xpath("//div//a[@href='newJob']");
        WebElement createJobButton = getDriver().findElement(createJob);
        wait.until(ExpectedConditions.visibilityOfElementLocated(createJob));
        createJobButton.click();

        By jenkinsInput = By.xpath("//div//input[@class='jenkins-input']");
        WebElement jenkinsInputButton = getDriver().findElement(jenkinsInput);
        wait.until(ExpectedConditions.visibilityOfElementLocated(jenkinsInput));
        jenkinsInputButton.sendKeys("First_Jenkins_Job");
        Thread.sleep(1000);
        WebElement freeConfigurationButton = getDriver().findElement(By.cssSelector("[class*='hudson_model']"));
        freeConfigurationButton.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        By freeTextIn = By.cssSelector("textarea[name='description']");
        WebElement freeTextInput = getDriver().findElement(freeTextIn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(freeTextIn));
        freeTextInput.sendKeys("First_description is here!");
        WebElement submitButton = getDriver().findElement(By.cssSelector("button[name='Submit']"));
        submitButton.click();

        getDriver().get("http://localhost:8080/");
        WebElement actualResult = getDriver().findElement(By.xpath("//div//td/a/span"));
        String expectedResult = "First_Jenkins_Job";

        Assert.assertEquals(actualResult.getText(),expectedResult);
    }
    @Test
    public void testCheckMenuAfterPushButtonPeople () {
        getDriver().findElement(By.linkText("People")).click();

        WebElement one = getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals(one.getText(),"People");

        List<String> expectedMenu = Arrays.asList("User ID", "Name", "Last Commit Activity", "On");

        List<WebElement> titles =  getDriver().findElements(By.xpath("//a[@class = 'sortheader']")); // локатор на все элементы в таблице
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
}
