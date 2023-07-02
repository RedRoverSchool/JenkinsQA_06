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

    public NewViewPage setNewViewName(String viewName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(nameInput)).sendKeys(viewName);
        return this;
    }

    private NewViewPage selectViewType(TestUtils.ViewType viewType) {
        getDriver().findElement(viewType.getLocator()).click();
        return this;
    }

    public <ViewConfigPage extends BaseMainHeaderPage<?>> ViewConfigPage selectTypeViewClickCreate(
            TestUtils.ViewType viewType, Class<ViewConfigPage> clazz) {
        selectViewType(viewType);
        createButton.click();
        return (ViewConfigPage) viewType.createNextPage(getDriver());
    }
}
