package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FooterJenkinsVersionTest extends BaseTest {
    @Test
    public void testFooterJenkinsVersion() {

        WebElement linkVersion = getDriver().findElement(By.cssSelector("a[target =  '_blank']"));
        Assert.assertEquals(linkVersion.getText(),"Jenkins 2.387.2");
        linkVersion.click();

        for(String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
        WebElement brandJenkins = getDriver().findElement(By.cssSelector(".page-title >span"));
        Assert.assertEquals(brandJenkins.getText(),"Jenkins");

    }

}
