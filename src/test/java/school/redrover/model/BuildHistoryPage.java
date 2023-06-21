package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.List;

public class BuildHistoryPage extends BaseMainHeaderPage<BuildHistoryPage> {

    @FindBy(xpath = "//table[@id='projectStatus']/tbody/tr/td[4]")
    private WebElement statusMessage;

    @FindBy(xpath = "//div[@class='simileAjax-bubble-contentContainer simileAjax-bubble-contentContainer-pngTranslucent']")
    private WebElement bubbleContainer;

    @FindBy(xpath = "//div[@class='timeline-event-bubble-title']/a")
    private WebElement bubbleTitle;

    @FindBy(xpath = "//h1")
    private WebElement pageHeader;

    @FindBy(xpath = "//table[@id='projectStatus']/tbody/tr")
    private List<WebElement> buildHistoryTable;

    @FindBy(css = ".task-link-wrapper>a[href$='newJob']")
    private WebElement newItem;

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    public BuildPage clickPipelineProjectBuildNumber(String projectName) {

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/" + projectName + "/1/']")))
                .click();

        return new BuildPage(getDriver());
    }

    public ConsoleOutputPage clickProjectBuildConsole(String projectBuildName){
        getDriver().findElement(By.xpath("//a[contains(@href, '" + projectBuildName + "')  and contains(@href, 'console') and not(contains(@href, 'default'))]")).click();
        return new ConsoleOutputPage(getDriver());
    }

    public String getStatusMessageText() {
        getDriver().navigate().refresh();
        return statusMessage.getText();
    }

    public BuildHistoryPage clickBuildNameOnTimeline(String projectBuildName) {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + projectBuildName + "')]"))).click();
        return this;
    }

    public String getBubbleTitleOnTimeline() {
        getWait5().until(ExpectedConditions.visibilityOf(bubbleContainer));
        return bubbleTitle.getText();
    }

    public int getNumberOfLinesInBuildHistoryTable() {
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1")));

        return buildHistoryTable.size();
    }

    public NewJobPage clickNewItem() {
        newItem.click();
        return new NewJobPage(getDriver());
    }
}
