package school.redrover.model;

import org.openqa.selenium.By;
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

    @FindBy(id = "jenkins-head-icon")
    private WebElement logo;

    public CreateItemErrorPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        return getWait10().until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public MainPage goMainPage() {
        logo.click();
        return new MainPage(getDriver());
    }

    public String getError() {
        return error.getText();
    }

    public String getHeaderText() {
        return getWait10().until(ExpectedConditions.visibilityOf(headerText)).getText();
    }
}
