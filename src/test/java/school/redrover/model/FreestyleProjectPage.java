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

    @FindBy(id = "enable-project")
    private WebElement warningMessage;

    @FindBy(xpath = "//*[@id='disable-project']/button")
    private WebElement projectDisabledButton;

    @FindBy(xpath = "//*[@id='jenkins-head-icon']")
    private WebElement jenkinsIconButton;


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

    public MainPage navigateToMainPageViaJenkinsIcon() {
        getWait5().until(ExpectedConditions.elementToBeClickable(jenkinsIconButton)).click();
        return new MainPage(getDriver());
    }

    public String getWarningMessage() {
        return warningMessage.getText().substring(0, warningMessage.getText().indexOf("\n"));
    }

    public boolean isProjectDisabledButtonDisplayed() {

        return projectDisabledButton.isDisplayed();
    }

}
