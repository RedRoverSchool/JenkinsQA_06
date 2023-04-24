package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;
@Ignore
public class UndercoverGroupTest extends BaseTest {

    // Params
    @BeforeMethod
    private void BeforeMethod(){
        //setResolution(2560,1440);
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
    public void testGoogleSearchWithRETURN() {
        getDriver().get("https://google.com");

        WebElement searchField = getDriver().findElement(By.name("q"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.RETURN);

        WebElement actual = getDriver().findElement(By.xpath("//h3[text() = \"Selenium\"]"));

        Assert.assertEquals(actual.getText(), "Selenium");
    }

    @Test
    public void testCheckboxClick() {
        getDriver().get("https://crossbrowsertesting.github.io/todo-app.html");

        WebElement checkboxOne = getDriver().findElement(By.name("todo-1"));
        checkboxOne.click();

        WebElement check = getDriver().findElement(By.cssSelector("ul.list-unstyled span.done-true"));
        check.isDisplayed();
    }

    @Test
    public void testDragAndDrop() {
        getDriver().get("https://crossbrowsertesting.github.io/drag-and-drop.html");

        WebElement element1 = getDriver().findElement(By.id("draggable"));
        WebElement element2 = getDriver().findElement(By.id("droppable"));

        Actions action = new Actions(getDriver());

        action.dragAndDrop(element1, element2).build().perform();
    }

    @Test
    public void seleniumWebFormTest() throws InterruptedException {

        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");

        assertEquals("Web form", getDriver().getTitle());

        Thread.sleep(2000);

        WebElement password = getDriver().findElement(By.name("my-password"));
        password.click();
        password.sendKeys("123456");

        WebElement textArea = getDriver().findElement(By.name("my-textarea"));
        textArea.click();
        textArea.sendKeys("lorem ipsum ...");

        Select dropdown = new Select(getDriver().findElement(By.className("form-select")));
        dropdown.selectByVisibleText("Two");

        WebElement checkbox1 = getDriver().findElement(By.id("my-check-1"));
        if(checkbox1.isDisplayed()){
            checkbox1.click();
        }
        WebElement checkbox2 = getDriver().findElement(By.id("my-check-2"));
        if(checkbox2.isDisplayed()){
            checkbox2.click();
        }
        assertFalse(getDriver().findElement(By.id("my-check-1")).isSelected());
        assertTrue(getDriver().findElement(By.id("my-check-2")).isSelected());

        getDriver().findElement(By.cssSelector("button")).click();

        WebElement message = getDriver().findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);
    }
}