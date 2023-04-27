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
}

