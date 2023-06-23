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
    private WebElement executeConcurrentBuildsIfNecessary;

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

    @FindBy(xpath = "//div[1]/div[6]/div[3]/div[3]")
    private WebElement trueBlockBuildWhenUpstreamProjectIsBuilding;

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

    public FreestyleProjectConfigPage clickAdvancedDropdownMenu() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", executeConcurrentBuildsIfNecessary);
        advancedDropdownMenu.click();
        return this;
    }

    public WebElement getTrueBlockBuildWhenUpstreamProjectIsBuilding() {
        return trueBlockBuildWhenUpstreamProjectIsBuilding;
    }
}
