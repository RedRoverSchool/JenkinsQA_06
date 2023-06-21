package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class MovePage<JobTypePage extends BasePage<?, ?>> extends BaseMainHeaderPage<MovePage<JobTypePage>> {

    @FindBy(name= "Submit")
    private WebElement moveButton;

    private final JobTypePage jobTypePage;

    public MovePage(JobTypePage jobTypePage) {
        super(jobTypePage.getDriver());
        this.jobTypePage = jobTypePage;
    }

    public MovePage<JobTypePage> selectDestinationFolder(String folderName) {
        new Select(getWait5().until(ExpectedConditions.elementToBeClickable(By.name("destination")))).selectByValue("/" + folderName);
        return this;
    }

    public JobTypePage clickMoveButton() {
        moveButton.click();
        return jobTypePage;
    }
}
