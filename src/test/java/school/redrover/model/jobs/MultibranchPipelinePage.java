package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseOtherFoldersPage;
import school.redrover.model.jobsconfig.MultibranchPipelineConfigPage;

public class MultibranchPipelinePage extends BaseOtherFoldersPage<MultibranchPipelinePage> {

    public MultibranchPipelinePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultibranchPipelineConfigPage clickConfigure() {
        setupClickConfigure();
        return new MultibranchPipelineConfigPage(new MultibranchPipelinePage(getDriver()));
    }
}
