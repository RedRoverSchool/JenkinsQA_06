package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BasePage;

public class BreadcrumbBarPage extends BasePage {

    public BreadcrumbBarPage(WebDriver driver) {
        super(driver);
    }

    public MainPage selectDashboard() {
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
        return new MainPage(getDriver());
    }
}
