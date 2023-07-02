package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;

import school.redrover.model.base.BaseOtherFoldersPage;
import school.redrover.model.jobsconfig.OrganizationFolderConfigPage;

public class OrganizationFolderPage extends BaseOtherFoldersPage<OrganizationFolderPage> {

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderConfigPage clickConfigure() {
        setupClickConfigure();
        return new OrganizationFolderConfigPage(this);
    }
}
