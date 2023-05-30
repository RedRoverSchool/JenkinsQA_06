package school.redrover.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseComponent;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BasePage;

public class MainConfigComponent<Page extends BasePage<?>> extends BaseComponent<Page> {
    public MainConfigComponent(Page page) {
        super(page);
    }

    public <Page extends BaseConfigPage<?>> Page clickSaveButton(Page page) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        return page;
        };
}
