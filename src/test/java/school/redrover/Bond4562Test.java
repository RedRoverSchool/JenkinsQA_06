package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class Bond4562Test extends BaseTest {

    private List<String> getText(List<WebElement> WebList) {
        if(WebList.size() > 0) {
            List<String> valueName = new ArrayList<>();
            for (WebElement webElement : WebList) {
                valueName.add(webElement.getText());
            }

            return valueName;
        }

        return List.of("ERRRR");
    }

    @Test
    public void testH2Title() {
        final List<String> expH2Title = List.of(
                "System Configuration", "Security", "Status Information", "Troubleshooting", "Tools and Actions");

        getDriver().findElement(
                By.xpath("//a[@href='/manage']"))
                .click();

        List <WebElement> actH2Title = getDriver().findElements(By.xpath(
                "//h2[@class='jenkins-section__title']"));

        Assert.assertEquals(getText(actH2Title), expH2Title);
    }

}
