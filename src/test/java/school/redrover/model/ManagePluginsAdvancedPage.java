package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;

public class ManagePluginsAdvancedPage extends BaseMainHeaderPage<ManagePluginsAdvancedPage> {

    @FindBy(className = "jenkins-help-button")
    private WebElement extraInfoServerIcon;

    @FindBy(css = ".help>div")
    private WebElement extraInfoServer;

    public ManagePluginsAdvancedPage(WebDriver driver) {super(driver);}

    public ManagePluginsAdvancedPage clickExtraInfoServerIcon(){
        extraInfoServerIcon.click();
        return this;
    }

    public String getExtraInfoServerTextBox(){
        return extraInfoServer.getText();
    }
}
