package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.ArrayList;
import java.util.List;

public class ConfigureSystemPage extends BaseMainHeaderPage<ConfigureSystemPage> {
    public ConfigureSystemPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {

        return getDriver().findElement(By.xpath("//h1")).getText();
    }


}
