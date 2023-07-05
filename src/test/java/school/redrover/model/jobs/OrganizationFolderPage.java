package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.MultibranchProjectPage;
import school.redrover.model.CredentialsPage;
import school.redrover.model.OrganizationFolderEventsPage;
import school.redrover.model.ScanOrganizationFolderLog;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseOtherFoldersPage;
import school.redrover.model.jobsconfig.OrganizationFolderConfigPage;

public class OrganizationFolderPage extends BaseOtherFoldersPage<OrganizationFolderPage> {

    @FindBy(xpath = "//a[@href='https://www.jenkins.io/doc/book/pipeline/multibranch/']")
    private WebElement multibranchProject;

    @FindBy(xpath = "//a[contains(@href,'/computation/console')]")
    private WebElement scanButton;

    @FindBy(xpath = "//a[contains(@href, '/events')]")
    private WebElement eventButton;

    @FindBy(xpath = "//a[contains(@href,'/credentials')]")
    private WebElement credentialsButton;

    @FindBy(xpath = "//*[@href='https://www.jenkins.io/doc/book/pipeline/']")
    private WebElement linkBookCreatingJenkinsPipeline;

    @FindBy(xpath = "//a[@href='./configure']")
    private WebElement configureProject;

    @FindBy(xpath = "//div/a[@id='description-link']")
    private WebElement addDescription;

    @FindBy(xpath = "//span[(text() = 'Re-run the Folder Computation')]")
    private WebElement reRunFolderComputationLink;

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderConfigPage clickConfigure() {
        setupClickConfigure();
        return new OrganizationFolderConfigPage(this);
    }

    public OrganizationFolderConfigPage clickConfigureProject() {
        getWait2().until(ExpectedConditions.elementToBeClickable(configureProject)).click();

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

    public OrganizationFolderEventsPage clickOrgFolderEvents() {
        getWait5().until(ExpectedConditions.elementToBeClickable(eventButton)).click();
        return new OrganizationFolderEventsPage(getDriver());
    }

    public CredentialsPage clickCredentials(){
        getWait5().until(ExpectedConditions.elementToBeClickable(credentialsButton)).click();

        return new CredentialsPage(getDriver());
    }

    public String getTextCreatingJenkinsPipeline() {

        return getWait5().until(ExpectedConditions.elementToBeClickable(linkBookCreatingJenkinsPipeline)).getText();
    }

    public OrganizationFolderPage clickAddDescription(){
        addDescription.click();
        return this;
    }

    public ScanOrganizationFolderLog clickRerunTheFolderComputation() {
        getWait5().until(ExpectedConditions.elementToBeClickable(reRunFolderComputationLink)).click();
        return new ScanOrganizationFolderLog(getDriver());
    }
}
