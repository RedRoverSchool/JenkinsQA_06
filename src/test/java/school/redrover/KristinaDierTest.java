package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import java.util.*;

public class KristinaDierTest extends BaseTest {

    @Test
    public void testCheckingBlogLink() {
        getDriver().get("https://compendiumdev.co.uk");
        WebElement blogLinks = getDriver().findElement(By.xpath("//*[@id=\"cssmenu\"]/ul/li[4]/a"));
        blogLinks.click();
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
        String blogPageUrl = getDriver().getCurrentUrl();

        Assert.assertEquals(blogPageUrl, "https://www.eviltester.com/blog/" );
    }

    @Test
    public void testCheckElementsArePresence(){
        getDriver().get("https://compendiumdev.co.uk");
        WebElement mainTitle = getDriver().findElement(By.xpath("//h1"));
        String mainTitleTextActual = mainTitle.getText();
        String mainTitleTextExpected = "Consultancy, Publishing, Digital Marketing and Software Development";

        Assert.assertEquals(mainTitleTextActual,mainTitleTextExpected);
    }

    @Test
    public void testCheckingSubtitlesArePresence(){
        getDriver().get("https://compendiumdev.co.uk");

        List<WebElement> subTitleList  = getDriver().findElements(By.xpath("//h2"));
        int countSubTitles = 4;
        ArrayList<String> actualSubTitletext = new ArrayList<String>( );
        for(WebElement i:subTitleList){
            actualSubTitletext.add(i.getText());
        }

        ArrayList<String> expectedSubTitletext = new ArrayList<String>( );
        expectedSubTitletext.add("Published Books");
        expectedSubTitletext.add("Online Training");
        expectedSubTitletext.add("Featured Application Portfolio");
        expectedSubTitletext.add("Contact");

        Assert.assertEquals(subTitleList.size(),countSubTitles);
        Assert.assertEquals (actualSubTitletext,expectedSubTitletext);
    }
}
