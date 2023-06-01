package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;

public class PeoplePage extends BaseMainHeaderPage<PeoplePage> {

    public PeoplePage(WebDriver driver) {
        super(driver);
    }
    public UserPage clickUserName (String newUserName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(getDriver()
                .findElement(By.xpath("//a[@href='/user/" + newUserName + "/']")))).click();
        return new UserPage(getDriver());

    }

    public boolean checkIfUserWasDeleted(String newUserName) {
        return ExpectedConditions.not(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//a[@href='/user/" + newUserName + "/']")))
                .apply(getDriver());
    }

}
