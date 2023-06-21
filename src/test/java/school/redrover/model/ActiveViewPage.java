package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class ActiveViewPage extends BaseMainHeaderPage<ActiveViewPage> {

    @FindBy(xpath = "//div[@class = 'tab active']")
    private WebElement activeViewName;

    public ActiveViewPage(WebDriver driver) {
        super(driver);
    }

    public String getActiveViewName() {

        return TestUtils.getText(this, activeViewName);
    }
}
