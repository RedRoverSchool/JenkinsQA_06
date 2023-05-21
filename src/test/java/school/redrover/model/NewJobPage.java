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

    public NewJobPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public NewJobPage enterItemName(String nameJob) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(nameJob);
        return this;
    }

    public FreestyleProjectConfigPage selectFreestyleProjectAndOk() {
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        okButton.click();
        return new FreestyleProjectConfigPage(getDriver());
    }

    public PipelineConfigPage selectPipelineAndOk() {
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        okButton.click();
        return new PipelineConfigPage(getDriver());
    }

    public NewJobPage selectMultiConfigurationProjectAndOk() {
        getDriver().findElement(By.cssSelector("[value$='MatrixProject'] + span")).click();
        okButton.click();
        return this;
    }

    public ConfigurePage selectMultiConfigurationProject() {
        getDriver().findElement(By.xpath("//span[.='Multi-configuration project']")).click();
        okButton.click();
        return new ConfigurePage(getDriver());
    }

    public FolderConfigPage selectFolderAndOk() {
        getDriver().findElement(By.cssSelector(".com_cloudbees_hudson_plugins_folder_Folder")).click();
        okButton.click();
        return new FolderConfigPage(getDriver());
    }

    public ConfigurePage selectFolder() {
        getDriver().findElement(By.xpath("//span[.='Folder']")).click();
        okButton.click();
        return new ConfigurePage(getDriver());
    }

    public NewJobPage selectMultibranchPipelineAndOk() {
        getDriver().findElement(By.xpath("//input[contains(@value, 'WorkflowMultiBranchProject')]")).click();
        okButton.click();
        return this;
    }

    public NewJobPage selectOrganizationFolderAndOk() {
        getDriver().findElement(By.xpath("//input[contains(@value, 'OrganizationFolder')]")).click();
        okButton.click();
        return this;
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
}
