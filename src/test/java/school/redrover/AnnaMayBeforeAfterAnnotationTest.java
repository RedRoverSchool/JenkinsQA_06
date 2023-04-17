package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class AnnaMayBeforeAfterAnnotationTest extends BaseTest {

    final String[] navigationLinks = {"Quick Tools", "Send", "Receive", "Shop", "Business", "International", "Help"};

    @Test
    public void testUspsWebsiteTitle() {
        getDriver().get("https://www.usps.com/");
        String actualResult = getDriver().getTitle();
        String expectedResult = "Welcome | USPS";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSearchWord() throws InterruptedException {
        getDriver().get("https://www.usps.com/");
        WebElement searchTextBox = getDriver().findElement(By.xpath("//input[@id='home-input']"));
        searchTextBox.sendKeys("stamps");
        searchTextBox.sendKeys(Keys.RETURN);
        Thread.sleep(2000); //this wait needed for handling processing result and going to another page
        WebElement resultText = getDriver().findElement(By.xpath("//span[@id='recommendedResults']"));
        String actualResult = resultText.getText();
        String expectedResult = "Most Relevant Results for stamps";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testHoverOnSubElement() {
        getDriver().get("https://www.usps.com/");
        WebElement navBarQuickTools = getDriver().findElement(By.xpath("//a[@class='nav-first-element menuitem']"));
        Actions action = new Actions(getDriver());
        action.moveToElement(navBarQuickTools).build().perform();
        WebElement trackAPackage = getDriver().findElement(By.xpath("//p[contains(text(),'Track a Package')]"));
        trackAPackage.click();
        WebElement resultTextTrackAPackage = getDriver().findElement(By.cssSelector("div.subheader_links a:nth-child(1)"));
        String actualResult = resultTextTrackAPackage.getText();
        String expectedResult = "Tracking";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLookupZipPageByAddress() throws InterruptedException {
        getDriver().get("https://www.usps.com/");
        WebElement mailAndSnip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        new Actions(getDriver()).moveToElement(mailAndSnip).perform();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//li[@class='tool-zip']//a")).click();
        getDriver().findElement(By.xpath("//a[contains(@class,'zip-code-address')]")).click();

        String zip = "10005";
        String street = "11 Wall St";
        String city = "New York";
        String state = "NY";

        WebElement stateElement = getDriver().findElement(By.xpath("//select[@id='tState']"));
        new Select(stateElement).selectByValue(state);

        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);
        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();

        getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
        Thread.sleep(3000);

        List<WebElement> list = getDriver().findElements(By.xpath("//*[@class='zipcode-result-address']"));
        for (WebElement item : list) {
            String itemText = item.getText();
            System.out.println(item.getText());
            System.out.println(list.size());

            Assert.assertTrue(itemText.contains(zip), "zip");
        }
    }

    @Test
    public void testVerifyNavigationLinksPresence() {
        getDriver().get("https://www.usps.com/");
        WebElement navigation = getDriver().findElement(By.className("nav-list"));
        List<WebElement> navigationElements = navigation.findElements(By.className("menuheader"));

        for (String link : navigationLinks) {
            boolean found = false;
            for (WebElement element : navigationElements) {
                if (element.getText().equals(link)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found, "Navigation link not found: " + link);
        }
    }
}
