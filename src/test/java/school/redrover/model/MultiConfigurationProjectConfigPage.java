package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseConfigProjectsPage;

public class MultiConfigurationProjectConfigPage extends BaseConfigProjectsPage<MultiConfigurationProjectConfigPage, MultiConfigurationProjectPage> {

    public MultiConfigurationProjectConfigPage(MultiConfigurationProjectPage multiConfigurationProjectPage) {
        super(multiConfigurationProjectPage);
    }

    public WebElement getCheckboxById(int id){
        return getDriver().findElement(By.id("cb" + id));
    }
}
