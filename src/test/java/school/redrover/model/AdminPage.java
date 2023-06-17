package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class AdminPage extends BaseMainHeaderPage<AdminPage> {

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel > div:nth-child(4)"))).getText();

    }
}

