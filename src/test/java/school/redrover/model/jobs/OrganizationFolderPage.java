package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseOtherFoldersPage;
import school.redrover.model.jobsconfig.OrganizationFolderConfigPage;

public class OrganizationFolderPage extends BaseOtherFoldersPage<OrganizationFolderPage> {

    @FindBy(xpath = "//a[@href='https://www.jenkins.io/doc/book/pipeline/multibranch/']")
    private WebElement multibranchProject;

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderConfigPage clickConfigure() {
        setupClickConfigure();
        return new OrganizationFolderConfigPage(this);
    }

    public OrganizationFolderConfigPage clickMultibranchProject() {
        getWait2().until(ExpectedConditions.elementToBeClickable(multibranchProject)).click();
        return new OrganizationFolderConfigPage(this);
    }
}
