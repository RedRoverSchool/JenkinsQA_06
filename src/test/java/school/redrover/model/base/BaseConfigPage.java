package school.redrover.model.base;

import org.openqa.selenium.WebDriver;

public abstract class BaseConfigPage<Config extends BaseComponent<?>> extends BaseMainHeaderPage <BaseMainHeaderPage<?>>{

    public BaseConfigPage(WebDriver driver) {
        super(driver);
    }

    public abstract Config getConfig();


}
