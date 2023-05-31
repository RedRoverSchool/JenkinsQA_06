package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class RestApiPage extends BaseMainHeaderPage<RestApiPage> {

    public RestApiPage(WebDriver driver) {
        super(driver);
    }

    public String getRestApiPageTitle(){
       return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText();
    }

    public boolean isRestApiPageIsOpen() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='main-panel']/h2[contains(text(),'Controlling')]"))).getText()
                .equals("Controlling the amount of data you fetch");
    }

}
