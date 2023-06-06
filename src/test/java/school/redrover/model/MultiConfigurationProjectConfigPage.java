package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseProjectsConfigPage;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProjectConfigPage extends BaseProjectsConfigPage<MultiConfigurationProjectConfigPage, MultiConfigurationProjectPage> {

    public MultiConfigurationProjectConfigPage(MultiConfigurationProjectPage multiConfigurationProjectPage) {
        super(multiConfigurationProjectPage);
    }

    public WebElement getCheckboxById(int id){
        return getDriver().findElement(By.id("cb" + id));
    }
}
