package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class FreestyleProject1Test extends BaseTest {
    private static final String NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final String RENAME_NAME = RandomStringUtils.randomAlphanumeric(10);

    private void createFreestyleProject() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys(NAME);

        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
    }

    @Ignore
    @Test
    public void testCreateNewFreestyleProject() {
        createFreestyleProject();

        Assert.assertEquals("Project " + NAME,
                getDriver().findElement(By.xpath("//h1")).getText());
    }

    @Test
    public void testFindNewProjectOnDashboard() {
        createFreestyleProject();

        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();

        Assert.assertEquals(NAME,
                getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span")).getText());
    }

    @Test
    public void testFindNewProjectOnDashboardAndOpen() {
        createFreestyleProject();

        WebElement dashboard = getDriver().findElement(By.xpath("//a[normalize-space()='Dashboard']"));
        dashboard.click();
        WebElement projectIcon = getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']//span"));
        projectIcon.click();

        Assert.assertEquals("Project " + NAME, getDriver().findElement(By.xpath("//h1")).getText());
    }

    @Ignore
    @Test
    public void testCreateFreestyleProjectWithDescription() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement name = getDriver().findElement(By.id("name"));
        name.sendKeys(NAME);

        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();

        WebElement descriptionLine = getDriver().findElement(By.name("description"));
        descriptionLine.sendKeys("Text");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Project " + NAME, getDriver().findElement(By.xpath("//h1")).getText());
    }

    @Ignore
    @Test
    public void testRenameFreestyleProject() {
        createFreestyleProject();

        getDriver().findElement(By.xpath("//a[@href='/job/" + NAME + "/confirm-rename']")).click();

        getDriver().findElement(By.xpath("//input[@name='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@name='newName']")).sendKeys(RENAME_NAME);

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals("Project " + RENAME_NAME, getDriver().findElement(By.xpath("//h1")).getText());
    }

    @Test
    public void testNavigateToChangePage() {
        createFreestyleProject();

        getDriver().findElement(By.xpath("//a[@href='/job/" + NAME + "/changes']")).click();

        Assert.assertEquals("Changes", getDriver().findElement(By.xpath("//h1")).getText());
    }

    @Test
    public void testDeleteProject() {
        createFreestyleProject();

        getDriver().findElement(By.xpath("//span[contains(text(),'Delete Project')]")).click();
        getDriver().switchTo().alert().accept();

        Assert.assertFalse(getDriver().findElements(By.xpath("//a[@class='jenkins-table__link model-link inside']"))
                .stream().map(WebElement::getText).toList().contains(NAME));
    }
}
