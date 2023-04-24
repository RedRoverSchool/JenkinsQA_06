package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class CaramelSyrupForJavaTest extends BaseTest {

    @Test
    public void testAbramovaHotKeys() {

        WebElement body = getDriver().findElement(By.tagName("body"));
        body.sendKeys(Keys.chord(Keys.CONTROL, "k"));
        WebElement searchBox = getDriver().findElement(By.xpath("//input[@role]"));
        WebElement currentElement = getDriver().switchTo().activeElement();

        Assert.assertEquals(currentElement, searchBox);
    }

    @Ignore
    @Test
    public void testKhudovaEditDescriptionButtonChanges() {

        WebElement editDescriptionButton = getDriver().findElement(By.id("description-link"));
        editDescriptionButton.click();

        WebElement inputWindow = getDriver().findElement(By.xpath("//textarea[@class = 'jenkins-input   ']"));
        inputWindow.sendKeys("New Description");

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name= 'Submit']"));
        saveButton.click();

        WebElement changedDescriptionButton = getDriver().findElement(By.id("description-link"));
        String newButtonText = changedDescriptionButton.getText();

        Assert.assertEquals(newButtonText, "Edit description");
    }

    @Test
    public void testRykovaEmptyRequiredField(){

        WebElement newItem = getDriver().findElement(By.cssSelector("#side-panel>div>div"));
        newItem.click();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement freestyleProject =
                getDriver().findElement(By.className("hudson_model_FreeStyleProject"));
        freestyleProject.click();

        WebElement error = getDriver().findElement(By.id("itemname-required"));

        WebElement notError =
                getDriver().findElement(By.xpath("//div[@class = 'add-item-name']/div[1]"));

        Assert.assertTrue(error.isDisplayed());
        Assert.assertFalse(notError.isDisplayed());
    }

    @Ignore
    @Test
    public void testDimaKFirst() {
        String expResFol = "Folder";
        String expResName = "First item";

        WebElement newItem = getDriver().findElement(By.cssSelector("#side-panel>div>div"));
        newItem.click();
        WebElement send = getDriver().findElement(By.className("jenkins-input"));
        send.sendKeys("First item");
        WebElement folder = getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']//span"));
        folder.click();
        WebElement okey = getDriver().findElement(By.id("ok-button"));
        okey.click();
        WebElement board = getDriver().findElement(By.xpath("//ol[@id = 'breadcrumbs']//a"));
        board.click();
        Actions act = new Actions(getDriver());
        WebElement boardfold = getDriver().findElement(By.xpath("(//*[name()='svg'][@title='Folder'])[1]\n"));
        act.moveToElement(boardfold).perform();
        WebElement nav = getDriver().findElement(By.className("tippy-content"));
        String actResFol = nav.getText();
        WebElement nameF = getDriver().findElement(By.xpath("//a[@class = 'jenkins-table__link model-link inside']/span"));
        String actResName = nameF.getText();

        Assert.assertEquals(actResFol, expResFol);
        Assert.assertEquals(actResName, expResName);
    }
}


