package school.redrover.model.base;

import org.openqa.selenium.WebDriver;
import school.redrover.model.component.MainBreadcrumbComponent;
import school.redrover.model.component.MainHeaderComponent;

public abstract class BaseMainHeaderPage<Self extends BaseMainHeaderPage<?>> extends BasePage<MainHeaderComponent<Self>, MainBreadcrumbComponent<Self>> {

    public BaseMainHeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainHeaderComponent<Self> getHeader() {
        return new MainHeaderComponent<>( (Self)this);
    }

    @Override
    public MainBreadcrumbComponent<Self> getBreadcrumb() {
        return new MainBreadcrumbComponent<>( (Self)this);
    }

}
