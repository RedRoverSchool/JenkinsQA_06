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
        getWait5().until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("[href$='console']"))).click();
        return new BuildPage(getDriver());
    }

    public String getDescription() {
        return  description.getText();
    }

    public FreestyleProjectConfigPage clickAddDescription() {
        addDescriptionButton.click();
        return new FreestyleProjectConfigPage(getDriver());
    }
}
