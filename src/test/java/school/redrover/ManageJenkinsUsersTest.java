package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import javax.swing.*;

public class ManageJenkinsUsersTest extends BaseTest {

    @Test
    public void testManageUsers() {

        WebElement dashBoard = getDriver().findElement(By.xpath("//a[contains(text(),'Dashboard')]"));
        Actions actionDashboard = new Actions(getDriver());
        actionDashboard.moveToElement(dashBoard)
                .pause(200)
                .moveToElement(dashBoard.findElement(By.tagName("button")))
                .pause(200)
                .click()
                .pause(200)
                .perform();

        new Actions(getDriver())
                .moveToElement(getDriver()
                    .findElement(By.xpath("//*[@id='breadcrumb-menu-target']//span[contains(text(), 'Manage Jenkins')]")))
                .pause(200)
                .moveToElement(getDriver().findElement(By.xpath("//span[contains(text(),'Manage Users')]")))
                .pause(200)
                .click()
                .perform();

        WebElement users = getDriver().findElement(By.tagName("h1"));
        Assert.assertEquals(users.getText(), "Users");
    }
}
