package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import school.redrover.model.base.BasePage;

public class NewJobPage extends BasePage {

    @FindBy(xpath = "//button[@id='ok-button']")
    private WebElement okButton;

    public NewJobPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public NewJobPage enterItemName(String nameJob) {
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(nameJob);
        return this;
    }

    public NewJobPage selectFreestyleProjectAndOk() {
        getDriver().findElement(By.cssSelector("[value='hudson.model.FreeStyleProject'] + span")).click();
        okButton.click();
        return this;
    }

    public NewJobPage selectPipelineAndOk() {
        getDriver().findElement(By.xpath("//div[@id='items']//li[2]")).click();
        okButton.click();
        return this;
    }

    public NewJobPage selectMultiConfigurationProjectAndOk() {
        getDriver().findElement(By.cssSelector("[value$='MatrixProject'] + span")).click();
        okButton.click();
        return this;
    }

    public NewJobPage selectFolderAndOk() {
        getDriver().findElement(By.xpath("//input[contains(@value, 'folder.Folder')]")).click();
        okButton.click();
        return this;
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
}
