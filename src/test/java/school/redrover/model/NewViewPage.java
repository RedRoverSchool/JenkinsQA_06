package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.runner.TestUtils;

public class NewViewPage extends BaseMainHeaderPage<NewViewPage> {

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(xpath = "//label[@for='hudson.model.ListView']")
    private WebElement listViewRadio;

    @FindBy(xpath = "//label[@for='hudson.model.MyView']")
    private WebElement myViewRadio;

    @FindBy(name = "Submit")
    private WebElement createButton;

    public NewViewPage(WebDriver driver) {
        super(driver);
    }

    public NewViewPage setNewViewName(String newViewName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(nameInput)).sendKeys(newViewName);

        return this;
    }

    public NewViewPage selectListView () {
        listViewRadio.click();

        return this;
    }

    public NewViewPage selectMyView() {
        TestUtils.click(this, myViewRadio);

        return this;
    }

    public ViewPage selectMyViewAndClickCreate() {
        TestUtils.click(this, myViewRadio);
        return new ViewPage(getDriver());
    }

    public ListViewConfigPage selectListViewAndClickCreate() {
        TestUtils.click(this, myViewRadio);
        return new ListViewConfigPage(new ViewPage(getDriver()));
    }

    public MyViewConfigPage clickCreateButton () {
        createButton.click();

        return new MyViewConfigPage(new ViewPage(getDriver()));
    }

    public ActiveViewPage clickCreateMyViewButton() {
        TestUtils.click(this, createButton);

        return new ActiveViewPage(getDriver());
    }
}
