package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class ActiveViewPage extends BaseMainHeaderPage<ActiveViewPage> {
    public ActiveViewPage(WebDriver driver) {
        super(driver);
    }

    public String getActiveViewName() {

        return TestUtils.getText(this, getDriver().findElement(By.xpath("//div[@class = 'tab active']")));
    }

}
