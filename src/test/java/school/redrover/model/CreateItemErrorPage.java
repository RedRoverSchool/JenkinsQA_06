package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class CreateItemErrorPage extends BaseMainHeaderPage<CreateItemErrorPage> {

    @FindBy(xpath = "//div//p")
    private WebElement errorMessage;

    @FindBy(xpath = "//h1")
    private WebElement error;

    @FindBy(xpath = "//div[@id='main-panel']//h1")
    private WebElement headerText;

    public CreateItemErrorPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        return getWait10().until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public String getError() {
        return error.getText();
    }

    public String getHeaderText() {
        return getWait10().until(ExpectedConditions.visibilityOf(headerText)).getText();
    }
}
