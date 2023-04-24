package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class TopMenuTest extends BaseTest {

    @Test
    public void testCheckPeopleButton() {
        WebElement buttonPeople = getDriver().findElement(By.linkText("People"));
        boolean actualResult = buttonPeople.isDisplayed();

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testUserButton() {
        WebElement buttonAdmin = getDriver().findElement(By.xpath("//header/div/a[@class = 'model-link']"));
        buttonAdmin.click();

        WebElement userIDDescription = getDriver().findElement(By.xpath("//div[@id='main-panel']//div[contains(text(), " +
                "'Jenkins User ID:')]"));
        String userID = userIDDescription.getText().split(": ")[1];

        Assert.assertEquals(userID, "admin");
    }
}