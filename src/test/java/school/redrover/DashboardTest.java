package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    private void moveToElement(WebElement element) {
        Actions actions = new Actions(getDriver());

        actions.moveToElement(element).build().perform();
    }

    private List<String> getTexts(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText());
        }

        return texts;
    }

    @Test(invocationCount = 20)
    public void testMenuIsShownWhenClickingButtonNearProjectName() {
        final List<String> expectedMenus = List.of(
                "Changes",
                "Workspace",
                "Build Now",
                "Configure",
                "Delete Project",
                "Rename"
        );

        createFreestyleProjectWithDefaultConfigurations(UUID.randomUUID().toString());

        WebElement firstProjectInList = getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//table[@id='projectstatus']//tbody/tr"))).get(0);

        WebElement dropDownMenuButton = firstProjectInList.findElement(By.xpath("//td[3]/a/button"));

        moveToElement(dropDownMenuButton);
        getWait10().until(ExpectedConditions.elementToBeClickable(dropDownMenuButton)).click();

        getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("breadcrumb-menu")));

        List<WebElement> menus = getDriver().findElements(By.xpath("//div[@id='breadcrumb-menu']//a//span"));

        Assert.assertEquals(getTexts(menus), expectedMenus);
    }
}
