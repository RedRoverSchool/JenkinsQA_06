package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.jobs.FolderPage;
import school.redrover.model.base.BaseMainHeaderPage;

public class ViewFolderPage extends BaseMainHeaderPage<ViewFolderPage> {

    @FindBy(linkText = "All")
    private WebElement allLink;

    @FindBy(xpath = "//div[@class='tab active']")
    private WebElement myViewTabBar;

    public ViewFolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage clickAll() {
        allLink.click();

        return new FolderPage(getDriver());
    }

    public String getMyView() {
        return getWait5().until(ExpectedConditions.visibilityOf(myViewTabBar)).getText();
    }
}
