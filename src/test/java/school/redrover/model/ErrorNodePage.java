package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class ErrorNodePage extends BaseMainHeaderPage<ErrorNodePage> {

    public ErrorNodePage(WebDriver driver){
        super(driver);
    }

    public String getTextError() {
        getWait2().until(ExpectedConditions
                .textToBePresentInElementLocated(By.xpath("//h1"), "Error"));
        String textError = getDriver().findElement(By.xpath("//p")).getText();
        return textError;
    }

    public String getErrorEqualName(){
        return getDriver().findElement(By.xpath("//*[@id='main-panel']/p")).getText();
    }

    public String getErrorMessage(){
        return getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText();
    }
}
