package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestWaitOnDropDown extends BaseTest {

    public List<String> getNamesOfLists(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();

        for (WebElement element : elements) {
            texts.add(element.getText());
        }

        return texts;
    }

    @Test
    public void testTopMenuUserDropDown() {
        Actions actions = new Actions(getDriver());
        WebElement topMenuUserDropDown = getDriver().findElement(By.xpath("//div/a[@class='model-link']/button[@class='jenkins-menu-dropdown-chevron']"));
        actions.click(topMenuUserDropDown).perform();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href = '/user/admin/configure']")));
        List<WebElement> listTopElements = getDriver().findElements(By.xpath("//li[@groupindex = '0']/a/span"));
        System.out.println("OLEGTEST: " + listTopElements.size());
        List<String> expected = Arrays.asList("Builds", "Configure", "My Views", "Credentials");
        List<String> actual = getNamesOfLists(listTopElements);

        Assert.assertEquals(actual,expected);
    }
}
