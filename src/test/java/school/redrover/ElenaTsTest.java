package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.util.ArrayList;


public class ElenaTsTest extends BaseTest {

    @Test
    public void testCheckJenkinsVersion(){
        WebElement jenkinsVersion = getDriver().findElement(By.xpath("//div[@class='page-footer__links page-footer__links--white jenkins_ver']/a"));

        Assert.assertEquals(jenkinsVersion.getText(), "Jenkins 2.387.2");
    }

    @Test
    public void testNavigationMenu(){
        WebElement newItemButton = getDriver().findElement(By.xpath("//span[text()='New Item']"));
        WebElement peopleButton = getDriver().findElement(By.xpath("//span[text()='People']"));
        WebElement buildHistoryButton = getDriver().findElement(By.xpath("//span[text()='Build History']"));
        WebElement manageJenkinsButton = getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']"));
        WebElement myViewsButton = getDriver().findElement(By.xpath("//span[text()='My Views']"));

        ArrayList<String> actualResult = new ArrayList<>();
        actualResult.add(newItemButton.getText());
        actualResult.add(peopleButton.getText());
        actualResult.add(buildHistoryButton.getText());
        actualResult.add(manageJenkinsButton.getText());
        actualResult.add(myViewsButton.getText());

        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("New Item");
        expectedResult.add("People");
        expectedResult.add("Build History");
        expectedResult.add("Manage Jenkins");
        expectedResult.add("My Views");

        Assert.assertEquals(actualResult, expectedResult);
    }
}

