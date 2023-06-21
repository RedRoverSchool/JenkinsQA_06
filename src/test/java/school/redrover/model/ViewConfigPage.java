package school.redrover.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;

import java.util.List;
import java.util.Objects;

public class ViewConfigPage extends BaseConfigPage<ViewConfigPage, ViewPage> {

    @FindBy(xpath = "//div[@class = 'listview-jobs']/span")
    private List<WebElement> viewJobList;

    @FindBy(xpath = "//label[contains(text(), 'Recurse in subfolders')]")
    private WebElement recurseCheckbox;

    @FindBy(id = "yui-gen1-button")
    private WebElement addJobFilterDropDown;

    @FindBy(xpath = "//*[@previewendpoint='/markupFormatter/previewDescription']")
    private WebElement preview;

    @FindBy(xpath = "//*[@class='textarea-preview']")
    private WebElement previewTextArea;

    @FindBy(xpath = "//div/a[@helpurl='/help/view-config/description.html']")
    private WebElement helpDescriptionButton;

    @FindBy(xpath = "//div[@class='help-area tr']/div/div")
    private WebElement helpDescriptionTextArea;

    public ViewConfigPage(ViewPage viewPage) {
        super(viewPage);
    }

    public ViewConfigPage selectJobsInJobFilters (String name) {
        for (WebElement el : viewJobList) {
            if (Objects.equals(el.getText(), name)) {
                el.click();
            }
        }
        return this;
    }

    public ViewConfigPage selectRecurseCheckbox() {
        getWait2().until(ExpectedConditions.elementToBeClickable(recurseCheckbox)).click();

        return this;
    }

    public ViewConfigPage scrollToAddJobFilterDropDown() {
        new Actions(getDriver()).scrollToElement(addJobFilterDropDown).perform();

        return this;
    }

    public ViewConfigPage chooseJobsInJobFilters (String name) {
        for (WebElement el : viewJobList) {
            if (Objects.equals(el.getText(), name)) {
                el.click();
            }
        }
        return this;
    }

    public ViewConfigPage clickPreview() {
        preview.click();
        return this;
    }

    public String getPreviewText() {
        return getWait2().until(ExpectedConditions.visibilityOf(previewTextArea)).getText();
    }

    public ViewConfigPage clickHelpFeatureDescription() {
        helpDescriptionButton.click();
        return this;
    }

    public String getTextHelpFeatureDescription() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(
                helpDescriptionTextArea)).getText();
    }
}