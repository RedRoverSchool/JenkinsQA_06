package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class RenameProjectPage extends BasePage {

    public RenameProjectPage(WebDriver driver){
        super(driver);
    }

    public RenameProjectPage enterNewName(String name) {
        WebElement inputTextbox = getDriver().findElement(By.name("newName"));
        inputTextbox.clear();
        inputTextbox.sendKeys(name);
        return this;
    }

    public ProjectPage submitNewName() {
        getDriver().findElement(By.name("Submit")).click();
        return new ProjectPage(getDriver());
    }

    public FolderPage SubmitNewNameFolder() {
        getDriver().findElement(By.name("Submit")).click();
        return new FolderPage(getDriver());
    }
}
