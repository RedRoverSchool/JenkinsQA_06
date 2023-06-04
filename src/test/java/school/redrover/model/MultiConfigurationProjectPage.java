package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.MultiConfigurationProjectTest;
import school.redrover.model.base.BaseMainHeaderPage;

import java.time.Duration;

public class MultiConfigurationProjectPage extends BaseMainHeaderPage<MultiConfigurationProjectPage> {

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getMultiProjectName() {

        return getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//h1"))));
    }

    public MultiConfigurationProjectPage getAddDescription(String text) {

        getDriver().findElement(By.cssSelector("#description-link")).click();

        WebElement textInput = getWait2().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.cssSelector("textarea[name='description']"))));
        textInput.clear();
        textInput.sendKeys(text);
        return this;
    }

    public MultiConfigurationProjectPage getSaveButton() {

        WebElement saveButton = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        saveButton.click();
        return this;
    }

    public WebElement getInputAdd() {
        return getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
    }

    public MultiConfigurationProjectPage getDisableClick() {
        getDriver().findElement(By.xpath("//button[text () = 'Disable Project']")).click();
        return this;
    }

    public WebElement getDisableElem() {
        return getDriver().findElement(By.xpath("//button[text () = 'Disable Project']"));
    }

    public WebElement getEnableSwitch() {
        return getDriver().findElement(By.xpath("//button[text () = 'Enable']"));
    }

    public MultiConfigurationProjectPage getEnableClick() {
        getDriver().findElement(By.xpath("//*[@id='enable-project']/button")).click();
        return this;
    }

    public WebElement getDisableSwitch() {
        return getDriver().findElement(By.xpath("//button[text () = 'Disable Project']"));
    }

    public String getJobBuildStatus(String jobName) {
        WebElement buildStatus = getWait5().until(ExpectedConditions.visibilityOf(getDriver()
                .findElement(By.xpath("//div[@id='matrix']//span[@class='build-status-icon__outer']/child::*"))));
        return buildStatus.getAttribute("tooltip");
    }

    public String getDisableText() {
        return getDriver().findElement(By.id("enable-project")).getText();
    }

    public boolean isDisableButtonDisplayed() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//form[@id='disable-project']/button"))))
                .isDisplayed();
    }

    public MultiConfigurationProjectConfigPage clickConfigureSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/configure']")))).click();

        return new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver()));
    }

    public MovePage<MultiConfigurationProjectPage> clickMoveOnSideMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                getDriver().findElement(By.cssSelector("[href$='/move']")))).click();
        return new MovePage<>(this);
    }

    public MultiConfigurationProjectConfigPage getConfigPage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.linkText("Configure")))).click();
        return new MultiConfigurationProjectConfigPage(new MultiConfigurationProjectPage(getDriver()));
    }

    public MainPage deleteProject(){
        getDriver().findElement(By.xpath("//a/span[text()='Delete Multi-configuration project']/..")).click();
        getDriver().switchTo().alert().accept();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
        return new MainPage(getDriver());
    }

    public RenamePage<MultiConfigurationProjectPage> clickRename() {
        getDriver().findElement(By.linkText("Rename")).click();
        return new RenamePage<MultiConfigurationProjectPage>(this);
    }

}

