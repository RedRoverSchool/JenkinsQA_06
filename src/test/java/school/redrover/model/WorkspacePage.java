package school.redrover.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;


public class WorkspacePage <JobTypePage extends BasePage<?, ?>> extends BaseMainHeaderPage<WorkspacePage<JobTypePage>> {

    @FindBy(css = "#main-panel h1")
    private WebElement pageTitle;
    private final JobTypePage jobTypePage;
    public WorkspacePage(JobTypePage jobTypePage){
        super(jobTypePage.getDriver());
        this.jobTypePage = jobTypePage;
    }

    public String getPageTitle() {
        return getWait5().until(ExpectedConditions.visibilityOf(pageTitle)).getText();
    }
}
