package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class OrganizationFolderConfigPage extends BasePage {

    public OrganizationFolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public OrganizationFolderPage clickSaveButton() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return new OrganizationFolderPage(getDriver());
    }
}
