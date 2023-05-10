package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class BreadcrumbTest extends BaseTest {
    @Test
    public void testNavigateToManageJenkinsSection() {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                By.xpath("//*[@id='breadcrumbs']/li/a[@href='/']"))).perform();

        WebElement pointer = getDriver().
                findElement(By.xpath("//a[@href='/']/*[@class='jenkins-menu-dropdown-chevron']"));
        getWait10().until(ExpectedConditions.elementToBeClickable(pointer));
        pointer.click();

        By sectionNameLocator = By.xpath("//*[@id='yui-gen4']/a/span");
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(sectionNameLocator));
        getDriver().findElement(sectionNameLocator).click();

        WebElement manageJenkinsHeader = getDriver().findElement(By.tagName("h1"));
        getWait2().until(ExpectedConditions.visibilityOf(manageJenkinsHeader));

        Assert.assertEquals(manageJenkinsHeader.getText(), "Manage Jenkins");
    }
}
