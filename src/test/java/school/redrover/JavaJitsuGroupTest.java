package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class JavaJitsuGroupTest extends BaseTest {

    @Test
    public void testNewItemFolder() {

        WebElement newItemElement = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        newItemElement.click();
        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys("newProject");
        WebElement folder = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']"));
        folder.click();
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));
        okButton.click();
        Assert.assertEquals("General", getDriver().findElement(By.cssSelector("#general")).getText());

        WebElement displayName = getDriver().findElement(By.name("_.displayNameOrNull"));
        displayName.sendKeys("NewTest");
        WebElement description = getDriver().findElement(By.name("_.description"));
        description.sendKeys("Test project to QA Redrover.school");

        WebElement submit = getDriver().findElement(By.name("Submit"));
        submit.click();
        Assert.assertEquals("NewTest", getDriver().findElement(By.cssSelector("div[id='main-panel'] h1")).getText());
    }

    @Test
    public void testNewUser() {

        WebElement manageJenkins = getDriver().findElement(By.cssSelector("a[href='/manage']"));
        manageJenkins.click();
        WebElement manageUsers = getDriver().findElement(By.cssSelector("a[href='securityRealm/']"));
        manageUsers.click();
        WebElement createUserButton = getDriver().findElement(By.xpath(" //a[@class='jenkins-button jenkins-button--primary']"));
        createUserButton.click();

        WebElement username = getDriver().findElement(By.name("username"));
        username.sendKeys("NewUser");
        WebElement password = getDriver().findElement(By.name("password1"));
        password.sendKeys("NewUser12345!");
        WebElement confirmPassword = getDriver().findElement(By.name("password2"));
        confirmPassword.sendKeys("NewUser12345!");
        WebElement fullName = getDriver().findElement(By.name("fullname"));
        fullName.sendKeys("Max Smith");
        WebElement email = getDriver().findElement(By.name("email"));
        email.sendKeys("max@gmail.com");

        WebElement submit = getDriver().findElement(By.name("Submit"));
        submit.click();
        Assert.assertEquals("NewUser", getDriver().findElement(By.xpath("//tbody/tr[2]/td[2]")).getText());
    }
}
