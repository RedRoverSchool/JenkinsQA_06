package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

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
    public void testMenuIsShownWhenClickingButtonNearProjectName() throws InterruptedException {
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
}
