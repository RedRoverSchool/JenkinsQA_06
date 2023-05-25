package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BasePage;

public class RenameFreestyleProjectPage extends BasePage {
    public RenameFreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public RenameFreestyleProjectPage enterNewName(String name) {
        WebElement inputTextbox = getDriver().findElement(By.name("newName"));
        inputTextbox.clear();
        inputTextbox.sendKeys(name);
        return this;
    }

    public FreestyleProjectPage submitNewName() {
        getDriver().findElement(By.name("Submit")).click();
        return new FreestyleProjectPage(getDriver());
    }
}
