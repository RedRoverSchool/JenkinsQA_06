package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import java.util.List;
import java.util.Objects;

public class CreateNewViewTest extends BaseTest {

    private static final By SAVE_BUTTON = By.name("Submit");
    private static final String FOLDER_NAME_1 = "f1";
    private static final String FOLDER_NAME_2 = "f2";
    private static final String VIEW_NAME = "view1";


    public void clickPerform(WebElement webElement) {
        new Actions(getDriver()).click(webElement).perform();
    }

    private void chooseJobsInJobFilters(String name) {
        List<WebElement> viewJobList = getDriver().findElements(By.xpath("//div[@class = 'listview-jobs']/span"));

        for (WebElement el : viewJobList) {
            if (Objects.equals(el.getText(), name)) {
                el.click();
            }
        }
    }

    @Test
    public void testMoveFolderToNewViewList() {
        TestUtils.createFolder(this, FOLDER_NAME_1, true);
        TestUtils.createFolder(this, FOLDER_NAME_2, true);

        getDriver().findElement(By.xpath("//a[@href='/newView']")).click();
        getDriver().findElement(By.id("name")).sendKeys(VIEW_NAME);
        clickPerform(getDriver().findElement(By.id("hudson.model.ListView")));
        getDriver().findElement(SAVE_BUTTON).click();

        chooseJobsInJobFilters(FOLDER_NAME_1);
        getDriver().findElement(SAVE_BUTTON).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'tab active']")).getText(), VIEW_NAME);
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'tab active']")).getText(), VIEW_NAME);
        Assert.assertEquals(getDriver().findElement(By.xpath(String.format("//a[@href='job/%s/']", FOLDER_NAME_1))).getText(), FOLDER_NAME_1);
    }
}
