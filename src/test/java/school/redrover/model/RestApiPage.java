package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class RestApiPage extends BasePage {

    public RestApiPage(WebDriver driver) {
        super(driver);
    }

    public WebElement restApiPageTitle(){
       return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
    }
}
