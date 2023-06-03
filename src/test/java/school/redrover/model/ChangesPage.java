package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BasePage;

public class ChangesPage<JobTypePage extends BasePage<?>> extends BaseMainHeaderPage<ChangesPage<JobTypePage>> {

    private final JobTypePage jobTypePage;

    public ChangesPage(JobTypePage jobTypePage) {
        super(jobTypePage.getDriver());
        this.jobTypePage = jobTypePage;
    }

    public String getTextOfPage() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']"))).getText();
    }
}
