package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.model.base.BasePage;

public class DeleteFolderPage extends BasePage {

    public DeleteFolderPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickYes(){
        getDriver().findElement(By.name("Submit")).click();
        return new MainPage(getDriver());
    }

    public MainPage clickDashboard(){
        getDriver().findElement(By.xpath("//ol[@id='breadcrumbs']/li[1]")).click();
        return new MainPage(getDriver());
    }
}
