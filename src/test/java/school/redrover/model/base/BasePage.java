package school.redrover.model.base;

import org.openqa.selenium.WebDriver;


public abstract class BasePage<Header extends BaseComponent<?>> extends BaseModel {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public abstract Header getHeader();
}
