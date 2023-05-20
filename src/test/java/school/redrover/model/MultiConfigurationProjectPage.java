package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;
import school.redrover.runner.TestUtils;

public class MultiConfigurationProjectPage extends BasePage {

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProjectName() {

        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//h1"))));
    }

}
