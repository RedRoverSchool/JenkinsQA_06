package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseMainHeaderPage;

public class PluginsPage extends BaseMainHeaderPage<PluginsPage> {

    public PluginsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getDriver().findElement(By.xpath("//h1")).getText();
    }


}
