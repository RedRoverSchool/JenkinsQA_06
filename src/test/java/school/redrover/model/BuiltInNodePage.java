package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseMainHeaderPage;

public class BuiltInNodePage extends BaseMainHeaderPage<BuiltInNodePage> {

    public BuiltInNodePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle(){

       return getDriver().findElement(By.xpath("//div[@class='jenkins-app-bar__content']/h1")).getText();
    }
}
