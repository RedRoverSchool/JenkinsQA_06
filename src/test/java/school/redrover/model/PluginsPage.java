package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;

public class PluginsPage extends BaseMainHeaderPage<PluginsPage> {
    @FindBy(xpath = "//h1")
    private WebElement pageTitle;

    public PluginsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }
}
