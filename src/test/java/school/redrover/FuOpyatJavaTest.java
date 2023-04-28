package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;

public class FuOpyatJavaTest extends BaseTest {
    @Test
    public void tesLogoIsDisplayed() {

        WebElement logo = getDriver().findElement(By.id("jenkins-head-icon"));

        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testCreateProject() {

        final String PROJECT_NAME = "Test1";

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        WebElement newItem = getDriver().findElement(By.cssSelector(".task-link "));
        newItem.click();

        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/job/Test1/configure");

        WebElement clickSave = wait.until(ExpectedConditions.elementToBeClickable(By.name("Submit")));
        clickSave.click();
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/job/Test1/");

        WebElement projectName = getDriver().findElement(By.xpath(" //h1[@class= \"job-index-headline page-headline\"]"));
        Assert.assertEquals(projectName.getText(), "Project Test1");

        WebElement deleteProject = getDriver().findElement(By.xpath("//div[6]//span[1]//a[1]"));
        deleteProject.click();

    }

    @Test
    public void testHeaderOfNewItem004() {
        getDriver().findElement(By.linkText("New Item")).click();

        WebElement h3Header = new WebDriverWait(getDriver(), Duration.ofMillis(3000))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class = 'h3']")));
        String actualResult004 = h3Header.getText();

        Assert.assertEquals(actualResult004, "Enter an item name");
    }
    @Test
    public void testPeople() {

        WebElement peopleSideBar = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[2]/span/a"));
        peopleSideBar.click();

        WebElement peoplePageElement = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[1]/div/h1"));
        Assert.assertEquals(peoplePageElement.getText(), "People");
    }
}
