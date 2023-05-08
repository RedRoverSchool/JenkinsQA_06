package school.redrover;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class NewViewTest extends BaseTest {
    private static final String NEW_VIEW_NAME_RANDOM = RandomStringUtils.randomAlphanumeric(5);

    private void createNewProjectFromMyViewsPage() {
        getDriver().findElement(By.xpath("//a[@href='/me/my-views']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href, '/view/all/newJob')]")).click();
        getDriver().findElement(By.id("name")).sendKeys("First Project");
        getDriver().findElement(By.cssSelector(".hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.xpath("//button[@formnovalidate = 'formNoValidate']")).click();
        getDriver().findElement(By.linkText("Dashboard")).click();
    }

    @Test
    public void testCreateNewView() throws InterruptedException {
        this.createNewProjectFromMyViewsPage();
        getDriver().findElement(By.className("addTab")).click();
        getDriver().findElement(By.id("name")).sendKeys("MyFirstView");
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();
        getDriver().findElement(By.id("ok")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        WebElement projectName = getDriver().findElement(By.linkText("MyFirstView"));

        Assert.assertEquals(projectName.getText(), "MyFirstView");
    }

    @Test
    public void testCreateNewViewSecond(){
        this.createNewProjectFromMyViewsPage();

        getDriver().findElement(By.cssSelector("a.addTab")).click();
        getDriver().findElement(By.cssSelector("input#name")).sendKeys(NEW_VIEW_NAME_RANDOM);
        getDriver().findElement(By.cssSelector("input#hudson\\.model\\.MyView + label")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("div.tab.active")).getText(),NEW_VIEW_NAME_RANDOM);
    }
}