package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class ViewPage extends BaseMainHeaderPage<ViewPage> {

    public ViewPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getJobList() {
        return getDriver().findElements(By.xpath("//tbody/tr/td/a/span"));
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
        return getDriver().findElement(By.xpath("//div[@id='description']/child::*")).getText();
    }

    public String getJobName(String name) {

        return getDriver().findElement(By.xpath(String.format("//a[@href='job/%s/']", name))).getText();
    }

    public String getViewName() {

        return TestUtils.getText(this, getDriver().findElement(By.xpath("//div[@class = 'tab active']")));
    }

    public NewViewPage createNewView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/newView']"))).click();

        return new NewViewPage(getDriver());
    }

    public List<String> getJobNamesList() {
        if (getJobList().size() > 0) {
            getWait10().until(ExpectedConditions.visibilityOfAllElements(getJobList()));
            List<String> textList = new ArrayList<>();
            for (WebElement element : getJobList()) {
                textList.add(element.getText());
            }
            return textList;
        }
        return null;
    }

    public ViewPage clickDropDownMenuFolder(String folderName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//tr[@id='job_%s']//a", folderName)))).click();
        return this;
    }

    public NewJobPage selectNewItemInDropDownMenu(String viewName, String folderName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[@href='/view/%s/job/%s/newJob']", viewName, folderName)))).click();
        return new NewJobPage(getDriver());
    }

    public ViewPage clickEditView(String nameProject) {
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//*[@href='/view/%s/configure']", nameProject.replaceAll(" ","%20"))))).click();
        return this;
    }

    public ViewPage enterDescription(String text) {
        new Actions(getDriver())
                .click(getDriver().findElement(By.xpath("//*[@name='description']")))
                .sendKeys(text)
                .perform();
        return this;
    }

    public ViewPage clickPreview() {
        getDriver().findElement(
                By.xpath("//*[@previewendpoint='/markupFormatter/previewDescription']")).click();
        return this;
    }

    public String getPreviewText() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@class='textarea-preview']"))).getText();
    }

    public ViewPage clickViewConfigOkButton() {
        getDriver().findElement(By.xpath("//*[@formnovalidate='formNoValidate']")).click();
        return this;
    }

    public ViewPage clickHelpFeatureDescription() {
        getDriver().findElement(
                By.xpath("//div/a[@helpurl='/help/view-config/description.html']")).click();
        return this;
    }

    public String getTextHelpFeatureDescription() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='help-area tr']/div/div"))).getText();
    }

    public DeletePage<MainPage> clickDeleteView() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='delete']"))).click();
        return new DeletePage<>(new MainPage(getDriver()));
    }

    public NewViewPage clickPlusSign() {
        getDriver().findElement(By.xpath("//div[@id='projectstatus-tabBar']/div/div[1]/div[2]/a")).click();
        return new NewViewPage(getDriver());
    }
}
