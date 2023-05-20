package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BasePage;

public class ConfigureFreestyleProjectPage extends BasePage {

    public ConfigureFreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

   public ConfigureFreestyleProjectPage buttonSave() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        return this;
   }
}
