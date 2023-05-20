package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject12Test extends BaseTest {

    @DataProvider(name = "specialCharacters")
    public static Object[][] specialCharacters() {
        return new Object[][]{{'&'}, {'>'}, {'<'}, {'!'}, {'@'}, {'#'},
                {'$'}, {'%'}, {'^'}, {'*'}, {'['}, {']'}, {'\\'}, {'|'},
                {';'}, {':'}, {'/'}, {'?'}};
    }

    @Test(dataProvider = "specialCharacters")
    public void testCreateWithInvalidName(Character specialCharacter) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getDriver().findElement(By.id("name")).sendKeys(String.valueOf(specialCharacter));
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        WebElement unsafeCharMessage = getDriver().findElement(By.id("itemname-invalid"));

        Assert.assertEquals(unsafeCharMessage.getText(), String.format("» ‘%s’ is an unsafe character", specialCharacter));
        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
}