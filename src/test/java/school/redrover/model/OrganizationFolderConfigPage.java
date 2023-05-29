package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseModel;

public class OrganizationFolderConfigPage extends BaseModel {

    public OrganizationFolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderPage clickSaveButton() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return new OrganizationFolderPage(getDriver());
    }
}
