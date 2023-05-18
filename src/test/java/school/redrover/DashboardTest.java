package school.redrover;

import org.openqa.selenium.By;
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

    private void moveToElement(WebElement element) {
        Actions actions = new Actions(getDriver());

        actions.moveToElement(element).perform();
    }

    private List<String> getTexts(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText());
        }

        return texts;
    }

    @Test(invocationCount = 1)
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

        WebElement firstProjectNameInList = getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//table[@id='projectstatus']//tbody/tr/td[3]/a"))).get(0);

        WebElement dropDownMenuButton = firstProjectNameInList.findElement(By.xpath("//button"));

        Actions action = new Actions(getDriver());
        action.moveToElement(firstProjectNameInList).perform();
        System.out.println(dropDownMenuButton.isDisplayed());
        System.out.println(dropDownMenuButton.isEnabled());

        System.out.println(dropDownMenuButton.getAttribute("class"));
        getWait5().until(ExpectedConditions.visibilityOf(dropDownMenuButton));
        System.out.println(dropDownMenuButton.isDisplayed());
        System.out.println(dropDownMenuButton.isEnabled());
        System.out.println(dropDownMenuButton.getAttribute("class"));

        action.moveToElement(dropDownMenuButton).click().build().perform();
//
//        getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='breadcrumb-menu']//a//span")));
//
//        List<WebElement> menus = getDriver().findElements(By.xpath("//div[@id='breadcrumb-menu']//a//span"));
//        System.out.println(getTexts(menus));

//        Assert.assertEquals(getTexts(menus), expectedMenus);
    }
}
