package school.redrover.model.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.jobsconfig.MultiConfigurationProjectConfigPage;
import school.redrover.model.base.BaseProjectPage;

public class MultiConfigurationProjectPage extends BaseProjectPage<MultiConfigurationProjectPage> {

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigurationProjectConfigPage clickConfigure() {
        setupClickConfigure();
        return new MultiConfigurationProjectConfigPage(this);
    }

    public String getJobBuildStatus() {
        WebElement buildStatus = getWait5().until(ExpectedConditions.visibilityOf(getDriver()
                .findElement(By.xpath("//div[@id='matrix']//span[@class='build-status-icon__outer']/child::*"))));
        return buildStatus.getAttribute("tooltip");
    }

}

