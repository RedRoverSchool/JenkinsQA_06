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

    public boolean isDisplayedGreenIconV() {

        return getWait5().until(ExpectedConditions.visibilityOf(greenIconV)).isDisplayed();
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
}
