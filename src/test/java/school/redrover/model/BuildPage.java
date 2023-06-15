package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.ArrayList;
import java.util.List;

public class BuildPage extends BaseMainHeaderPage<BuildPage> {

    public BuildPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getBuildHeader() {
        return getDriver().findElement(By.xpath("//h1"));
    }
    public boolean buildHeaderIsDisplayed() {
        return getDriver().findElement(By.xpath("//h1")).isDisplayed();
    }

    public boolean isDisplayedGreenIconV() {

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='build-status-icon__outer']//*[local-name()='svg']"))).isDisplayed();
    }

    public boolean isDisplayedBuildTitle() {

        return getBuildHeader().getText().contains("Build #1");
    }

    public EditBuildInformationPage clickEditBuildInformationButton(String projectName) {
        getDriver().findElement(By.xpath("//*[@href = '/job/" + projectName + "/1/configure']")).click();
        return new EditBuildInformationPage(getDriver());
    }

    public String getProjectDescription() {
       return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='description']/div[1]")))
               .getText();
    }

    public String getBooleanParameterName() {
        return getDriver().findElement(By.xpath("//label[@class='attach-previous ']")).getText();
    }

    public String getBooleanParameterCheckbox() {
        return getDriver().findElement(By.xpath("//input[@name='value']")).getAttribute("checked");
    }

    public String getParameterDescription() {
        return getDriver().findElement(By.xpath("//div[@class='jenkins-form-description']")).getText();
    }

    public boolean isParameterNameDisplayed(String parameterName) {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String
                .format("//div[@class = 'jenkins-form-label help-sibling' and text() = '%s']", parameterName))))
                .isDisplayed();
    }

    public List<WebElement> getChoiceParametersList() {
        return getDriver().findElements(By.xpath("//select[@name='value']/option"));
    }

    public List<String> getChoiceParametersValuesList() {
        if (getChoiceParametersList().size() > 0) {
            getWait10().until(ExpectedConditions.visibilityOfAllElements(getChoiceParametersList()));
            List<String> valuesList = new ArrayList<>();
            for (WebElement element : getChoiceParametersList()) {
                valuesList.add(element.getText());
            }
            return valuesList;
        }
        return null;
    }

    public boolean checkedTrue() {
       String checked = getDriver().findElement(By.xpath("//input[@checked='true']")).getAttribute("checked");
        if(checked.equals("true")){
            return true;
        }
        return false;
    }
}
