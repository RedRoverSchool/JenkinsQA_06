package school.redrover.model;

import org.openqa.selenium.By;
import school.redrover.model.base.BaseConfigPage;

public class OrganizationFolderConfigPage extends BaseConfigPage<OrganizationFolderConfigPage, OrganizationFolderPage> {


    public OrganizationFolderConfigPage(OrganizationFolderPage organizationFolderPage) {
        super(organizationFolderPage);
    }

    public OrganizationFolderConfigPage clickDisable(){
        getDriver().findElement(By.xpath("//label[@data-title='Disabled']")).click();

        return this;
    }

    public OrganizationFolderConfigPage selectDescription(String description){
        getDriver().findElement(By.xpath("//textarea[@name='_.description']")).sendKeys(description);

        return this;
    }
}
