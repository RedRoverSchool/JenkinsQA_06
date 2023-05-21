package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashboardTest extends BaseTest {

    private void createFreestyleProjectWithDefaultConfigurations(String name) {
        getDriver().findElement(By.xpath("//a[@href='newJob']/span[@class='trailing-icon']")).click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(name);
        getDriver().findElement(By.xpath("//label/span[text()='Freestyle project']")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link"))).click();
    }

    private List<String> getTexts(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText());
        }

        return texts;
    }

    @Test()
    public void testMenuIsShownWhenClickingButtonNearProjectName() {
        final String projectName = UUID.randomUUID().toString();
        final List<String> expectedMenus = List.of(
                "Changes",
                "Workspace",
                "Build Now",
                "Configure",
                "Delete Project",
                "Rename"
        );

        createFreestyleProjectWithDefaultConfigurations(projectName);

        WebElement chevron = getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//a[@href='job/" + projectName + "/']/button"))
        );
        chevron.sendKeys(Keys.ENTER);

        List<WebElement> menus = getDriver().findElements(
                By.xpath("//div[@id = 'breadcrumb-menu' and @class = 'yui-module yui-overlay yuimenu visible']//li/a/span"));

        Assert.assertEquals(getTexts(menus), expectedMenus);
    }

    @Test
    public void testReturnToDashboardPage() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("One");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='jenkins-button jenkins-button--primary ']"))).click();
        getDriver().findElement(By.xpath("//a[@class='model-link'][contains(text(),'Dashboard')]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//a[@href='job/One/']")).getText(), "One");
    }

    @Test
    public void testDropDownMenuFromPeoplePage() {
        getDriver().findElement(By.xpath("//a[@href='/asynchPeople/']")).click();

        WebElement dashboardButton = getDriver()
                .findElement(By.xpath("//a[text()='Dashboard']"));
        new Actions(getDriver())
                .moveToElement(dashboardButton)
                .perform();

        getDriver().findElement(By.xpath("//a[text()='Dashboard']/button")).sendKeys(Keys.RETURN);

        WebElement manageJenkinsButton = getDriver()
                .findElement(By.xpath("//a[@class='yuimenuitemlabel yuimenuitemlabel-hassubmenu']"));
        WebElement managePluginsButton = getDriver().findElement(By.xpath("//*[@id='yui-gen8']/a/span"));
        new Actions(getDriver())
                .moveToElement(manageJenkinsButton)
                .pause(Duration.ofSeconds(1))
                .moveToElement(managePluginsButton)
                .click()
                .perform();

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "Plugins");
    }
}
