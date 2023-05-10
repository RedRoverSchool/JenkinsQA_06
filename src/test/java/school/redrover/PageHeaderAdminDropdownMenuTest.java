package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PageHeaderAdminDropdownMenuTest extends BaseTest {

    @Test
    public void testBuildsOpenFromDropdownMenu(){

        Actions act = new Actions(getDriver());

        WebElement dropDownMenu = getDriver().findElement(By.xpath
                ("//a[@href='/user/admin']//button[@class='jenkins-menu-dropdown-chevron']"));
        act.click(dropDownMenu).perform();

        WebElement btnBuilds = getWait2().until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[.='Builds']")));
        btnBuilds.click();

        WebElement pageBuilds = getDriver().findElement(By.xpath("//h1[.='Builds for admin']"));

        Assert.assertTrue(pageBuilds.isDisplayed());
    }
}

