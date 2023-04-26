package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupUkrTest extends BaseTest {

    @Test
    public void testVerifyJenkinsVersion(){
        WebElement versionJenkins = getDriver().findElement(By.xpath("//div[@class = 'container-fluid']//a[@rel='noopener noreferrer']"));

        Assert.assertEquals(versionJenkins.getText(), "Jenkins 2.387.2");
        }
    }



