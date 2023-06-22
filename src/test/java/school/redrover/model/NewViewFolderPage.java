package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BaseMainHeaderPage;

public class NewViewFolderPage extends BaseMainHeaderPage<NewViewFolderPage> {

    @FindBy(xpath = "//button[@id='ok']")
    private WebElement okButton;

    @FindBy(id = "name")
    private WebElement viewNameInFolder;

    @FindBy(xpath = "//fieldset/div[3]/input")
    private WebElement myView;

    public NewViewFolderPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getOkButton() {
        return okButton;
    }

    public NewViewFolderPage enterViewName(String viewName) {
        viewNameInFolder.sendKeys(viewName);
        return this;
    }

    public ViewFolderPage selectMyViewAndClickCreate() {
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        getOkButton().click();
        return new ViewFolderPage(getDriver());
    }
}
