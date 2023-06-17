package school.redrover.model.JobsConfig;

import org.openqa.selenium.By;
import school.redrover.model.Jobs.OrganizationFolderPage;
import school.redrover.model.base.BaseConfigFoldersPage;

public class OrganizationFolderConfigPage extends BaseConfigFoldersPage<OrganizationFolderConfigPage, OrganizationFolderPage> {

    public OrganizationFolderConfigPage(OrganizationFolderPage organizationFolderPage) {
        super(organizationFolderPage);
    }

    public OrganizationFolderConfigPage clickDisable(){
        getDriver().findElement(By.xpath("//label[@data-title='Disabled']")).click();

        return this;
    }
}
