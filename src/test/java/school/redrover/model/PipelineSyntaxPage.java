package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;

public class PipelineSyntaxPage extends BaseMainHeaderPage<StatusUserPage>  {

    @FindBy(xpath = "//select")
    private WebElement sampleStepDropDownList;

    @FindBy(xpath = "//select//option[@value='echo: Print Message']")
    private WebElement printMessage;

    @FindBy(name = "_.message")
    private WebElement messageTextField;

    @FindBy(xpath = "//button[contains(text(),'Generate')]")
    private WebElement generatePipelineScriptButton;

    @FindBy(xpath = "//textarea")
    private WebElement textArea;

    public PipelineSyntaxPage(WebDriver driver) {
        super(driver);
    }

    public PipelineSyntaxPage clickPrintMessageOption() {
        printMessage.click();

        return this;
    }

    public PipelineSyntaxPage enterMessage(String text) {
        messageTextField.sendKeys(text);

        return this;
    }

    public PipelineSyntaxPage clickGeneratePipelineScriptButton() {
        generatePipelineScriptButton.click();

        return this;
    }

    public String getTextPipelineScript() {
       return textArea.getAttribute("value");
    }
}
