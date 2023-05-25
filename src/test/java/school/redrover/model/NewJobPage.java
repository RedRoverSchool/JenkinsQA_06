package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class NewJobPage extends BasePage {

    public NewJobPage(WebDriver driver) {
        super(driver);
    }

    public NewJobPage enterItemName(String nameJob) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(nameJob);
        return this;
    }

    public FreestyleProjectConfigPage selectFreestyleProjectAndOk() {
        getFreestyleProject().click();
        getOkButton().click();
        return new FreestyleProjectConfigPage(getDriver());
    }

    public PipelineConfigPage selectPipelineAndOk() {
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        getOkButton().click();
        return new PipelineConfigPage(getDriver());
    }

    public MultiConfigurationProjectConfigPage selectMultiConfigurationProjectAndOk() {
        getDriver().findElement(By.xpath("//span[.='Multi-configuration project']")).click();
        getOkButton().click();
        return new MultiConfigurationProjectConfigPage(getDriver());
    }

    public FolderConfigPage selectFolderAndOk() {
        getDriver().findElement(By.xpath("//li[contains(@class, 'folder_Folder')]")).click();
        getOkButton().click();
        return new FolderConfigPage(getDriver());
    }

    public MultibranchPipelineConfigPage selectMultibranchPipelineAndOk() {
        getDriver().findElement(By.xpath("//li[contains(@class, 'WorkflowMultiBranchProject')]")).click();
        getOkButton().click();
        return new MultibranchPipelineConfigPage(getDriver());
    }

    public OrganizationFolderConfigPage selectOrganizationFolderAndOk() {
        getDriver().findElement(By.xpath("//li[contains(@class, 'OrganizationFolder')]")).click();
        getOkButton().click();
        return new OrganizationFolderConfigPage(getDriver());
    }

    public NewJobPage copyFrom(String typeToAutocomplete) {
        getDriver().findElement(By.xpath("//input[contains(@autocompleteurl, 'autoCompleteCopyNewItemFrom')]"))
                .sendKeys(typeToAutocomplete);
        return this;
    }

    public PipelineConfigPage selectPipelineAndClickOK() {
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        getOkButton().click();
        return new PipelineConfigPage(getDriver());
    }

    public String getItemInvalidMessage() {
        return getWait2().until(ExpectedConditions.visibilityOf(getItemInvalidNameMessage())).getText();
    }

    public NewJobPage selectFreestyleProject() {
        getWait5().until(ExpectedConditions.elementToBeClickable(getFreestyleProject())).click();
        return this;
    }

    public boolean isOkButtonEnabled() {
        return getOkButton().isEnabled();
    }

    public CreateItemErrorPage clickOkToCreateWithExistingName() {
        getOkButton().click();
        return new CreateItemErrorPage(getDriver());
    }

    public String getItemNameRequiredMessage() {
        return getDriver().findElement(By.id("itemname-required")).getText();
    }

    private WebElement getOkButton() {
        return getDriver().findElement(By.xpath("//button[@id='ok-button']"));
    }

    private WebElement getFreestyleProject() {
        return getDriver().findElement(By.className("hudson_model_FreeStyleProject"));
    }

    private WebElement getItemInvalidNameMessage() {
       return getDriver().findElement(By.id("itemname-invalid"));
    }
}
