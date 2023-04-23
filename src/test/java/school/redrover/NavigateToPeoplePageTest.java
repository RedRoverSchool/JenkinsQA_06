package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class NavigateToPeoplePageTest extends BaseTest {

    @Test
    public void testNavigateToPeoplePage() throws InterruptedException {
        final String expectedPeoplePageUrl = "http://localhost:8080/asynchPeople/";
        final String expectedPeoplePageTitle = "People - [Jenkins]";

        getDriver().findElement(By.xpath("//a[contains(@href, 'People')]")).click();
        Thread.sleep(2000);

        String actualPeoplePageTitle = getDriver().getTitle();
        String actualPeoplePageUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(actualPeoplePageTitle, expectedPeoplePageTitle);
        Assert.assertEquals(actualPeoplePageUrl, expectedPeoplePageUrl);
    }
}
