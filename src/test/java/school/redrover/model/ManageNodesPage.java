package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ManageNodesPage extends BaseMainHeaderPage<ManageNodesPage> {

    public ManageNodesPage(WebDriver driver){
        super(driver);
    }

    public NewNodePage clickNewNodeButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='new']"))).click();
        return new NewNodePage(getDriver());
    }
}
