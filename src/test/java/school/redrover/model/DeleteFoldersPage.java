package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BaseModel;

public class DeleteFoldersPage extends BaseMainHeaderPage<DeleteFoldersPage> {

    public DeleteFoldersPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickYes(){
        getDriver().findElement(By.name("Submit")).click();
        return new MainPage(getDriver());
    }
}
