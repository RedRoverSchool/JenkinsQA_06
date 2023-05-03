package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProjectTest extends BaseTest {

    private static final String FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);
    private static final By GO_TO_DASHBOARD_BUTTON = By.linkText("Dashboard");
    private static final String NEW_FREESTYLE_NAME = RandomStringUtils.randomAlphanumeric(10);

    @Test
    public void testCreateNewFreestyleProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        Assert.assertEquals(getDriver()
                .findElement(By.xpath("//h1")).getText(), "Project " + FREESTYLE_NAME);
    }

    @Test
    public void testDisableProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Project " + FREESTYLE_NAME);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'warning']")).getText().trim().substring(0, 34),
                "This project is currently disabled");
    }


    @Test
    public void testEnableProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        getDriver().findElement(By.cssSelector("button.jenkins-button.jenkins-button--primary ")).click();
        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//span/span/*[name()='svg' and @class= 'svg-icon ']")).getAttribute("tooltip"), "Not built");
    }

    @Test
    public void testFreestyleProjectPageIsOpenedFromDashboard() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();
        getDriver().findElement(By.xpath("//a[@href='job/" + FREESTYLE_NAME + "/']")).click();
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                String.format("Project %s", FREESTYLE_NAME));

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id='main-panel']/h2 ")).getText(),
                "Permalinks");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("h1.job-index-headline.page-headline")).isEnabled());
    }

    @Ignore
    @Test
    public void testAddDescriptionToFreestyleProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        getDriver().findElement(GO_TO_DASHBOARD_BUTTON).click();

        getDriver().findElement(By.xpath("//a[@href='job/" + FREESTYLE_NAME + "/']")).click();
        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//div[@class='setting-main help-sibling']/textarea")).sendKeys("Job " + FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector("button.jenkins-button.jenkins-button--primary")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id = 'description'] /div[1]")).getText(), "Job " + FREESTYLE_NAME);
    }

    @Test
    public void testRenameFreestyleProject() {

        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(FREESTYLE_NAME);
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();

        getDriver().findElement(By.xpath("//a[@href='/job/" + FREESTYLE_NAME + "/confirm-rename']")).click();
        getDriver().findElement(By.cssSelector("input[name='newName']")).clear();
        getDriver().findElement(By.cssSelector("input[name='newName']")).sendKeys(NEW_FREESTYLE_NAME);
        getDriver().findElement(By.xpath("//button[@formnovalidate]")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("h1.job-index-headline.page-headline")).getText(),
                "Project " + NEW_FREESTYLE_NAME);
    }

}
