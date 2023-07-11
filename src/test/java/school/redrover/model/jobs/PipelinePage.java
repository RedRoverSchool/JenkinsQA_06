package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;
import school.redrover.model.jobsconfig.PipelineConfigPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseProjectPage;

public class PipelinePage extends BaseProjectPage<PipelinePage> {

    @FindBy(css = ".stage-header-name-0")
    private WebElement stage;

    public PipelinePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineConfigPage clickConfigure() {
        setupClickConfigure();
        return new PipelineConfigPage(this);
    }

    public String getStage() {
        return stage.getText();
    }
}
