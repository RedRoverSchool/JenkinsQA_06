package school.redrover.model.jobsconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.jobs.FreestyleProjectPage;
import school.redrover.model.base.BaseConfigProjectsPage;

public class FreestyleProjectConfigPage extends BaseConfigProjectsPage<FreestyleProjectConfigPage, FreestyleProjectPage> {

    @FindBy(xpath = "//label[text()='Execute concurrent builds if necessary']")
    private WebElement checkBoxExecuteConcurrentBuilds;

    @FindBy(xpath = "//div[@ref='cb8']/following-sibling::div[2]")
    private WebElement trueExecuteConcurrentBuilds;

    @FindBy(xpath = "//label[text()='Quiet period']")
    private WebElement quietPeriod;

    @FindBy(xpath = "//label[text()='Execute concurrent builds if necessary']")
    private WebElement executeConcurrentBuildsIfNecessary;

    @FindBy(xpath = "//input[@name='quiet_period']")
    private WebElement inputQuietPeriod;

    @FindBy(xpath = "//div[5]/div[1]/button")
    private WebElement advancedDropdownMenu;

    @FindBy(tagName = "footer")
    private WebElement footer;

    @FindBy(xpath = "//*[@id='yui-gen9-button']")
    private WebElement executeShellButton;

    @FindBy(xpath = "//*[@id='yui-gen24']")
    private WebElement generalButton;

    @FindBy(xpath = "//*[@name='description']")
    private WebElement descriptionField;

    @FindBy(xpath = "//label[normalize-space(text())='Throttle builds']")
    private WebElement throttleBuildsCheckbox;

    @FindBy(xpath = "//label[text()='Retry Count']")
    private WebElement retryCount;

    @FindBy(xpath = "//input[@name='scmCheckoutRetryCount']")
    private WebElement checkoutRetryCountSCM;


    public FreestyleProjectConfigPage(FreestyleProjectPage freestyleProjectPage) {
        super(freestyleProjectPage);
    }

    public FreestyleProjectConfigPage addBuildStepsExecuteShell(String buildSteps) {
        int deltaY = footer.getRect().y;
        new Actions(getDriver())
                .scrollByAmount(0, deltaY)
                .perform();

        getWait5().until(ExpectedConditions.visibilityOf(executeShellButton)).click();
        generalButton.click();

        new Actions(getDriver())
                .click(descriptionField)
                .sendKeys(buildSteps)
                .perform();
        return this;
    }

    public FreestyleProjectConfigPage clickCheckBoxExecuteConcurrentBuilds() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", throttleBuildsCheckbox);
        checkBoxExecuteConcurrentBuilds.click();
        return this;
    }

    public WebElement getTrueExecuteConcurrentBuilds() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", throttleBuildsCheckbox);
        return trueExecuteConcurrentBuilds;
    }

    public FreestyleProjectConfigPage clickAdvancedDropdownMenu() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", executeConcurrentBuildsIfNecessary);
        advancedDropdownMenu.click();
        return this;
    }

    public FreestyleProjectConfigPage clickQuietPeriod() {
        quietPeriod.click();
        return this;
    }

    public FreestyleProjectConfigPage inputQuietPeriod(String number) {
        inputQuietPeriod.clear();
        inputQuietPeriod.sendKeys(number);
        return this;
    }

    public String getQuietPeriod() {
        return inputQuietPeriod.getAttribute("value");
    }

    public FreestyleProjectConfigPage clickRetryCount() {
        retryCount.click();
        return this;
    }

    public FreestyleProjectConfigPage inputSCMCheckoutRetryCount(String count) {
        checkoutRetryCountSCM.clear();
        checkoutRetryCountSCM.sendKeys(count);
        return this;
    }

    public String getCheckoutRetryCountSCM() {
        return checkoutRetryCountSCM.getAttribute("value");
    }


}
