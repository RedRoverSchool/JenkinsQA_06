package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class BreadcrumbBarComponent extends BasePage {

    public BreadcrumbBarComponent(WebDriver driver) {
        super(driver);
    }

    public MainPage selectDashboard() {
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
        return new MainPage(getDriver());
    }

    public String getValueAttrElement(WebElement element, String attr) {
        String value = element.getAttribute(attr);
        return value;
    }
}
