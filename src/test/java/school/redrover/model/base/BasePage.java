package school.redrover.model.base;

import org.openqa.selenium.WebDriver;

public abstract class BasePage<Header, Breadcrumb> extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public abstract Header getHeader();

    public abstract Breadcrumb getBreadcrumb();
}
