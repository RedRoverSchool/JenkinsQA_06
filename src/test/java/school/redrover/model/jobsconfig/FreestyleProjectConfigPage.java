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

    public FreestyleProjectConfigPage(FreestyleProjectPage freestyleProjectPage) {
        super(freestyleProjectPage);
    }

    public FreestyleProjectConfigPage addBuildStepsExecuteShell(String buildSteps) {
        int deltaY = getDriver().findElement(By.tagName("footer")).getRect().y;
        new Actions(getDriver())
                .scrollByAmount(0, deltaY)
                .perform();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='yui-gen9-button']"))).click();
        getDriver().findElement(
                By.xpath("//*[@id='yui-gen24']")).click();

        new Actions(getDriver())
                .click(getDriver().findElement(By.xpath("//*[@name='description']")))
                .sendKeys(buildSteps)
                .perform();
        return this;
    }

    public FreestyleProjectConfigPage clickCheckBoxExecuteConcurrentBuilds() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", getDriver()
                .findElement(By.xpath("//label[normalize-space(text())='Throttle builds']")));
        checkBoxExecuteConcurrentBuilds.click();
        return this;
    }

    public WebElement getTrueExecuteConcurrentBuilds() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", getDriver()
                .findElement(By.xpath("//label[normalize-space(text())='Throttle builds']")));
        return trueExecuteConcurrentBuilds;
    }

    public FreestyleProjectConfigPage clickAdvancedDropdownMenu() {
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
}
