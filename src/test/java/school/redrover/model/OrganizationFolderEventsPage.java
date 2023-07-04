package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;

public class OrganizationFolderEventsPage extends BaseMainHeaderPage<ScanOrganizationFolderLog> {

    @FindBy(xpath = "//h1")
    private WebElement title;

    public OrganizationFolderEventsPage(WebDriver driver) {
        super(driver);
    }

    public String getTextFromTitle() {
        return title.getText();
    }
}