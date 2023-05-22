package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BasePage;

public class MultibranchPipelineConfigPage extends BasePage {
    public MultibranchPipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    public MultibranchPipelinePage saveButton() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return new MultibranchPipelinePage(getDriver());
    }
}
