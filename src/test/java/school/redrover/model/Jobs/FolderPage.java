package school.redrover.model.Jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.*;
import school.redrover.model.JobsConfig.FolderConfigPage;
import school.redrover.model.base.BaseJobPage;
import school.redrover.runner.TestUtils;

import java.util.List;

public class FolderPage extends BaseJobPage<FolderPage> {

    @FindBy(css = "#tasks>:nth-child(3)")
    private WebElement buttonNewItem;

    @FindBy(xpath = "//div[@class='tab']")
    private WebElement buttonNewView;

    @FindBy(xpath = "//div[@id='main-panel']/h1")
    private By folderName;

    @FindBy(id = "view-message")
    private By folderDescription;

    @FindBy(css = ".jenkins-table__link")
    private List<WebElement> jobList;

    public FolderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FolderConfigPage clickConfigure() {
        return new FolderConfigPage(new FolderPage(getDriver()));
    }

    public NewJobPage clickNewItem() {
        buttonNewItem.click();
        return new NewJobPage(getDriver());
    }

    public NewViewFolderPage clickNewView() {
        buttonNewView.click();
        return new NewViewFolderPage(getDriver());
    }

    public String getFolderName() {
        return TestUtils.getText(this, getWait2().until(ExpectedConditions.visibilityOfElementLocated(folderName)));
    }

    public String getFolderDescription() {
        return TestUtils.getText(this, getWait2().until(ExpectedConditions.visibilityOfElementLocated(folderDescription)));
    }

    public boolean nestedFolderIsVisibleAndClickable(String nestedFolder) {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + nestedFolder + "/')]"))).isDisplayed()
                && getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(@href,'job/" + nestedFolder + "/')]"))).isEnabled();
    }

    public boolean viewIsDisplayed(String viewName){
       return getDriver().findElement(By.linkText(viewName)).isDisplayed();
    }

    public List<String> getJobList() {
        return jobList
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
