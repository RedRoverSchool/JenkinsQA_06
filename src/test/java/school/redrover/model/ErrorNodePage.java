package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ErrorNodePage extends MainPage {

    public ErrorNodePage(WebDriver driver){
        super(driver);
    }

    public String getTextError() {
        getWait2().until(ExpectedConditions
                .textToBePresentInElementLocated(By.xpath("//h1"), "Error"));
        String textError = getDriver().findElement(By.xpath("//p")).getText();
        return textError;
    }
}
