package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.model.base.BaseConfigFoldersPage;

public class MultibranchPipelineConfigPage extends BaseConfigFoldersPage<MultibranchPipelineConfigPage, MultibranchPipelinePage> {

    public MultibranchPipelineConfigPage(MultibranchPipelinePage multibranchPipelinePage) {
        super(multibranchPipelinePage);
    }

    public WebElement titleMultibranchPipeline() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//body/div[@id='page-body']/div[@id='main-panel']/h1[1]"))));

    }

    public MultibranchPipelineConfigPage clickDisable() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@data-title='Disabled']"))).click();
        return this;
    }

    public MultibranchPipelineConfigPage clickAppearance() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-section-id='appearance']"))).click();
        return this;
    }

    public MultibranchPipelineConfigPage selectDefaultIcon() {
        new Select(getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='jenkins-form-item has-help']/div/select"))))
                .selectByVisibleText("Default Icon");
        return this;
    }
}

