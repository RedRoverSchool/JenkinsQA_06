package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageJenkinsPage extends MainPage {

    @FindBy(xpath ="//a[@href='securityRealm/']")
    private WebElement manageUsers;

    public ManageJenkinsPage(WebDriver driver){
        super(driver);
    }

    public ManageJenkinsPage inputToSearchField(String text) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("settings-search-bar"))).sendKeys(text);
        return new ManageJenkinsPage(getDriver());
    }

    public String getNoResultTextInSearchField() {
        Actions action = new Actions(getDriver());
        WebElement searchResultDropdown = getDriver().findElement(By.cssSelector("div.jenkins-search__results-container--visible"));
        action.moveToElement(searchResultDropdown).perform();
        return searchResultDropdown.getText();
    }

    public ManageUsersPage clickManageUsers() {
        getWait2().until(ExpectedConditions.elementToBeClickable(manageUsers)).click();
        return new ManageUsersPage(getDriver());
    }
}
