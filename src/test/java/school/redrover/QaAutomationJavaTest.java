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
    public void testjj(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

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

        getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[2]/span/a/span[2]")).click();
    }

    @Test
    public void test (){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement createItem = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"));
        createItem.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"name\"]")));
        nameField.sendKeys("New Item");

        WebElement typeOfItems = getDriver().findElement(By.xpath("//*[@class=\"hudson_model_FreeStyleProject\"]"));
        typeOfItems.click();

        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ok-button\"]")));
        okButton.click();

        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"description\"]")));
        descriptionField.sendKeys("New Item");

        WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"jenkins-button apply-button\"]")));
        applyButton.click();

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"jenkins-button jenkins-button--primary \"]")));
        WebDriverWait wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        saveButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//*[@class=\"job-index-headline page-headline\"]"));
        Assert.assertEquals(actualResult.getText(),"Project New Item");
    }

    @Test
    public void testCheckMenuAfterPushButtonPeople () {
        getDriver().findElement(By.linkText("People")).click();

        WebElement one = getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals(one.getText(),"People");

        List<String> expectedMenu = Arrays.asList("User ID", "Name", "Last Commit Activity", "On");
        /*List < String > dataInTable = new ArrayList<>();
        WebElement userId = getDriver().findElement(By.linkText("User ID"));
        WebElement name = getDriver().findElement(By.xpath("//*[@id=\"people\"]/thead/tr/th[3]/a"));
        WebElement lastCommitActivity = getDriver().findElement(By.xpath("//*[@id=\"people\"]/thead/tr/th[4]/a"));
        WebElement on = getDriver().findElement(By.xpath( "//*[@id=\"people\"]/thead/tr/th[5]/a"));
        dataInTable.add(userId.getText());
        dataInTable.add(name.getText());
        dataInTable.add(lastCommitActivity.getText());
        dataInTable.add(on.getText());*/

        List<WebElement> titles =  getDriver().findElements(By.xpath("//a[@class = 'sortheader']")); // локатор на все элементы в таблице
        List<String> actualMenu = new ArrayList<>();

      /*  for (int i = 0; i < expectedMenu.size(); i++) {
            String actualMenuItems = expectedMenu.get(i);
            //System.out.println(actualMenuItems);
            //System.out.println(dataInTable.size());
            Assert.assertEquals(actualMenuItems, expectedMenu.get(i));
        }*/
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
