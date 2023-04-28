package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class Katy1313Test extends BaseTest {

    @Test
    public void testAddDescription() {

        WebElement adminDropDown = getDriver().findElement(By.xpath("//a [@href='editDescription']"));
        adminDropDown.click();
        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));

        Assert.assertTrue(textArea.isDisplayed());
    }

    @Test
    public void testSaveDescription() {

        WebElement addDescriptionButton = getDriver().findElement(By.xpath("//a [@href='editDescription']"));
        addDescriptionButton.click();
        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name = 'description']"));
        textArea.clear();
        textArea.sendKeys("Some text is here");
        WebElement submitButton = getDriver().findElement(By.xpath("//button[@name = 'Submit']"));
        submitButton.click();
        WebElement description = getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]"));

        Assert.assertEquals(description.getText(), "Some text is here");
    }

    @Test
    public void testDropDownCredentials() {

        WebElement dropDownList = getDriver().findElement(By.xpath("//a[@href='/user/admin']//button[@class='jenkins-menu-dropdown-chevron']"));
        Actions act = new Actions(getDriver());
        act.click(dropDownList).perform();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        WebElement credentials = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href = '/user/admin/credentials']")));
        Actions actions = new Actions(getDriver());
        actions.click(credentials).perform();

        WebElement h1CredentialsPage = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(h1CredentialsPage.getText(), "Credentials");
    }

    @Test
    public void testSearchField() {
        WebElement searchField = getDriver().findElement(By.xpath("//*[@id='search-box']"));
        searchField.click();
        searchField.sendKeys("configure");
        searchField.sendKeys(Keys.ENTER);

        WebElement searchResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/div[1]/div[1]/h1"));

        Assert.assertTrue(searchResult.getText().toLowerCase().contains("configure"), "configure");

    }

    @Test
    public void testStatusButtonIsDisplayed() {
        WebElement statusButton = getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]"));

        Assert.assertTrue(statusButton.isDisplayed());

    }

}
