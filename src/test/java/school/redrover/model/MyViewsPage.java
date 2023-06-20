package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class MyViewsPage extends BaseMainHeaderPage<MyViewsPage> {
    @FindBy(css = "#ok-button")
    private WebElement okButton;

    @FindBy(id = "description-link")
    private WebElement onDescription;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement enterDescription;

    @FindBy(xpath = "//button[@class='jenkins-button jenkins-button--primary ']")
    private WebElement saveButtonDescription;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement textFromDescription;

    @FindBy(css = ".task-link-wrapper>a[href$='newJob']")
    private WebElement newJobNewItem;

    @FindBy(xpath = "//a[@class='addTab']")
    private WebElement newViewButton;

    public MyViewsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@href, '/configure')]")
    private WebElement configureView;

    @FindBy(xpath = "//input[@name = 'name']")
    private WebElement nameView;

    @FindBy(xpath = "//button[@name = 'Submit']")
    private WebElement submitView;

    @FindBy(xpath = "//h2")
    private WebElement statusMessage;

    @FindBy(xpath = "//div[@class = 'tab active']")
    private WebElement activeView;

    @FindBy(xpath = "//div[@class = 'tab'][last()-1]")
    private WebElement inactiveLastCreatedMyView;

    @FindBy(xpath = "//a[@href = 'delete']")
    private WebElement deleteViewButton;

    @FindBy(xpath = "//div[@class='tabBar']//div[starts-with(@class, 'tab')]")
    private List<WebElement> allViews;

    @FindBy(xpath = "//a[@href='newJob']")
    private WebElement createAJob;

    @FindBy(id = "name")
    private WebElement anItemName;

    @FindBy(css = ".hudson_model_FreeStyleProject")
    private WebElement freestyleProject;

    @FindBy(xpath = "//button[@formnovalidate = 'formNoValidate']")
    private WebElement saveButton;

    public MyViewsPage clickOkButton() {
        getWait5().until(ExpectedConditions.visibilityOf(okButton)).click();
        return this;
    }

    public MyViewsPage clickOnDescription() {
        getWait5().until(ExpectedConditions.visibilityOf(onDescription)).click();
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage enterDescription(String name) {
        getWait5().until(ExpectedConditions.visibilityOf(enterDescription)).sendKeys(name);
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage clickSaveButtonDescription() {
        getWait5().until(ExpectedConditions.visibilityOf(saveButtonDescription)).click();
        return new MyViewsPage(getDriver());
    }

    public String getTextFromDescription() {

        return getWait5().until(ExpectedConditions.visibilityOf(textFromDescription)).getText();
    }

    public MyViewsPage clearTextFromDescription() {
        getWait10().until(ExpectedConditions.visibilityOf(textFromDescription)).clear();
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage enterNewDescription(String name) {
        getWait5().until(ExpectedConditions.visibilityOf(textFromDescription)).sendKeys(name);
        return new MyViewsPage(getDriver());
    }

    public NewJobPage clickNewItem() {
        newJobNewItem.click();
        return new NewJobPage(getDriver());
    }

    public String getStatusMessageText() {

        return statusMessage.getText();
    }

    public NewViewPage clickNewViewButton() {
        TestUtils.click(this, newViewButton);

        return new NewViewPage(getDriver());
    }

    public String getActiveView() {

        return TestUtils.getText(this, activeView);
    }

    public MyViewsPage clickInactiveLastCreatedMyView() {
        TestUtils.click(this, inactiveLastCreatedMyView);

        return this;
    }

    public MyViewsPage editMyViewNameAndClickSubmitButton(String editedMyViewName) {
        TestUtils.click(this, configureView);
        TestUtils.sendTextToInput(this, nameView, editedMyViewName);
        TestUtils.click(this, submitView);

        return this;
    }

    public DeletePage<MyViewsPage> clickDeleteViewButton() {
        TestUtils.click(this, deleteViewButton);

        return new DeletePage<>(this);
    }

    public List<String> getListOfAllViews() {
        List<String> list = new ArrayList<>();
        List<WebElement> views = allViews;
        for (WebElement view : views) {
            list.add(view.getText());
        }

        return list;
    }

    public MyViewsPage clickCreateAJob() {
        getWait5().until(ExpectedConditions.visibilityOf(createAJob)).click();
        return new MyViewsPage(getDriver());
    }

    public MyViewsPage enterAnItemName(String name) {
        getWait5().until(ExpectedConditions.visibilityOf(anItemName)).sendKeys(name);
        return this;
    }

    public MyViewsPage clickFreestyleProject() {
        getWait5().until(ExpectedConditions.visibilityOf(freestyleProject)).click();
        return this;
    }

    public MyViewsPage clickSaveButton() {
        getWait5().until(ExpectedConditions.visibilityOf(saveButton)).click();

        return this;
    }
}
