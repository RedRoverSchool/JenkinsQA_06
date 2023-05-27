package school.redrover.model.base;

import org.openqa.selenium.WebDriver;
import school.redrover.model.component.MainHeaderComponent;

public abstract class BaseMainHeaderPage extends BasePage<MainHeaderComponent> {

    public BaseMainHeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainHeaderComponent getHeader() {
        return new MainHeaderComponent(getDriver());
    }
}
