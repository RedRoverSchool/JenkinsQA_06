package school.redrover;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class HeaderTest extends BaseTest {

    @Description("Verify the placeholder text in the search field")
    @Test
    public void testSearchFieldPlaceholder(){
        Assert.assertEquals(getDriver().findElement(By.id("search-box")).getAttribute("placeholder"), "Search (CTRL+K)");
    }

    @Description("Verify the status of autocomplete in the search field")
    @Test
    public void testSearchFieldAutocomplete(){
        Assert.assertEquals(getDriver().findElement(By.id("search-box")).getAttribute("autocomplete"), "off");
    }
}
