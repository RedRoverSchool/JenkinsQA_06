package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class NewJobPage extends BasePage {

    @FindBy(xpath = "//button[@id='ok-button']")
    private WebElement okButton;

    @FindBy(className = "hudson_model_FreeStyleProject")
    private WebElement freestyleProject;

    @FindBy(id = "itemname-invalid")
    private WebElement itemInvalidNameMessage;

    public NewJobPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public NewJobPage enterItemName(String nameJob) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(nameJob);
        return this;
    }

    public FreestyleProjectConfigPage selectFreestyleProjectAndOk() {
        freestyleProject.click();
        okButton.click();
        return new FreestyleProjectConfigPage(getDriver());
    }

    public PipelineConfigPage selectPipelineAndOk() {
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        okButton.click();
        return new PipelineConfigPage(getDriver());
    }

    public MultiConfigurationProjectConfigPage selectMultiConfigurationProjectAndOk() {
        getDriver().findElement(By.xpath("//span[.='Multi-configuration project']")).click();
        okButton.click();
        return new MultiConfigurationProjectConfigPage(getDriver());
    }

    public FolderConfigPage selectFolderAndOk() {
        getDriver().findElement(By.xpath("//span[.='Folder']")).click();
        okButton.click();
        return new FolderConfigPage(getDriver());
    }

    public MultibranchPipelineConfigPage selectMultibranchPipelineAndOk() {
        getDriver().findElement(By.xpath("//input[contains(@value, 'WorkflowMultiBranchProject')]")).click();
        okButton.click();
        return new MultibranchPipelineConfigPage(getDriver());
    }

    public OrganizationFolderConfigPage selectOrganizationFolderAndOk() {
        getDriver().findElement(By.xpath("//input[contains(@value, 'OrganizationFolder')]")).click();
        okButton.click();
        return new OrganizationFolderConfigPage(getDriver());
    }

    public NewJobPage copyFrom(String typeToAutocomplete) {
        getDriver().findElement(By.xpath("//input[contains(@autocompleteurl, 'autoCompleteCopyNewItemFrom')]"))
                .sendKeys(typeToAutocomplete);
        return this;
    }

    public PipelineConfigPage selectPipelineAndClickOK() {
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        okButton.click();
        return new PipelineConfigPage(getDriver());
    }

    public String getItemInvalidMessage() {
        return getWait2().until(ExpectedConditions.visibilityOf(itemInvalidNameMessage)).getText();
    }

    public NewJobPage selectFreestyleProject() {
        getWait5().until(ExpectedConditions.elementToBeClickable(freestyleProject)).click();
        return this;
    }

    public boolean isOkButtonEnabled() {
        return okButton.isEnabled();
    }
}
