package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.model.base.BaseMainHeaderPage;

public class EditBuildInformationPage extends BaseMainHeaderPage<EditBuildInformationPage>{

    public EditBuildInformationPage(WebDriver driver) {
        super(driver);
    }

    public EditBuildInformationPage editBuildDescription(String newDescription) {
       WebElement description = getDriver().findElement(By.xpath("//textarea[@name='description']"));
       description.clear();
       description.sendKeys(newDescription);
        return this;
    }

    public BuildPage clickSaveButton() {
     getDriver().findElement(By.xpath("//*[@name = 'Submit']")).click();
     return new BuildPage(getDriver());
    }
}
