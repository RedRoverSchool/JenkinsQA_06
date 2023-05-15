package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProject26Test extends BaseTest {
    private static final String NAME_FREESTYLE_PROJECT = "freestyle";

    @Ignore
    @Test
    public void testCreate() {

        WebElement newItemSideBar = getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a"));
        newItemSideBar.click();

        WebElement nameField = getDriver().findElement(By.xpath("//*[@id='name']"));
        nameField.sendKeys(NAME_FREESTYLE_PROJECT);

        WebElement freestyleProjectElement = getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]"));
        freestyleProjectElement.click();

        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("Submit")).click();

        WebElement h1Element = getDriver().findElement(By.xpath("//*[@id='main-panel']/h1"));
        Assert.assertEquals(h1Element.getText(), "Project " + NAME_FREESTYLE_PROJECT);
    }
}
