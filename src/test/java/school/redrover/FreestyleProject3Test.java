package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class FreestyleProject3Test extends BaseTest {

    private void createFreestyleProject() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Engineer");
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
    }

    @Test
    public void testCreatedNewBuild() {
        createFreestyleProject();

        getDriver().findElement(By.cssSelector("[href$='Engineer/']")).click();
        getDriver().findElement(By.cssSelector("[href*='build?']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.cssSelector("[href$='console']"))).click();

        WebElement buildIconInBuildHistory = getDriver().findElement(By.cssSelector(".jenkins-icon-adjacent"));
        Assert.assertTrue(buildIconInBuildHistory.isDisplayed());
    }

    @Ignore
    @Test
    public void testProjectAddMultilineTextAndClearAndAddDescription() {
        String expectedResult = "Test";
        createFreestyleProject();

        getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a")).click();
        WebElement description = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        description.click();

        String string = "w";
        for (int i = 0; i < 10; i++) {
            string += string;
        }

        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).sendKeys(string);
        WebElement submit = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        submit.click();

        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).clear();
        getDriver().findElement(By.xpath("//textarea[@class='jenkins-input   ']")).sendKeys("Test");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText(), expectedResult);
    }

    @Ignore
    @Test
    public void testPreviewDescription() {
        String expectedResult = "wwwww";
        createFreestyleProject();

        getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a")).click();
        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[contains(@class, 'jenkins-input')]")).clear();
        getDriver().findElement(By.xpath("//textarea[contains(@class, 'jenkins-input')]")).sendKeys(expectedResult);
        getDriver().findElement(By.xpath("//a[text()='Preview']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='textarea-preview']")).getText(), expectedResult);
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='textarea-preview']")).isDisplayed());
    }

    @Ignore
    @Test
    public void testDeleteProjectFromTheDashboardList() {
        String expectedResult = "Start building your software project";
        createFreestyleProject();

        Actions actions = new Actions(getDriver());
        WebElement nameProject = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a"));
        actions.moveToElement(nameProject).perform();

        WebElement dropdown = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a/button"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", dropdown);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='first-of-type']/li[5]"))).click();
        getDriver().switchTo().alert().accept();

        String actualResult = getDriver().findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testDeleteProjectFromTheProjectPage() {
        String expectedResult = "Start building your software project";
        createFreestyleProject();

        WebElement nameProject = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a"));
        nameProject.click();
        getDriver().findElement(By.xpath("//span[normalize-space(text())='Delete Project' ]")).click();
        getDriver().switchTo().alert().accept();

        String actualResult = getDriver().findElement(By.xpath("//h2")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testRenamingProjectFromTheDashboard() {
        String expectedResultProjectPage = "Project Engineer2";
        String expectedResultDashboardPage = "Engineer2";
        createFreestyleProject();

        Actions actions = new Actions(getDriver());
        WebElement nameProject = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a"));
        actions.moveToElement(nameProject).perform();

        WebElement dropdown = getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a/button"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", dropdown);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='first-of-type']/li[6]"))).click();
        WebElement inputName = getDriver().findElement(By.xpath("//input[@name='newName']"));
        inputName.clear();
        inputName.click();
        inputName.sendKeys("Engineer2");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), expectedResultProjectPage);

        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']/td[3]"))
                .getText(), expectedResultDashboardPage);
    }

    @Test
    public void testRenamingProjectFromTheProjectPage() {
        String newNameProjectOnProjectPage = "Project Engineer2";
        String newNameProjectOnDashboardPage = "Engineer2";
        createFreestyleProject();

        getDriver().findElement(By.xpath("//tr[@class=' job-status-nobuilt']//td[3]/a")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, 'confirm-rename')]")).click();
        WebElement keyboard = getDriver().findElement(By.xpath("//input[@name='newName']"));
        keyboard.clear();
        keyboard.sendKeys("Engineer2");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), newNameProjectOnProjectPage);

        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();

        Assert.assertEquals(getDriver().
                findElement(By.xpath("//tr[@class=' job-status-nobuilt']/td[3]")).getText(), newNameProjectOnDashboardPage);
    }
}
