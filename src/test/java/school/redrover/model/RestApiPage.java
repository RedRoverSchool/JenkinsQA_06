package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class RestApiPage extends BaseMainHeaderPage<RestApiPage> {

    @FindBy(xpath = "//h1")
    private WebElement title;

    public RestApiPage(WebDriver driver) {
        super(driver);
    }

    public String getRestApiPageTitle(){
       return getWait2().until(ExpectedConditions.elementToBeClickable(title)).getText();
    }
}
