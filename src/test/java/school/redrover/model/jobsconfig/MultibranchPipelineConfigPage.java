package school.redrover.model.jobsconfig;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.model.jobs.MultibranchPipelinePage;
import school.redrover.model.base.BaseConfigFoldersPage;

public class MultibranchPipelineConfigPage extends BaseConfigFoldersPage<MultibranchPipelineConfigPage, MultibranchPipelinePage> {

    @FindBy(xpath = "//label[@data-title='Disabled']")
    private WebElement disabledSwitch;

    @FindBy(xpath = "//button[@data-section-id='appearance']")
    private WebElement appearanceButton;

    @FindBy(xpath = "//div[@class='jenkins-form-item has-help']/div/select")
    private WebElement defaultIcon;

    public MultibranchPipelineConfigPage(MultibranchPipelinePage multibranchPipelinePage) {
        super(multibranchPipelinePage);
    }

    public MultibranchPipelineConfigPage clickDisable() {
        getWait5().until(ExpectedConditions.visibilityOf(disabledSwitch)).click();
        return this;
    }

    public MultibranchPipelineConfigPage clickAppearance() {
        getWait5().until(ExpectedConditions.elementToBeClickable(appearanceButton)).click();
        return this;
    }

    public MultibranchPipelineConfigPage selectDefaultIcon() {
        new Select(getWait5().until(ExpectedConditions.elementToBeClickable(defaultIcon)))
                .selectByVisibleText("Default Icon");
        return this;
    }
}

