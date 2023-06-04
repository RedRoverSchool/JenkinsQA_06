package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseMainHeaderPage;

public class ScriptConsolePage extends BaseMainHeaderPage<ScriptConsolePage> {
    public ScriptConsolePage(WebDriver driver) {
        super(driver);
    }
    public String getTitle(){
        return getDriver().findElement(By.tagName("h1")).getText();
    }
}
