package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class JavaExpertsNationTest extends BaseTest {
    @Test
    public void testManageTitle() {
        getDriver().findElement(By.cssSelector("a[href='/manage']")).click();

        WebElement manageTitle = getDriver().findElement(By.tagName("h1"));
        WebElement securityTitle = getDriver().findElement(By.cssSelector("#main-panel section:nth-child(6) h2"));

        Assert.assertEquals(manageTitle.getText(), "Manage Jenkins");
        Assert.assertEquals(securityTitle.getText(), "Security");

        WebElement systemConfigurationTitle = getDriver().
                findElement(By.xpath("//*[@id=\"main-panel\"]/section[2]/h2"));

        Assert.assertEquals(systemConfigurationTitle.getText(), "System Configuration");
    }
    @Test
    public void testPeopleTitle() {
        WebElement people = getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']"));
        people.click();

        WebElement peopleTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(peopleTitle.getText(), "People");
    }

    @Test
    public void testBuildHistoryTitle() {
        WebElement buildHistory = getDriver().findElement(By.xpath("//a[@href='/view/all/builds']"));
        buildHistory.click();

        WebElement peopleTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(peopleTitle.getText(), "Build History of Jenkins");
    }
    @Test
    public void testAddDescription(){
        WebElement addDescLink = getDriver().findElement(By.id("description-link"));
        addDescLink.click();

        WebElement inputField = getDriver().findElement(By.cssSelector("textarea[name = 'description']"));
        inputField.clear();

        String text = "Some description";
        inputField.sendKeys(text);

        WebElement saveButton = getDriver().findElement(By.cssSelector("button[name='Submit']"));
        saveButton.click();

        WebElement actualResult = getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
        Assert.assertEquals(actualResult.getText(), text);

    }

    @Test
    public void testAddNewItem() {
        WebElement newItem = getDriver().findElement(By.cssSelector("a[href='/view/all/newJob']"));
        newItem.click();

        WebElement inputField = getDriver().findElement(By.id("name"));
        String firstJob = "First Job";
        inputField.sendKeys(firstJob);

        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();
        WebElement projectTitle = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(projectTitle.getText(), "Project " + firstJob, "projectTitle does not match inputted text");
    }
}
