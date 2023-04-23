package school.redrover;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class JasperGroupTest extends BaseTest {

    @Test
    public void testFindElement() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement searchField = getDriver().findElement(By.xpath("//input[@name = 'q']"));
        searchField.sendKeys("admin");
        searchField.sendKeys(Keys.RETURN);

        WebElement actualResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-panel\"]/div[2]")));
        Assert.assertEquals(actualResult.getText(), "Jenkins User ID: admin");
    }

    @Test
    public void testCreateNewItem() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement createItem = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"));
        createItem.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"name\"]")));
        nameField.sendKeys("New Item");

        WebElement typeOfItems = getDriver().findElement(By.xpath("//*[@class=\"hudson_model_FreeStyleProject\"]"));
        typeOfItems.click();

        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ok-button\"]")));
        okButton.click();

        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"description\"]")));
        descriptionField.sendKeys("New Item");

        WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"jenkins-button apply-button\"]")));
        applyButton.click();

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"jenkins-button jenkins-button--primary \"]")));
        saveButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//*[@class=\"job-index-headline page-headline\"]"));
        Assert.assertEquals(actualResult.getText(),"Project New Item");
    }

    @Test
    public void testValidationOfCreateNewItem(){
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement newItem = getDriver().findElement(By.cssSelector("[href*='/view/all/newJob']"));
        newItem.click();

        WebElement freestyleProject = getDriver().findElement(By.cssSelector("[class*='FreeStyleProject']"));
        freestyleProject.click();

        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));
        WebElement errorText = getDriver().findElement(By.cssSelector("#itemname-required"));

        Assert.assertEquals(okButton.getAttribute("disabled"), "true");
        Assert.assertEquals(errorText.getText(), "» This field cannot be empty, please enter a valid name");
    }
}
