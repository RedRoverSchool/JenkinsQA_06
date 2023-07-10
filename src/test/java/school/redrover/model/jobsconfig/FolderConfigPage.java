package school.redrover.model.jobsconfig;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.jobs.FolderPage;
import school.redrover.model.base.BaseConfigFoldersPage;

public class FolderConfigPage extends BaseConfigFoldersPage<FolderConfigPage, FolderPage> {

    public FolderConfigPage(FolderPage folderPage) {
        super(folderPage);
    }

    @FindBy(xpath = "//div[@class='repeated-container with-drag-drop']/span")
    private WebElement addButton;

    @FindBy(xpath = "//input[@checkdependson='name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@name='_.defaultVersion']")
    private WebElement defaultVersionField;

    @FindBy(xpath = "//div[@name='retriever']//select[contains(@class, 'dropdownList')]")
    private WebElement sourceCodeManagementOptions;

    @FindBy(xpath = "//div[@name='retriever']//select[@class='jenkins-select__input dropdownList']/option[text()='GitHub']")
    private WebElement optionGitHub;

    @FindBy(xpath = "//input[@name='_.repositoryUrl']")
    private WebElement repositoryField;

    @FindBy(xpath = "//button[@name='Apply']")
    private WebElement applyButton;

    @FindBy(xpath = "//div[@class='validation-error-area validation-error-area--visible']//div[@class='ok']")
    private WebElement currentDefaultVersion;

    @FindBy(xpath = "//div[@id='notification-bar'][contains(@class, 'jenkins-notification--success jenkins-notification--visible')]")
    private WebElement notificationSuccess;

    @FindBy(xpath = "//button[@data-section-id='properties']")
    private WebElement propertiesButton;

    public FolderConfigPage inputNameLibrary() {
        propertiesButton.click();
        getWait5().until(ExpectedConditions.elementToBeClickable(addButton)).click();
        nameField.sendKeys("shared-library");

        return this;
    }

    public FolderConfigPage inputDefaultVersion(String defaultVersion) {
        defaultVersionField.sendKeys(defaultVersion);

        return this;
    }

    public FolderConfigPage pushSourceCodeManagementButton() {
        new Actions(getDriver())
            .scrollToElement(sourceCodeManagementOptions)
                .click()
                .perform();

        return this;
    }

    public FolderConfigPage chooseOption() {
        getWait5().until(ExpectedConditions.elementToBeClickable(optionGitHub)).click();

        return this;
    }

    public FolderConfigPage inputLibraryRepoUrl(String repoUrl) {
        repositoryField.sendKeys(repoUrl);

        return this;
    }

    public FolderConfigPage pushApply() {
        applyButton.click();
        getWait2().until(ExpectedConditions.visibilityOf(notificationSuccess));

        return this;
    }

    public FolderConfigPage refreshPage() {
        getDriver().navigate().refresh();

        return this;
    }

    public Boolean libraryDefaultVersionValidated() {
        return getWait2().until(ExpectedConditions.visibilityOf(currentDefaultVersion)).getText().contains("Currently maps to revision");
    }
}
