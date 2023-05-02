package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.Arrays;
import java.util.List;


public class ElenaTsTest extends BaseTest {

    @Test
    public void testCheckJenkinsVersion(){
        WebElement jenkinsVersion = getDriver().findElement(By.xpath("//div[@class='page-footer__links page-footer__links--white jenkins_ver']/a"));

        Assert.assertEquals(jenkinsVersion.getText(), "Jenkins 2.387.2");
    }

    @Test
    public void testNavigationMenu(){
        List<WebElement> navigationMenuItem = getDriver().findElements(By.xpath("//span[@class='task-link-text']"));

        List<String> expectedResult = Arrays.asList("New Item","People","Build History","Manage Jenkins","My Views");

        for (int i = 0; i < navigationMenuItem.size(); i++) {
            String actualResult = navigationMenuItem.get(i).getText();

            Assert.assertEquals(actualResult, expectedResult.get(i));
        }
    }

    @Test
    public void testCreateJobWithEmptyItemNameField() throws InterruptedException {
        WebElement createJobLink = getDriver().findElement(By.linkText("Create a job"));
        createJobLink.click();
        Thread.sleep(500);
        WebElement freeStyleProjectField = getDriver().findElement(By.xpath("//li[contains(@class,'hudson_model_FreeStyleProject')]"));
        freeStyleProjectField.click();
        Thread.sleep(500);
        WebElement emptyItemNameFieldErrorMessage = getDriver().findElement(By.id("itemname-required"));

        Assert.assertEquals(emptyItemNameFieldErrorMessage.getText(), "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testCreateJobWithEnterSpecialCharactersInItemNameField() throws InterruptedException {
        WebElement createJobLink = getDriver().findElement(By.linkText("Create a job"));
        createJobLink.click();

        Thread.sleep(1000);
        WebElement itemNameField = getDriver().findElement(By.id("name"));
        List<String>specialCharacters= Arrays.asList("@","/","$","*","%","&");
        for (String specialCharacter : specialCharacters) {
            itemNameField.sendKeys(specialCharacter);

            WebElement warningMessage = getDriver().findElement(By.id("itemname-invalid"));
            Thread.sleep(500);

            String actualResultWarningMessage = warningMessage.getText();
            itemNameField.clear();

            Assert.assertEquals(actualResultWarningMessage, "» ‘" + specialCharacter + "’ is an unsafe character");
        }
    }

    @Test
    public void testCreateJobsList() throws InterruptedException {
        getDriver().findElement(By.linkText("New Item")).click();
        Thread.sleep(500);
        List<String> itemName = Arrays.asList("1","2","3");
        for (String number:itemName){
            getDriver().findElement(By.id("name")).sendKeys(number);

            getDriver().findElement(By.xpath("//*[text()='Freestyle project']")).click();
            getDriver().findElement(By.id("ok-button")).click();
            Thread.sleep(500);
            getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
            Thread.sleep(500);
            getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
            getDriver().findElement(By.linkText("New Item")).click();
        }
        getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();

        List<WebElement> listOfJobs =getDriver().findElements(By.xpath("//a[contains(@class,'jenkins-table__link')]/span"));

        Assert.assertEquals(listOfJobs.size(), itemName.size());
    }

    @Test
    public void testCreateJobsWithTheSameName() throws InterruptedException {
        getDriver().findElement(By.linkText("New Item")).click();
        Thread.sleep(500);
        List<String> itemName = Arrays.asList("1","1");
        for (String name:itemName){
            getDriver().findElement(By.id("name")).sendKeys(name);

            WebElement warningMassage = getDriver().findElement(By.id("itemname-invalid"));
            if (warningMassage.isDisplayed()){

                Assert.assertTrue(warningMassage.isDisplayed(), "A job already exists with this name");
            }
            else {
            getDriver().findElement(By.xpath("//*[text()='Freestyle project']")).click();
            getDriver().findElement(By.id("ok-button")).click();

            Thread.sleep(500);
            getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
            Thread.sleep(500);
            getDriver().findElement(By.xpath("//a[text()='Dashboard']")).click();
            getDriver().findElement(By.linkText("New Item")).click();}
            }
        }
    }

