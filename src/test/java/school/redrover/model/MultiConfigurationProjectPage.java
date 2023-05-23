package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BasePage;

public class MultiConfigurationProjectPage extends BasePage {

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

    public MultiConfigurationProjectPage getSaveButton(){

        WebElement saveButton = getDriver().findElement(By.cssSelector("button[formnovalidate='formNoValidate' ]"));
        saveButton.click();
        return this;
    }

    public WebElement getInputAdd (){
      return getDriver().findElement(By.xpath("//div[@id='description']/div[1]"));
    }

    public MultiConfigurationProjectPage getDisableClick() {
        getDriver().findElement(By.xpath("//button[text () = 'Disable Project']")).click();
        return this;
    }

    public WebElement getEnableSwitch (){
    return getDriver().findElement(By.xpath("//button[text () = 'Enable']"));
    }
}
