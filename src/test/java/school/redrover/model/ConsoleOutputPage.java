package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ConsoleOutputPage extends BaseMainHeaderPage<ConsoleOutputPage> {

    @FindBy(xpath = "//pre[@class='console-output']")
    private WebElement consoleOutput;

    @FindBy(xpath = "//a[contains(@class,'model-link--float')]")
    private WebElement startedByUser;

    @FindBy(xpath = "(//*[name()='svg'][@title='Success'])[1]")
    private WebElement greenIconV;

    @FindBy(css = ".jenkins-icon-adjacent")
    private WebElement buildTitle;

    public ConsoleOutputPage(WebDriver driver) {
        super(driver);
    }

    public String getConsoleOutputText() {
        getWait5().until(ExpectedConditions.textToBePresentInElement(consoleOutput, "Finished"));

        return consoleOutput.getText();
    }

    public String getParameterFromConsoleOutput(String consoleText, String containParameterText) {
        String[] split = consoleText.split("\n");
        for (String str : split) {
            if (str.contains(containParameterText)) {
                return str;
            }
        }
        return null;
    }

    public String getStartedByUser() {
        return getWait2().until(ExpectedConditions.visibilityOf(startedByUser)).getText();
    }

    public boolean isDisplayedGreenIconV() {
        return getWait2().until(ExpectedConditions.visibilityOf(greenIconV)).isDisplayed();
    }

    public boolean isDisplayedBuildTitle() {
        return getWait2().until(ExpectedConditions.visibilityOf(buildTitle)).isDisplayed();
    }

    public BuildPage clickNumberBuild(int buildNumber) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text() ,'#" + buildNumber +  "')]"))).click();
        return new BuildPage(getDriver());
    }
}
