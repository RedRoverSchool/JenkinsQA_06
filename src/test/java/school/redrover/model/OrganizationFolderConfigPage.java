package school.redrover.model;

import org.openqa.selenium.By;
import school.redrover.model.base.BaseConfigPage;
import school.redrover.model.base.BaseFoldersConfigPage;

public class OrganizationFolderConfigPage extends BaseFoldersConfigPage<OrganizationFolderConfigPage, OrganizationFolderPage> {


    public OrganizationFolderConfigPage(OrganizationFolderPage organizationFolderPage) {
        super(organizationFolderPage);
    }

    public OrganizationFolderConfigPage clickDisable(){
        getDriver().findElement(By.xpath("//label[@data-title='Disabled']")).click();

        return this;
    }
}
