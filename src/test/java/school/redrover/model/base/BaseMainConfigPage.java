package school.redrover.model.base;

import org.openqa.selenium.WebDriver;
import school.redrover.model.component.MainConfigComponent;

public abstract class BaseMainConfigPage<Self extends BaseConfigPage<?>> extends BaseConfigPage<MainConfigComponent<Self>>{

    public BaseMainConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainConfigComponent <Self> getConfig() {
        return new MainConfigComponent<>((Self)this);
    }
}
