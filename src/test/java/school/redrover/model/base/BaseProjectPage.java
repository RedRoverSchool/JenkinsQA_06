package school.redrover.model.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.ConsoleOutputPage;

import static org.openqa.selenium.By.cssSelector;

public abstract class BaseProjectPage<Self extends BaseProjectPage<?>> extends BaseJobPage<BaseProjectPage<?>> {

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getEnableButtonText(){
        return getDriver().findElement(By.cssSelector("form#enable-project")).getText();
    }

    public String getDisableButtonText() {
        return getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).getText();

    }

    public Self clickDisable() {
        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();
        return (Self)this;
    }

    public Self clickEnable() {
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().
                findElement(By.xpath("//form[@id='enable-project']/button")))).click();

        return (Self)this;
    }

    public Self clickBuildNow() {
        getDriver().findElement(cssSelector("[href*='build?']")).click();
        return (Self)this;
    }

    public ConsoleOutputPage openConsoleOutputForBuild(int buildNumber) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@class = 'build-row-cell']//a[contains(@href,'/" + buildNumber +  "/console')]"))).click();
        return new ConsoleOutputPage(getDriver());
    }

    public int getBuildsQuantity() {
        return getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//td[@class = 'build-row-cell']"))).size();
    }
}
