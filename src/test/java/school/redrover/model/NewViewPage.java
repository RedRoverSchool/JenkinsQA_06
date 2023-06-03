package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;
import school.redrover.model.base.BaseModel;
import school.redrover.runner.TestUtils;

public class NewViewPage extends BaseMainHeaderPage<NewViewPage> {

    public NewViewPage(WebDriver driver) {
        super(driver);
    }

    public NewViewPage setNewViewName(String newViewName) {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys(newViewName);

        return this;
    }

    public NewViewPage selectListView () {
        getDriver().findElement(By.xpath("//label[@for='hudson.model.ListView']")).click();

        return this;
    }

    public NewViewPage selectMyView() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//label[@for='hudson.model.MyView']")));

        return this;
    }

    public NewViewPage selectIncludeAGlobalView() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//label[@for = 'hudson.model.ProxyView']")));

        return this;
    }

    public ViewConfigPage clickCreateButton () {
        getDriver().findElement(By.name("Submit")).click();

        return new ViewConfigPage(getDriver());
    }

    public ActiveViewPage clickCreateMyViewButton() {
        TestUtils.click(this, getDriver().findElement(By.xpath("//button[@name = 'Submit']")));

        return new ActiveViewPage(getDriver());
    }
}
