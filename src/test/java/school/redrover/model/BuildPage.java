package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class BuildPage extends BaseMainHeaderPage<BuildPage> {

    public BuildPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getBuildHistoryTitle() {
        return getDriver().findElement(By.xpath("//a[normalize-space()='Build']"));
    }

    public WebElement getBuildHeader() {
        return getDriver().findElement(By.xpath("//h1"));
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

    public String getBooleanParameterDescription() {
        return getDriver().findElement(By.xpath("//div[@class='jenkins-form-description']")).getText();
    }
}
