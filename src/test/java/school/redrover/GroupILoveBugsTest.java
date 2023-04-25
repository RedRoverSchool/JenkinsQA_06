package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class GroupILoveBugsTest extends BaseTest {

    @Test
    public void testVerifySearchbarIsPresent() {
        WebElement searchBar = getDriver().findElement(By.id("search-box"));
        Assert.assertTrue(searchBar.isDisplayed());
    }
}
