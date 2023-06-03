package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ChangesPage extends BaseMainHeaderPage<ChangesPage> {

    public ChangesPage(WebDriver driver) {
        super(driver);
    }

    public String getTextOfPage() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']"))).getText();
    }
}
