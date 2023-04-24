package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupJavaciraptorsTest extends BaseTest {
    @Test
    public void simpleTest() {
       WebElement welcomeElement = getDriver().findElement(By.xpath("//div[@class = 'empty-state-block']/h1"));

        Assert.assertEquals(welcomeElement.getText(),"Welcome to Jenkins!");
    }

    @Test
    public void savichevTest() {
        WebElement versionElement = getDriver().findElement(By.xpath("//a[normalize-space()='Jenkins 2.387.2']"));
        Assert.assertEquals(versionElement.getText(), "Jenkins 2.387.2");

        WebElement textElement = getDriver().findElement(By.xpath("//h2[normalize-space()='Set up a distributed build']"));
        Assert.assertEquals(textElement.getText(), "Set up a distributed build");
    }
}
