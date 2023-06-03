package school.redrover.model.base;

import org.openqa.selenium.WebDriver;
import school.redrover.model.component.MainHeaderComponent;

public abstract class BaseMainHeaderPage<Self extends BaseMainHeaderPage<?>> extends BasePage<MainHeaderComponent<Self>> {

    public BaseMainHeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainHeaderComponent<Self> getHeader() {
        return new MainHeaderComponent<>( (Self)this);
    }
}
