package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MultibranchProjectPage;
import school.redrover.model.ScanOrganizationFolderLog;
import school.redrover.model.base.BaseOtherFoldersPage;
import school.redrover.model.jobsconfig.OrganizationFolderConfigPage;

public class OrganizationFolderPage extends BaseOtherFoldersPage<OrganizationFolderPage> {

    @FindBy(xpath = "//a[@href='https://www.jenkins.io/doc/book/pipeline/multibranch/']")
    private WebElement multibranchProject;

    @FindBy(xpath = "//a[contains(@href,'/computation/console')]")
    private WebElement scanButton;

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderConfigPage clickConfigure() {
        setupClickConfigure();
        return new OrganizationFolderConfigPage(this);
    }

    public MultibranchProjectPage clickMultibranchProject() {
        getWait2().until(ExpectedConditions.elementToBeClickable(multibranchProject)).click();
        return new MultibranchProjectPage(getDriver());
    }

    public ScanOrganizationFolderLog clickScanOrgFolderLog() {
        getWait5().until(ExpectedConditions.elementToBeClickable(scanButton)).click();
        return new ScanOrganizationFolderLog(getDriver());
    }
}
