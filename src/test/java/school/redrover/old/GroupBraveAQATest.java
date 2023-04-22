package school.redrover.old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
@Ignore
public class GroupBraveAQATest extends BaseTest {

    @Test
    public void testCategoryCardsArePresent() {
        List<String> expectedCardNames = List.of(
                "Elements", "Forms", "Alerts, Frame & Windows", "Widgets", "Interactions", "Book Store Application");

        getDriver().get("https://demoqa.com/");

        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".category-cards")));

        List<WebElement> categoryCards = getDriver().findElements(By.cssSelector(".card.mt-4.top-card"));

        List<String> actualCardNames = new ArrayList<>();
        for (WebElement element : categoryCards) {
            actualCardNames.add(element.getText());
        }

        Assert.assertEquals(actualCardNames, expectedCardNames);
    }

}
