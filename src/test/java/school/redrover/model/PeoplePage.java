package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import school.redrover.model.base.BaseMainHeaderPage;

public class PeoplePage extends BaseMainHeaderPage<PeoplePage> {

    public PeoplePage(WebDriver driver) {
        super(driver);
    }

    public UserPage clickUserName (String newUserName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//a[@href='/user/" + newUserName + "/']")))).click();
        return new UserPage(getDriver());

    }

    public boolean checkIfUserWasDeleted(String newUserName) {
        return ExpectedConditions.not(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//a[@href='/user/" + newUserName + "/']")))
                .apply(getDriver());
    }

    public NewJobPage clickNewItem() {
        getDriver().findElement(By.cssSelector(".task-link-wrapper>a[href$='newJob']")).click();
        return new NewJobPage(getDriver());
    }

    public PeoplePage clickUserIDButton() {
        WebElement userIDButton = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]"));
            userIDButton.click();
            return new PeoplePage(getDriver());
    }

    public boolean isUserIDButtonWithoutArrow() {
        WebElement userIDButtonArrow = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        return userIDButtonArrow.getText().isEmpty();
    }

    public boolean isUserIDButtonWithUpArrow() {
        WebElement userIDButtonArrow = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        return userIDButtonArrow.getText().trim().contains("↑");
    }

    public boolean isUserIDButtonWithDownArrow() {
        WebElement userIDButtonArrow = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'User ID')]/span"));
        return userIDButtonArrow.getText().trim().contains("↓");
    }

    public PeoplePage clickNameButton() {
        WebElement nameButton = getDriver().findElement(
                By.xpath("//a[@class='sortheader'][contains(text(), 'Name')]"));
        nameButton.click();
        return new PeoplePage(getDriver());
    }

    public String getPageTitle() {
        return getDriver().findElement(By.xpath("//h1")).getText();
    }

    public boolean checkIfUserWasAdded(String userName) {
        return getDriver().findElement(By.xpath("//table[@id = 'people']/tbody")).getText().contains(userName);

    }
}
