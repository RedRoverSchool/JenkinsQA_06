package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class UndercoverGroupHDTest extends BaseTest {

    // Params
    @BeforeMethod
    private void BeforeMethod(){
        setResolution(1280,720);
    }

    // Tests
    @Test
    public void testGoogleSearch() {
        getDriver().get("https://www.google.com/");

        WebElement searchbox = getDriver().findElement(By.name("q"));
        searchbox.sendKeys("selenium\n");

        WebElement text = getDriver().findElement(By.xpath("//h3[text() = 'Selenium']"));
        Assert.assertEquals(text.getText(), "Selenium");
    }
    @Test
    public void testCheckboxClick() {
        getDriver().get("https://crossbrowsertesting.github.io/todo-app.html");

        WebElement checkboxOne = getDriver().findElement(By.name("todo-1"));
        checkboxOne.click();

        WebElement check = getDriver().findElement(By.cssSelector("ul.list-unstyled span.done-true"));
        check.isDisplayed();
    }
}