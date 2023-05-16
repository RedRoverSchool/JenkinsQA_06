package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
public class ManageUsersTest extends BaseTest {
    private final By MANAGE_JENKINS = By.xpath("//a[@href='/manage']");
    private final By MANAGE_USERS = By.xpath("//a[@href='securityRealm/']");
    private final By SUBMIT = By.xpath("//button[@name='Submit']");
    private final By USERS_TABLE = By.id("people");

    @DataProvider(name = "userdata provider")
    public Object[][] provideUserData() {
        return new Object[][]
                {{"JabbaTheHutt", "Hutt", "Hutt", "Jabba", "Jabba@test.com"},
                        {"Chewbacca", "Wookiee", "Wookiee", "Chewie", "Chewie@test.com"}};
    }

    @Test(dataProvider = "userdata provider")
    public void testCreateUser(String username, String password, String confirmPassword, String name, String email) {
        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();
        getDriver().findElement(By.xpath("//a[@href = 'addUser']")).click();
        getDriver().findElement(By.id("username")).sendKeys(username);
        getDriver().findElement(By.xpath("//input[@name='password1']")).sendKeys(password);
        getDriver().findElement(By.xpath("//input[@name='password2']")).sendKeys(confirmPassword);
        getDriver().findElement(By.xpath("//input[@name='fullname']")).sendKeys(name);
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        getDriver().findElement(SUBMIT).click();

        Assert.assertTrue(getWait2().until(ExpectedConditions
                .presenceOfElementLocated(USERS_TABLE)).getText().contains(username));
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testDeleteUserFromUserList() {
        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'chewbacca/delete')]")).click();
        getDriver().findElement(SUBMIT).click();

        Assert.assertFalse(getWait2().until(ExpectedConditions
                .presenceOfElementLocated(USERS_TABLE)).getText().contains("Chewbacca"));
    }

    @Test (dependsOnMethods = {"testCreateUser","testDeleteUserFromUserList"})
    public void testLogInWithDeletedUserCredentials() {
        getDriver().findElement(By.xpath("//a[@href= '/logout']")).click();
        getDriver().findElement(By.id("j_username")).sendKeys("Chewbacca");
        getDriver().findElement(By.xpath("//input[@name='j_password']")).sendKeys("Wookiee");
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//div[contains(@class, 'alert-danger')]")).getText(),
                "Invalid username or password");
    }
    @Ignore
    @Test
    public void testMakeChangesToUserProfile() {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();

        getDriver().findElement(MANAGE_JENKINS).click();
        getDriver().findElement(MANAGE_USERS).click();

        WebElement user = getDriver().findElement(By.xpath("//a[@href='user/mr_churchill/']"));
        new Actions(getDriver()).moveToElement(user).perform();
        WebElement chevron = getDriver().findElement(By
                .xpath("//a[@href='user/mr_churchill/']/button"));
        js.executeScript("arguments[0].click();", chevron);

        getWait2().until(ExpectedConditions.elementToBeClickable(By
            .xpath("//a[@href='/user/mr_churchill/configure']"))).click();

        WebElement fullNameField = getDriver().findElement(By.xpath("//input[@name='_.fullName']"));
        fullNameField.clear();
        fullNameField.sendKeys("Chepchik");
        getDriver().findElement(SUBMIT).click();

        Assert.assertEquals(getDriver().findElement(By
                .xpath("//h1")).getText(), "Chepchik");
    }

}











