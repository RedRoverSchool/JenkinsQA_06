package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.CredentialsPage;
import school.redrover.model.ScanOrganizationFolderLog;
import school.redrover.model.base.BaseOtherFoldersPage;
import school.redrover.model.jobsconfig.OrganizationFolderConfigPage;

public class OrganizationFolderPage extends BaseOtherFoldersPage<OrganizationFolderPage> {

    @FindBy(xpath = "//a[contains(@href,'/computation/console')]")
    private WebElement scanButton;

    @FindBy(xpath = "//a[contains(@href,'/credentials')]")
    private WebElement credentialsButton;
    
    @FindBy(xpath = "//*[@href='https://www.jenkins.io/doc/book/pipeline/']")
	 private WebElement linkBookCreatingJenkinsPipeline;

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderConfigPage clickConfigure() {
        setupClickConfigure();
        return new OrganizationFolderConfigPage(this);
    }

    public ScanOrganizationFolderLog clickScanOrgFolderLog() {
        getWait5().until(ExpectedConditions.elementToBeClickable(scanButton)).click();

        return new ScanOrganizationFolderLog(getDriver());
    }

    public CredentialsPage clickCredentials(){
        getWait5().until(ExpectedConditions.elementToBeClickable(credentialsButton)).click();

        return new CredentialsPage(getDriver());

    public String getTextCreatingJenkinsPipeline() {

        return getWait5().until(ExpectedConditions.elementToBeClickable(linkBookCreatingJenkinsPipeline)).getText();
    }
}
