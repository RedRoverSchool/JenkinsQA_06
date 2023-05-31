package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class MovePage<JobTypePage extends BasePage<?>> extends BaseMainHeaderPage<MovePage<JobTypePage>> {

    private final JobTypePage jobTypePage;

    public MovePage(JobTypePage jobTypePage) {
        super(jobTypePage.getDriver());
        this.jobTypePage = jobTypePage;
    }

    public MovePage<JobTypePage> selectDestinationFolder() {
        new Select(getWait5().until(ExpectedConditions.elementToBeClickable(By.name("destination")))).selectByIndex(1);
        return this;
    }

    public JobTypePage clickMoveButton() {
        getDriver().findElement(By.name("Submit")).click();
        return jobTypePage;
    }
}
