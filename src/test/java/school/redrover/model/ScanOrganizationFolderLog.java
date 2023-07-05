package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;

public class ScanOrganizationFolderLog extends BaseMainHeaderPage<ScanOrganizationFolderLog> {

    @FindBy(xpath = "//span[@class='jenkins-icon-adjacent']")
    private WebElement title;

    public ScanOrganizationFolderLog(WebDriver driver) {
        super(driver);
    }

    public String getTextFromTitle(){
        return title.getText();
    }
}

