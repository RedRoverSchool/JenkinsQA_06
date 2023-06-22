package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class PeoplePage extends BaseMainHeaderPage<PeoplePage> {

    @FindBy(css = ".task-link-wrapper>a[href$='newJob']")
    private WebElement newItem;

    @FindBy(xpath = "//a[@class='sortheader'][contains(text(), 'User ID')]")
    private WebElement userIDButton;

    @FindBy(xpath = "//a[@class='sortheader'][contains(text(), 'User ID')]/span")
    private WebElement userIDButtonArrow;

    @FindBy(xpath = "//a[@class='sortheader'][contains(text(), 'Name')]")
    private WebElement nameButton;

    @FindBy(xpath = "//h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//table[@id = 'people']/tbody")
    private WebElement user;

    public PeoplePage(WebDriver driver) {
        super(driver);
    }

    public UserPage clickUserName(String newUserName) {
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
        newItem.click();
        return new NewJobPage(getDriver());
    }

    public PeoplePage clickUserIDButton() {
        userIDButton.click();
        return new PeoplePage(getDriver());
    }

    public boolean isUserIDButtonWithoutArrow() {
        return userIDButtonArrow.getText().isEmpty();
    }

    public boolean isUserIDButtonWithUpArrow() {
        return userIDButtonArrow.getText().trim().contains("↑");
    }

    public boolean isUserIDButtonWithDownArrow() {
        return userIDButtonArrow.getText().trim().contains("↓");
    }

    public PeoplePage clickNameButton() {
        nameButton.click();
        return new PeoplePage(getDriver());
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean checkIfUserWasAdded(String userName) {
        return user.getText().contains(userName);
    }
}
