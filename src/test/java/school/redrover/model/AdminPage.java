package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class AdminPage extends BaseMainHeaderPage<AdminPage> {
    @FindBy(css = "#main-panel > div:nth-child(4)")
    private WebElement title;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return getWait2().until(ExpectedConditions.visibilityOf(title)).getText();
    }
}

