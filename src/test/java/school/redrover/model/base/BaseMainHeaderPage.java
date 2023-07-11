package school.redrover.model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.component.MainBreadcrumbComponent;
import school.redrover.model.component.MainHeaderComponent;

public abstract class BaseMainHeaderPage<Self extends BaseMainHeaderPage<?>> extends BasePage<MainHeaderComponent<Self>, MainBreadcrumbComponent<Self>> {

    @FindBy(xpath = "//div[@id='main-panel']")
    private WebElement mainPanel;

    public BaseMainHeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainHeaderComponent<Self> getHeader() {
        return new MainHeaderComponent<>( (Self)this);
    }

    @Override
    public MainBreadcrumbComponent<Self> getBreadcrumb() {
        return new MainBreadcrumbComponent<>( (Self)this);
    }

    public String getTextPage() {
        return getWait5().until(ExpectedConditions.visibilityOf(mainPanel)).getText();
    }
}
