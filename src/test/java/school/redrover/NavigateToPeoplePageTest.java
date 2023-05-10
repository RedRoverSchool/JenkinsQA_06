package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NavigateToPeoplePageTest extends BaseTest {

    @Test
    public void testNavigateToPeoplePage() throws InterruptedException {
        final String expectedPeoplePageTitle = "People - [Jenkins]";
        final String expectedPeoplePageText = "People";

        getDriver().findElement(By.xpath("//a[contains(@href, 'People')]")).click();
        Thread.sleep(2000);

        String actualPeoplePageTitle = getDriver().getTitle();
        String actualPeoplePageText = getDriver().findElement(By.xpath("//h1[contains(text(),'People')]"))
                .getText();

        Assert.assertEquals(actualPeoplePageTitle, expectedPeoplePageTitle);
        Assert.assertEquals(actualPeoplePageText, expectedPeoplePageText);
    }
}
