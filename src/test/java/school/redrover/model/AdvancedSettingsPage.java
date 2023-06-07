package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class AdvancedSettingsPage extends BaseMainHeaderPage<AdvancedSettingsPage> {
    public AdvancedSettingsPage(WebDriver driver) {super(driver);}

    public AdvancedSettingsPage clickExtraInfoServerIcon(){
        getWait2().until(ExpectedConditions.elementToBeClickable(By.className("jenkins-help-button")))
                .click();
        return this;
    }

    public String getExtraInfoServerTextBox(){
        return getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='main-panel']/div[2]/section[1]/form/div[1]/div[1]/div[4]/div/div"))).getText();
    }
}
