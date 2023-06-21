package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseConfigPage;

import java.util.List;
import java.util.Objects;

public class ViewConfigPage extends BaseConfigPage<ViewConfigPage, ViewPage> {

    public ViewConfigPage(ViewPage viewPage) {
        super(viewPage);
    }

    public ViewConfigPage selectJobsInJobFilters (String name) {
        List<WebElement> viewJobList = getDriver().findElements(By.xpath("//div[@class = 'listview-jobs']/span"));

        for (WebElement el : viewJobList) {
            if (Objects.equals(el.getText(), name)) {
                el.click();
            }
        }
        return this;
    }

    public ViewConfigPage selectRecurseCheckbox() {
        getWait2().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.xpath("//label[contains(text(), 'Recurse in subfolders')]")))).click();

        return this;
    }

    public ViewConfigPage scrollToAddJobFilterDropDown() {
        new Actions(getDriver()).scrollToElement(getDriver().findElement(By.id("yui-gen1-button"))).perform();

        return this;
    }

    public ViewConfigPage chooseJobsInJobFilters (String name) {
        List<WebElement> viewJobList = getDriver().findElements(By.xpath("//div[@class = 'listview-jobs']/span"));

        for (WebElement el : viewJobList) {
            if (Objects.equals(el.getText(), name)) {
                el.click();
            }
        }
        return this;
    }

    public ViewConfigPage clickPreview() {
        getDriver().findElement(
                By.xpath("//*[@previewendpoint='/markupFormatter/previewDescription']")).click();
        return this;
    }

    public String getPreviewText() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@class='textarea-preview']"))).getText();
    }

    public ViewConfigPage clickHelpFeatureDescription() {
        getDriver().findElement(
                By.xpath("//div/a[@helpurl='/help/view-config/description.html']")).click();
        return this;
    }

    public String getTextHelpFeatureDescription() {
        return getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='help-area tr']/div/div"))).getText();
    }
}