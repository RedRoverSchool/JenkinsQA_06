package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import school.redrover.model.base.BasePage;

public class CreateItemErrorPage extends BasePage {

    @FindBy(xpath = "//div[@id='main-panel']/p")
    private WebElement errorMessage;

    public CreateItemErrorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public String getErrorMessage() {
        return getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();
    }
}
