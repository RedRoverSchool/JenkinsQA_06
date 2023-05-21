package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import school.redrover.model.base.BasePage;

public class NewViewFolderPage extends BasePage {

    @FindBy(xpath = "//button[@id='ok']")
    private WebElement okButton;

    public NewViewFolderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public NewViewFolderPage interViewName(String viewName){
        getDriver().findElement(By.id("name")).sendKeys(viewName);
        return this;
    }

    public NewViewFolderPage selectIncludeAGlobalViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[1]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        okButton.click();
        return this;
    }

    public NewViewFolderPage selectListViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[2]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        okButton.click();
        return this;
    }

    public ViewFolderPage selectMyViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[3]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        okButton.click();
        return new ViewFolderPage(getDriver());
    }
}
