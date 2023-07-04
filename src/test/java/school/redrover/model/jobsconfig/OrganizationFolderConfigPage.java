package school.redrover.model.jobsconfig;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.model.ScanOrganizationFolderLog;
import school.redrover.model.jobs.OrganizationFolderPage;
import school.redrover.model.base.BaseConfigFoldersPage;

public class OrganizationFolderConfigPage extends BaseConfigFoldersPage<OrganizationFolderConfigPage, OrganizationFolderPage> {

    @FindBy(xpath = "//label[@data-title='Disabled']")
    private WebElement disableEnableFromConfig;

    @FindBy(xpath = "//button[@data-section-id='appearance']")
    private WebElement appearanceButton;

    @FindBy(xpath = "//div[@class='jenkins-form-item has-help']/div/select")
    private WebElement defaultIcon;


    @FindBy(tagName = "h1")
    private WebElement header;

    public OrganizationFolderConfigPage(OrganizationFolderPage organizationFolderPage) {
        super(organizationFolderPage);
    }

    public OrganizationFolderConfigPage clickDisableEnable() {
        disableEnableFromConfig.click();
        return this;
    }

    public OrganizationFolderConfigPage clickAppearance() {
        getWait5().until(ExpectedConditions.elementToBeClickable(appearanceButton)).click();
        return this;
    }

    public OrganizationFolderConfigPage selectDefaultIcon() {
        new Select(getWait5().until(ExpectedConditions.elementToBeClickable(defaultIcon)))
                .selectByVisibleText("Default Icon");
        return this;
    }

    public String getConfigurationHeaderText() {
        return header.getText();
    }
}
