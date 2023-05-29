package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseModel;

public class ViewFolderPage extends BaseModel {

    public ViewFolderPage(WebDriver driver) {
        super(driver);
    }

    public FolderPage clickAll(){
        getDriver().findElement(By.linkText("All")).click();
        return new FolderPage(getDriver());
    }

    public WebElement getMyView() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='tab active']")));
    }


}
