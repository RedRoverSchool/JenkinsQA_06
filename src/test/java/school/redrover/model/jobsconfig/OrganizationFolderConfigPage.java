package school.redrover.model.jobsconfig;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.jobs.OrganizationFolderPage;
import school.redrover.model.base.BaseConfigFoldersPage;

public class OrganizationFolderConfigPage extends BaseConfigFoldersPage<OrganizationFolderConfigPage, OrganizationFolderPage> {

    @FindBy(xpath = "//label[@data-title='Disabled']")
    private WebElement disableEnableFromConfig;

    @FindBy(tagName = "h1")
    private WebElement header;

    public OrganizationFolderConfigPage(OrganizationFolderPage organizationFolderPage) {
        super(organizationFolderPage);
    }

    public OrganizationFolderConfigPage clickDisableEnable(){
        disableEnableFromConfig.click();

        return this;
    }

    public String getConfigurationHeaderText() {
        return header.getText();
    }
}
