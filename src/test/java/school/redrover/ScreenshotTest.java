package school.redrover;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class ScreenshotTest extends BaseTest {
  @Test
  public void testScreenshot() {

    WebElement newItemMenu = getDriver().findElement(By.xpath("//a[@href = '/view/all/builds']"));
    newItemMenu.click();
    WebElement iconLegendButton = getDriver().findElement(By.xpath("//a[@href = '/legend']"));
    iconLegendButton.click();

    WebElement textProjectHealth = getDriver().findElement(By.xpath("//div/h2[text()='Project Health']"));
    List<WebElement> listProjectHealth = getDriver().findElements(By.xpath("//div/dl[@class='app-icon-legend'][2]/dd"));

    Assert.assertEquals(textProjectHealth.getText(), "Project Health");
    Assert.assertEquals(listProjectHealth.size(), 6);
  }

}
