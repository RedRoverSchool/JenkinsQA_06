package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Test
    public void testNewItemFolder() {

        WebElement newItemSideBar = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a"));
        newItemSideBar.click();

        WebElement nameField = getDriver().findElement(By.xpath("//*[@id=\"name\"]"));
        nameField.sendKeys("Folder1");

        WebElement folderPageElement = getDriver().findElement(By.xpath("//*[@id=\"j-add-item-type-nested-projects\"]/ul/li[1]"));
        folderPageElement.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
    }
    @Test
    public void verifySidePanel() {

        List<String> expectedResult = Arrays.asList("New Item", "People", "Build History", "Manage Jenkins", "My Views");

        WebElement newItem = getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"));
        WebElement people = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']"));
        WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        WebElement myViews = getDriver().findElement(By.xpath("//a[@href='/me/my-views']"));

        List<String> actualResult = new ArrayList<>();

        actualResult.add(newItem.getText());
        actualResult.add(people.getText());
        actualResult.add(buildHistory.getText());
        actualResult.add(manageJenkins.getText());
        actualResult.add(myViews.getText());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSearchUserAdmin() {

        WebElement searchBox = getDriver().findElement(By.name("q"));
        searchBox.sendKeys("admin");
        searchBox.sendKeys(Keys.ENTER);

        WebElement addDescription = getDriver().findElement(By.xpath("//*[@id=\"description-link\"]"));
        addDescription.click();
        getDriver().findElement(By.name("Submit")).click();
        WebElement searchUserAdmin = getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]"));

        Assert.assertEquals(searchUserAdmin.getText(), "Jenkins User ID: admin");
    }
}
