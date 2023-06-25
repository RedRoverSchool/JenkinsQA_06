package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.jobsconfig.MultiConfigurationProjectConfigPage;
import school.redrover.model.base.BaseProjectPage;

public class MultiConfigurationProjectPage extends BaseProjectPage<MultiConfigurationProjectPage> {

    @FindBy(xpath = "//div[@id='matrix']//span[@class='build-status-icon__outer']/child::*")
    private WebElement jobBuildStatus;

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigurationProjectConfigPage clickConfigure() {
        setupClickConfigure();

        return new MultiConfigurationProjectConfigPage(this);
    }

    public String getJobBuildStatus() {
        WebElement buildStatus = getWait5().until(ExpectedConditions.visibilityOf(jobBuildStatus));
        new Actions(getDriver()).moveToElement(buildStatus).perform();

        return buildStatus.getAttribute("tooltip");
    }
}

