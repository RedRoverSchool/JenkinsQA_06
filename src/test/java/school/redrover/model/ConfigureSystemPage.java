package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseMainHeaderPage;

public class ConfigureSystemPage extends BaseMainHeaderPage<ConfigureSystemPage> {
    public ConfigureSystemPage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return getDriver().findElement(By.tagName("h1")).getText();
    }
}
