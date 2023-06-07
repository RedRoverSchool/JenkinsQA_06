package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseProjectPage;

public class MultiConfigurationProjectPage extends BaseProjectPage<MultiConfigurationProjectPage> {

    public MultiConfigurationProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigurationProjectConfigPage clickConfigure() {
        setupClickConfigure();
        return new MultiConfigurationProjectConfigPage(this);
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
    public boolean isEnabledButtonDisplayed(){
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Enable']"))).isDisplayed();
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

