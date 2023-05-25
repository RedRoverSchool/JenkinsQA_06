package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StatusUserPage extends MainPage {

    public StatusUserPage(WebDriver driver) {
        super(driver);
    }

    public StatusUserPage clickAddDescriptionLink() {
        getDriver().findElement(By.id("description-link")).click();

        return this;
    }

    public StatusUserPage clearDescriptionInputField() {
        getWait10().until(ExpectedConditions.visibilityOf(getDriver()
                .findElement(By.xpath("//textarea[@name='description']"))))
                .clear();

        return this;
    }

    public StatusUserPage setDescription(String text) {
        getWait10().until(ExpectedConditions.visibilityOf(getDriver()
                .findElement(By.xpath("//textarea[@name='description']"))))
                .sendKeys(text);

        return this;
    }

    public StatusUserPage clickSaveButton() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }

    public String getDescription() {

        return getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText();
    }

    public String getDescriptionText() {

        return getDriver().findElement(By.xpath("//textarea[@name='description']")).getText();
    }

    public String getUserIDText() {

        return getDriver().findElement(
                By.xpath("//div[@id='main-panel']/div[contains(text(), 'ID')]")).getText();
    }

}
