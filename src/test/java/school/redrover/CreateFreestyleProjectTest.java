package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.concurrent.TimeUnit;

public class CreateFreestyleProjectTest extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String expectedProjectName = "Project Test";

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys("Test");
        getDriver().findElement(By.xpath("//*[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//*[@class='btn-decorator']")).click();
        getDriver().manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        String actualProjectName = getDriver().findElement(By.xpath("//h1")).getText();

        Assert.assertEquals(actualProjectName, expectedProjectName);
    }

    @Test

    public void testCreateFreestyleProjectWithInvalidName() {
        getDriver().findElement(By.xpath("//a[contains(@href, 'newJob')]")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@class, 'FreeStyleProject')]/label"))).click();

        WebElement inputField = getDriver().findElement(By.xpath("//input[@id='name']"));

        String[] invalidSymbols = {"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "/", "|", "?", "<", ">"};
        for (String invalidSymbol : invalidSymbols) {
            inputField.sendKeys(invalidSymbol);
            String validationMessage = getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText();
            inputField.clear();

            Assert.assertEquals(validationMessage, "» ‘" + invalidSymbol + "’ is an unsafe character");
            Assert.assertFalse(getDriver().findElement(By.xpath("//button[@id='ok-button']")).isEnabled());
        }
    }

    @Test
    public void testCreateFreestyleWithEmptyName() {
        WebElement newItem = getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[href*='/view/all/newJob']")));
        newItem.click();

        WebElement freestyleProject = getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class*='FreeStyleProject']")));
        freestyleProject.click();

        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));
        WebElement errorText = getDriver().findElement(By.cssSelector("#itemname-required"));

        Assert.assertEquals(okButton.getAttribute("disabled"), "true");
        Assert.assertEquals(errorText.getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testCreateFreestyleProject1() {

        String myProjectName = "JenkinsQA";

        WebElement newItemButton = getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']"));
        newItemButton.click();

        WebElement inputField = getDriver().findElement(By.xpath("//input[@id='name']"));
        inputField.sendKeys(myProjectName);
        WebElement iconButton = getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']"));
        iconButton.click();
        WebElement okButton = getDriver().findElement(By.xpath("//button[@id='ok-button']"));
        okButton.click();

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']"));
        saveButton.click();

        WebElement dashboardButton = getDriver().findElement(By.xpath("//a[contains(text(), 'Dashboard')]"));
        dashboardButton.click();

        WebElement projectName = getDriver().findElement(By.xpath("//a/span[contains(text(), 'JenkinsQA')]"));

        Assert.assertEquals(projectName.getText(), myProjectName);
    }

    @Test
    public void testCreateFreestyleProject2(){
        final String testData = "Test";
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(testData);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();

        WebElement projectPage = getDriver().findElement(By.xpath("//h1[normalize-space()='Project Test']"));
        Assert.assertEquals(projectPage.getText(), "Project " + testData);
    }

    @Test
    public void testCreateFolderTestFreestyleProgect() {

        final String nameOfProject = "Project Test1";

       WebElement newItem =  getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
       newItem.click();
       WebElement projectName =   getDriver().findElement(By.xpath("//input[@name = 'name']"));
       projectName.sendKeys("Test1");
       WebElement freestyleProgect = getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']"));
       freestyleProgect.click();
       WebElement buttonOk = getDriver().findElement(By.xpath("//button[@class = 'jenkins-button jenkins-button--primary jenkins-buttons-row--equal-width']"));
       getWait2().until(ExpectedConditions.elementToBeClickable(buttonOk)).click();
       WebElement saveButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
       saveButton.click();
       WebElement projectTest1 = getDriver().findElement(By.xpath("//h1[text() = 'Project Test1']"));

       Assert.assertEquals(projectTest1.getText(),nameOfProject);


    }
}
