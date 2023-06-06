package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

import java.util.List;

public class ManagePluginsPage extends BaseMainHeaderPage<ManagePluginsPage> {

    public ManagePluginsPage(WebDriver driver) {
        super(driver);
    }
    public List<String> checkFourTasksOnTheLeftsidePanel() {
        List<WebElement> listOfTasks = getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.xpath("//div[@id='tasks']//div[not(contains(@class, 'subtask'))]")));

        return TestUtils.getTexts(listOfTasks);
    }
    public AdvancedSettingsPage clickAdvancedSettings(){
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[4]/span"))
                .click();
        return new AdvancedSettingsPage(getDriver());
    }
}
