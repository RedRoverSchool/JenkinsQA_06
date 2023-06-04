package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseFoldersConfigPage;

public class MultibranchPipelineConfigPage extends BaseFoldersConfigPage<MultibranchPipelineConfigPage, MultibranchPipelinePage> {

    public MultibranchPipelineConfigPage(MultibranchPipelinePage multibranchPipelinePage) {
        super(multibranchPipelinePage);
    }

    public WebElement titleMultibranchPipeline() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//body/div[@id='page-body']/div[@id='main-panel']/h1[1]"))));

    }
}
