package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import static org.testng.Assert.assertEquals;
import jdk.jfr.Description;

public class HeaderTest extends BaseTest {
    @Test
    public void testHeaderLogoIcon() {
        WebElement logoIcon = getDriver().findElement(By.xpath("//*[@id=\"jenkins-head-icon\"]"));
        Assert.assertTrue(logoIcon.isDisplayed());
    }

    @Test
    public void testHeaderLogoText() {
        WebElement logoText = getDriver().findElement(By.xpath("//*[@id=\"jenkins-name-icon\"]"));
        Assert.assertTrue(logoText.isDisplayed());
    }

    @Test
    public void testSearchTextField() throws InterruptedException {
        Actions hover = new Actions(getDriver());

        WebElement searchTextBox = getDriver().findElement(By.xpath("//*[@id=\"search-box\"]"));
        WebElement searchIcon = getDriver().findElement(By.cssSelector(".main-search__icon-leading svg"));
        WebElement helpButton = getDriver().findElement(By.xpath("//*[@id=\"searchform\"]/a"));
        WebElement helpButtonIcon = getDriver().findElement(By.cssSelector(".main-search__icon-trailing svg"));

        String placeholder = searchTextBox.getAttribute("placeholder");
        String defaultHelpButtonColor = helpButton.getCssValue("color");

        Assert.assertTrue(searchIcon.isDisplayed());
        assertEquals(placeholder, "Search (CTRL+K)");
        Assert.assertTrue(helpButtonIcon.isDisplayed());
        assertEquals(defaultHelpButtonColor,"rgba(115, 115, 140, 1)");

        hover.moveToElement(helpButton).perform();
        Thread.sleep(500);
        String hoverHelpButtonColor = helpButton.getCssValue("color");

        assertEquals(hoverHelpButtonColor, "rgba(64, 64, 64, 1)");
    }

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
