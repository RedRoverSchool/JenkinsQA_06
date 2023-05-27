package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageNodesPage extends MainPage {

    public ManageNodesPage(WebDriver driver){
        super(driver);
    }

    public NewNodePage clickNewNodeButton() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='new']"))).click();
        return new NewNodePage(getDriver());
    }
}
