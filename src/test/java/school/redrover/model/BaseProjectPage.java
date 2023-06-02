package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

import static org.openqa.selenium.By.cssSelector;

public abstract class BaseProjectPage<Self extends BaseProjectPage> extends BaseJobPage<BaseProjectPage<?>> {

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getEnableForm(){
        return getDriver().findElement(By.cssSelector("form#enable-project"));
    }

    public WebElement getDisableButton(){
        return getDriver().findElement(By.xpath("//form[@id='disable-project']/button"));

    }

    public BaseProjectPage clickDisable(){
        getDriver().findElement(By.xpath("//form[@id='disable-project']/button")).click();
        return this;
    }

    public BaseProjectPage clickEnable(){
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver().
                findElement(By.xpath("//form[@id='enable-project']/button")))).click();

        return this;
    }

    public Self clickBuildNow() {
        getDriver().findElement(cssSelector("[href*='build?']")).click();
        return (Self)this;
    }

    public ConsoleOutputPage openConsoleOutputForBuild(int buildNumber){
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@class = 'build-row-cell']//a[contains(@href,'/" + buildNumber +  "/console')]"))).click();
        return new ConsoleOutputPage(getDriver());
    }

    public int getBuildsQuantity() {
        return Arrays.asList(getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[@class = 'build-row-cell']")))).size();
    }



}
