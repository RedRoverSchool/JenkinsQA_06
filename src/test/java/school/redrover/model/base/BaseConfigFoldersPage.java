package school.redrover.model.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public abstract class BaseConfigFoldersPage<Self extends BaseConfigPage<?, ?>, FolderPage extends BaseMainHeaderPage<?>> extends BaseConfigPage<Self, FolderPage>{

    @FindBy(xpath = "//input[@name='_.displayNameOrNull']")
    private WebElement inputDisplayName;

    @FindBy(xpath = "(//button[contains(text(), 'Health metrics')])[1]")
    private WebElement healthMetric;

    @FindBy(xpath = "//button[@data-section-id='health-metrics']")
    private WebElement healthMetricsSideMenu;

    @FindBy(xpath = "(//button [text()='Add metric'])[1]")
    private WebElement addHealthMetric;

    @FindBy(xpath = "//a[text()='Child item with worst health']")
    private WebElement childItemWithWorstHealth;

    @FindBy(xpath = "//div[@name='healthMetrics']")
    private WebElement addedHealthMetric;

    @FindBy(xpath = "//input[@name='_.recursive']")
    private WebElement recursiveCheckbox;

    @FindBy(xpath = "//button[@tooltip='Remove']")
    private WebElement removeHealthMetric;

    public BaseConfigFoldersPage(FolderPage foldersPage) {
        super(foldersPage);
    }

    public Self enterDisplayName(String displayName) {
        inputDisplayName.click();
        inputDisplayName.sendKeys(displayName);
        return (Self)this;
    }

    public Self clearDisplayName() {
        inputDisplayName.clear();
        return (Self)this;
    }

    public Self clickHealthMetrics(){
        new Actions(getDriver())
                .click(healthMetricsSideMenu)
                .pause(Duration.ofMillis(800))
                .perform();
        getWait10().until(ExpectedConditions.elementToBeClickable(healthMetric)).click();
        return (Self)this;
    }

    public Self addHealthMetrics(){
        clickHealthMetrics();

        getWait5().until(ExpectedConditions.elementToBeClickable(addHealthMetric)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(childItemWithWorstHealth)).click();

        return (Self)this;
    }

    public Boolean healthMetricIsVisible(){
        return getWait5().until(ExpectedConditions.visibilityOf(addedHealthMetric)).isDisplayed();
    }

    public Self removeHealthMetrics(){
        getWait5().until(ExpectedConditions.elementToBeClickable(removeHealthMetric)).click();

        return (Self) this;
    }
}
