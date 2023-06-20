package school.redrover.model.jobsconfig;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.jobs.OrganizationFolderPage;
import school.redrover.model.base.BaseConfigFoldersPage;

public class OrganizationFolderConfigPage extends BaseConfigFoldersPage<OrganizationFolderConfigPage, OrganizationFolderPage> {

    @FindBy(xpath = "//label[@data-title='Disabled']")
    private WebElement disableFromConfig;
    public OrganizationFolderConfigPage(OrganizationFolderPage organizationFolderPage) {
        super(organizationFolderPage);
    }

    public OrganizationFolderConfigPage clickDisable(){
        disableFromConfig.click();

        return this;
    }
}
