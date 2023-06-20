package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

import java.util.ArrayList;
import java.util.List;

public class BuildPage extends BaseMainHeaderPage<BuildPage> {

    @FindBy(xpath = "//span[@class='build-status-icon__outer']//*[local-name()='svg']")
    private WebElement greenIconV;

    @FindBy(xpath = "//h1")
    private WebElement buildHeader;

    @FindBy(xpath = "//label[@class='attach-previous ']")
    private WebElement booleanParameterName;

    @FindBy(xpath = "//input[@checked='true']")
    private WebElement checkedParameter;

    @FindBy(xpath = "//select[@name='value']/option")
    private List<WebElement> choiceParametersList;

    @FindBy(xpath = "//div[@class='jenkins-form-description']")
    private WebElement description;

    @FindBy(xpath = "//input[@name='value']")
    private WebElement checkbox;

    public BuildPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getBuildHeader() {
        return buildHeader;
    }
    public boolean buildHeaderIsDisplayed() {
        return buildHeader.isDisplayed();
    }

    public boolean isDisplayedGreenIconV() {

        return getWait5().until(ExpectedConditions.visibilityOf(greenIconV)).isDisplayed();
    }

    public boolean isDisplayedBuildTitle() {

        return getBuildHeader().getText().contains("Build #1");
    }

    public String getBooleanParameterName() {
        return booleanParameterName.getText();
    }

    public String getBooleanParameterCheckbox() {
        return checkbox.getAttribute("checked");
    }

    public String getParameterDescription() {
        return description.getText();
    }

    public boolean isParameterNameDisplayed(String parameterName) {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String
                .format("//div[@class = 'jenkins-form-label help-sibling' and text() = '%s']", parameterName))))
                .isDisplayed();
    }

    public List<WebElement> getChoiceParametersList() {
        return choiceParametersList;
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
       String checked = checkedParameter.getAttribute("checked");
        if(checked.equals("true")){
            return true;
        }
        return false;
    }
}
