package school.redrover.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;

import java.util.List;
import java.util.Objects;

public class ListViewConfigPage extends BaseConfigPage <ListViewConfigPage, ViewPage> {

    @FindBy(xpath = "//div[@class = 'listview-jobs']/span")
    private List<WebElement> viewJobList;

    @FindBy(xpath = "//label[contains(text(), 'Recurse in subfolders')]")
    private WebElement recurseCheckbox;

    @FindBy(id = "yui-gen1-button")
    private WebElement addJobFilter;

    @FindBy(xpath = "//a[@tooltip='Help for feature: Description']")
    private WebElement helpForFeatureDescription;

    @FindBy(xpath = "//div[@class='help-area tr']/div/div")
    private WebElement textHelpDescription;

    public ListViewConfigPage(ViewPage viewPage) {
        super(viewPage);
    }

    public ListViewConfigPage selectJobsInJobFilters (String name) {

        for (WebElement el : viewJobList) {
            if (Objects.equals(el.getText(), name)) {
                el.click();
            }
        }
        return this;
    }

    public ListViewConfigPage selectRecurseCheckbox() {
        getWait2().until(ExpectedConditions.elementToBeClickable(recurseCheckbox)).click();
        return this;
    }

    public ListViewConfigPage scrollToAddJobFilterDropDown() {
        new Actions(getDriver()).scrollToElement(addJobFilter).perform();
        return this;
    }

    public ListViewConfigPage clickHelpForFeatureDescription() {
        helpForFeatureDescription.click();
        return this;
    }

    public String getTextHelpFeatureDescription() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(textHelpDescription)).getText();
    }
}
