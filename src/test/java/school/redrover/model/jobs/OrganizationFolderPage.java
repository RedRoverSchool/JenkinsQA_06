package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.DeletePage;
import school.redrover.model.MainPage;
import school.redrover.model.jobsconfig.OrganizationFolderConfigPage;
import school.redrover.model.base.BaseJobPage;

public class OrganizationFolderPage extends BaseJobPage<OrganizationFolderPage> {

    @FindBy(xpath = "//div[@id='tasks']//a[@href='/job/OrgFolderNew/delete']")
    private WebElement delete;

    @FindBy(xpath = "//button[@class='jenkins-button jenkins-button--primary ']")
    private WebElement disableButton;

    @FindBy(xpath = "//div[@id='view-message']")
    private WebElement descriptionMessage;

    @FindBy(xpath = "//form[@method='post']")
    private WebElement disableMessage;

    public OrganizationFolderPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public OrganizationFolderConfigPage clickConfigure() {
        setupClickConfigure();
        return new OrganizationFolderConfigPage(this);
    }

    public String getTextFromDisableMessage() {

        return disableMessage.getText();
    }

    public String getTextFromDescription() {

        return descriptionMessage.getText();
    }

    public OrganizationFolderPage clickDisableButton() {
        disableButton.click();

        return this;
    }

    public DeletePage<MainPage> clickDeleteOrganizationFolderSideMenu() {
        getWait2().until(ExpectedConditions.elementToBeClickable(delete)).click();

        return new DeletePage<>(getDriver(), new MainPage(getDriver()));
    }
}
