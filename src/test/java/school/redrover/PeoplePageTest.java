package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PeoplePageTest extends BaseTest {

    @Test
    public void testViewPeoplePage() {
        WebElement peopleSideMenu = getDriver().findElement(By.xpath("//span/a[@href='/asynchPeople/']"));
        peopleSideMenu.click();
        WebElement nameOfPeoplePageHeader = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(nameOfPeoplePageHeader.getText(), "People");
    }
}
