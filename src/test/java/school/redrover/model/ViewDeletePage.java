package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ViewDeletePage extends BaseMainHeaderPage<ViewDeletePage> {

    public ViewDeletePage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickYes() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        return new MainPage(getDriver());
    }

    public MyViewsPage clickYesButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        return new MyViewsPage(getDriver());
    }
}
