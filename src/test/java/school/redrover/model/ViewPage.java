package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ViewPage extends BasePage {

    @FindBy(xpath = "//div[@id='description']/child::*")
    private WebElement description;

    @FindBy(xpath = "//input[@id = 'name']")
    private WebElement stringSearchItemName;

    @FindBy(xpath = "//span[normalize-space()='Pipeline']")
    private WebElement pipelineProject;

    @FindBy(xpath = "//button[@id = 'ok-button']")
    private WebElement saveButton;

    @FindBy(xpath = "//tbody/tr/td/a/span")
    private List<WebElement> jobList;

    public ViewPage(WebDriver driver) {
        super(driver);
    }

    public ViewPage inputAnItemName(String text) {

        sendTextToInput(stringSearchItemName, text);
        return new ViewPage(getDriver());
    }

    public ViewPage clickPipelineProject() {
        click(pipelineProject);
        return new ViewPage(getDriver());
    }

    public ConfigurePage clickSaveButton() {
        click(saveButton);
        return new ConfigurePage(getDriver());
    }

    public ViewPage clickAddDescription() {
        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        return this;
    }

    public ViewPage inputDescText(String desc) {
        new Actions(getDriver()).
                click(getDriver().findElement(By.xpath("//textarea[@name='description']"))).
                sendKeys(desc).
                perform();
        return this;
    }

    public ViewPage saveDescription() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return this;
    }

    public String getDescriptionText() {
        return description.getText();
    }

    public String getJobName (String name) {

        return getDriver().findElement(By.xpath(String.format("//a[@href='job/%s/']", name))).getText();
    }

    public String getViewName () {

        return getText(getDriver().findElement(By.xpath("//div[@class = 'tab active']")));
    }

    public void clickBreadcrumbPathItem(int n, String name) {
        List<WebElement> breadcrumbTree = getDriver().findElements(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']/a"));
        if (breadcrumbTree.get(breadcrumbTree.size() - n).getText().contains(name)) {
            breadcrumbTree.get(breadcrumbTree.size() - n).click();
        }
    }

    public ViewPage createFreestyleProjectInsideFolderAndView(String jobName, String viewName, String folderName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//tr[@id='job_%s']//a", folderName)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='/view/%s/job/%s/newJob']", viewName, folderName)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("name")))).sendKeys(jobName);
        getDriver().findElement(By.xpath("//span[@class='label'][text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath("//div[@class='btn-decorator']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        clickBreadcrumbPathItem(3, viewName);

        return new ViewPage(getDriver());
    }

    public NewViewPage createNewView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/newView']"))).click();

        return new NewViewPage(getDriver());
    }

    public List<String> getJobNamesList() {
        if (jobList.size() > 0) {
            getWait10().until(ExpectedConditions.visibilityOfAllElements(jobList));
            List<String> textList = new ArrayList<>();
            for (WebElement element : jobList) {
                textList.add(element.getText());
            }
            return textList;
        }
        return null;
    }

    public ViewPage clickFreestyleProject(){
        click(getDriver().findElement(By.xpath("//span[text()='Freestyle project']")));
        return this;
    }
}
