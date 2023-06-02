package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import school.redrover.model.base.BaseMainHeaderPage;

public class NewViewFolderPage extends BaseMainHeaderPage<NewViewFolderPage> {

    public NewViewFolderPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getOkButton() {
        return getDriver().findElement(By.xpath("//button[@id='ok']"));
    }

    public NewViewFolderPage enterViewName(String viewName){
        getDriver().findElement(By.id("name")).sendKeys(viewName);
        return this;
    }

    public NewViewFolderPage selectIncludeAGlobalViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[1]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        getOkButton().click();
        return this;
    }

    public NewViewFolderPage selectListViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[2]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        getOkButton().click();
        return this;
    }

    public ViewFolderPage selectMyViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[3]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        getOkButton().click();
        return new ViewFolderPage(getDriver());
    }
}
