package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class FreestyleProjectPage extends BasePage {

    @FindBy(xpath = "//*[@id='description']/div")
    private WebElement description;

    @FindBy(id = "description-link")
    private WebElement addDescriptionButton;

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public FreestyleProjectPage selectBuildNow() {
        getDriver().findElement(By.cssSelector("[href*='build?']")).click();
        return this;
    }

    public BuildPage selectBuildItemTheHistoryOnBuildPage() {
        getWait10().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("[href$='console']"))).click();
        return new BuildPage(getDriver());
    }

    public FreestyleProjectPage clickTheDisableProjectButton() {
        getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[text() = 'Disable Project']"))).click();
        return this;
    }

    public FreestyleProjectPage clickTheEnableProjectButton() {
        getWait5().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[text() = 'Enable']"))).click();
        return this;
    }

    public String getDescription() {
        return description.getText();
    }

    public FreestyleProjectConfigPage clickAddDescription() {
        addDescriptionButton.click();
        return new FreestyleProjectConfigPage(getDriver());
    }
}
