package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ErrorNodePage extends BaseMainHeaderPage<ErrorNodePage> {

    @FindBy(xpath = "//div//p")
    private WebElement errorMessage;

    @FindBy(xpath = "//p")
    private WebElement textError;

    @FindBy(xpath = "//h1")
    private WebElement error;

    public ErrorNodePage(WebDriver driver){
        super(driver);
    }

    public String getTextError() {
        getWait2().until(ExpectedConditions
                .textToBePresentInElement(error, "Error"));
        return textError.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
