package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import school.redrover.model.base.BasePage;

public class NewViewFolderPage extends BasePage {

    private static final By okButton = By.xpath("//button[@id='ok']");
    public NewViewFolderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public NewViewFolderPage enterViewName(String viewName){
        getDriver().findElement(By.id("name")).sendKeys(viewName);
        return this;
    }

    public NewViewFolderPage selectIncludeAGlobalViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[1]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        click((WebElement) okButton);
        return this;
    }

    public NewViewFolderPage selectListViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[2]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        click((WebElement) okButton);
        return this;
    }

    public ViewFolderPage selectMyViewAndClickCreate(){
        WebElement myView = getDriver().findElement(By.xpath("//fieldset/div[3]/input"));
        new Actions(getDriver()).moveToElement(myView).click(myView).perform();
        click((WebElement) okButton);
        return new ViewFolderPage(getDriver());
    }
}

